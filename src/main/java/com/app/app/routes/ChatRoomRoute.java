package com.app.app.routes;

import com.alibaba.fastjson.JSON;
import com.app.app.controll.SqlMessage;
import com.app.app.form.chat.Message;
import com.app.app.model.MolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/chat")
@Component
public class ChatRoomRoute {

    private static ConcurrentHashMap<String, List<ChatRoomRoute>> webSocketMap =
            new ConcurrentHashMap<>(3);

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收roomId
    private String roomId;
    private Message newMessage = new Message();
    private MolResponse molResponse = new MolResponse();

    public int sender;
    public int receiver;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
    * static和@Autowired 这样写是为了解决@Autowired在websocket中无法自动注入
    * */
    private static SqlMessage sqlMessage;

    @Autowired
    public void setSqlMessage(SqlMessage sqlMessage) {
        ChatRoomRoute.sqlMessage = sqlMessage;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        initParams(session.getQueryString());
        this.session = session;
        addSocketServer2Map(this);
        sendMessage(JSON.toJSONString(sqlMessage.findForUnRead(this.roomId)));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        List<ChatRoomRoute> wssList = webSocketMap.get(roomId);
        if (wssList != null) {
            for (ChatRoomRoute item : wssList) {
                if (item.session.getId().equals(session.getId())) {
                    wssList.remove(item);
                    if (wssList.isEmpty()) {
                        webSocketMap.remove(roomId);
                    }
                    break;
                }
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //群发消息
        String msg = filterMessage(message);
        newMessage.date = sdf.format(new Date());
        newMessage.message = message;
        if (msg != null) {
            sendInfo(JSON.toJSONString(sqlMessage.insert(newMessage, roomId)), this);
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    private String filterMessage(String message) {
        if (message == null || message.isEmpty()) return null;
        if ("undefined".equals(message)) return null;
        return message;
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, ChatRoomRoute previousRoom) {
        if (previousRoom.roomId == null || previousRoom.roomId.isEmpty() || previousRoom.session == null) return;
        List<ChatRoomRoute> wssList = webSocketMap.get(previousRoom.roomId);
        for (ChatRoomRoute item : wssList) {
            if (previousRoom.sender != item.sender) {
                item.sendMessage(message);
            }
        }
    }

    public static synchronized void addSocketServer2Map(ChatRoomRoute wss) {
        if (wss != null) {
            List<ChatRoomRoute> wssList = webSocketMap.computeIfAbsent(wss.roomId, k -> new ArrayList<>(6));
            wssList.add(wss);
        }
    }

    public void initParams(String params) {
        for (String item: params.split("&")) {
            String[] itemArr = item.split("=");
            switch (itemArr[0]) {
                case "sender":
                    sender = Integer.parseInt(itemArr[1]);
                    break;
                case "receiver":
                    receiver = Integer.parseInt(itemArr[1]);
                    break;
            }
        }
        newMessage.sender = sender;
        newMessage.receiver = receiver;
        roomId = sender > receiver ? receiver + "&" + sender : sender + "&" + receiver;
    }

    /**
     * 实现服务器主动发送消息
     */
    public void sendMessage(String message) {
        try {
            this.session.getAsyncRemote().sendText(message);
        }
        catch (Exception e) {
            this.session.getAsyncRemote().sendText(JSON.toJSONString(molResponse.fail("发送失败")));
        }
    }

}

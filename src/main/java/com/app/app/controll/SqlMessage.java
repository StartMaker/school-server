package com.app.app.controll;

import com.app.app.form.chat.Message;
import com.app.app.map.MapMessage;
import com.app.app.model.MolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SqlMessage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MolResponse molResponse;

    public MolResponse findForUnRead (String roomId) {
        String sql = "SELECT `date`, `message`, `read`, `sender`, `receiver` FROM `message` WHERE `roomId` = ? and `read` = false ORDER BY `date`;";
        try {
            return molResponse.success(jdbcTemplate.query(sql, new MapMessage(), roomId));
        }
        catch (DataAccessException e) {
            System.out.println(e);
            return molResponse.fail(null);
        }
    }

    public MolResponse findForDate (String roomId, String date) {
        String sql = "select * from `message` where `date`<? and `roomId`=? order by `date` limit 0,20";
        try {
            return molResponse.success(jdbcTemplate.query(sql, new MapMessage(), date, roomId));
        }
        catch (DataAccessException e) {
            return molResponse.fail("发送失败");
        }
    }

    public MolResponse insert(Message message, String roomId) {
        String sql = "insert into `message` (`sender`, `receiver`, `roomId`, `message`, `date`, `roomId`) values (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, message.sender, message.receiver, roomId, message.message, message.date);
            return molResponse.success(null);
        }
        catch (DataAccessException e) {
            return molResponse.fail("发送失败");
        }
    }
}

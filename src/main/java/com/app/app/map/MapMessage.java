package com.app.app.map;
import com.app.app.form.chat.Message;
import com.app.app.model.MolAccount;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class MapMessage implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        Message message = new Message();
        message.setDate(resultSet.getString("date"));
        message.setMessage(resultSet.getString("message"));
        message.setRead(resultSet.getBoolean("read"));
        message.setReceiver(resultSet.getInt("receiver"));
        message.setSender(resultSet.getInt("sender"));
        return message;
    }
}

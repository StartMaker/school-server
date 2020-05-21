package com.app.app.controll;

import com.app.app.form.chat.Message;
import com.app.app.map.MapMessage;
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

    public List<Message> findForUnRead (String roomId) {
        String sql = "SELECT `date`, `message`, `read`, `sender`, `receiver` FROM `message` WHERE `roomId` = ? and `read` = false ORDER BY `date`;";
        try {
            return jdbcTemplate.query(sql, new MapMessage(),roomId);
        }
        catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Map <String, Object>> findForDate (String roomId, String date) {
        String sql = "select * from `message` where `date`<? and `roomId`=? order by `date` limit 0,20";
        try {
            return jdbcTemplate.queryForList(sql, date, roomId);
        }
        catch (DataAccessException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean insert(Message message) {
        String sql = "insert into `message` (`sender`, `receiver`, `roomId`, `message`, `date`) values (?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, message.sender, message.receiver);
            return true;
        }
        catch (DataAccessException e) {
            System.out.println(e);
            return false;
        }
    }
}

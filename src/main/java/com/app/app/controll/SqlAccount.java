package com.app.app.controll;

import com.app.app.form.account.Params;
import com.app.app.model.MolAccount;
import com.app.app.model.MolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlAccount {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private MolResponse molResponse = new MolResponse();

    public MolAccount find(String name, String password) {
        String sql = "select * from account where name=? and password=?";
        try {
            return (MolAccount) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(MolAccount.class), name, password);
        }
        catch (DataAccessException e) {
            return null;
        }
    }

    public MolResponse insert(Params params) {
        String sql = "insert into `account` (`name`, `password`, `role`, `phone`) values (?, ?, ?, ?);";
        try{
            jdbcTemplate.update(sql, params.name, params.password, params.role, params.phone);
            return molResponse.success(null);
        }
        catch (DataAccessException e) {
            if (e.getCause().getLocalizedMessage().indexOf("name_UNIQUE") > 0) {
                return molResponse.fail("账号名已存在");
            }
            else if (e.getCause().getLocalizedMessage().indexOf("phone_UNIQUE") > 0) {
                return molResponse.fail("电话号码已存在");
            }
            return molResponse.fail("error");
        }
    }

    public MolResponse update(Params params) {
        String sql = "UPDATE `account` SET `name` = ?, `password` = ?, `role` = ?, `phone` = ? WHERE ( `id` = ? )";
        try {
            boolean bolUpdate = jdbcTemplate.update(sql, params.name, params.password, params.role, params.phone, params.id) > 0;
            if (bolUpdate) {
                return molResponse.success(null);
            }
            else {
                return molResponse.fail("找不到id");
            }

        }
        catch (DataAccessException e) {
            if (e.getCause().getLocalizedMessage().indexOf("name_UNIQUE") > 0) {
                return molResponse.fail("账号名已存在");
            }
            else if (e.getCause().getLocalizedMessage().indexOf("phone_UNIQUE") > 0) {
                return molResponse.fail("电话号码已存在");
            }
            return molResponse.fail("error");
        }
    }
}

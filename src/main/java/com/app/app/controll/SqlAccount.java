package com.app.app.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication

public class SqlAccount {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void p() throws Exception {
        System.out.println(jdbcTemplate.queryForList("SELECT * FROM account;"));
        System.out.println(111);
        List <Map<String, Object>> result = jdbcTemplate.queryForList("select * from graduation_project.account;");


    }
}

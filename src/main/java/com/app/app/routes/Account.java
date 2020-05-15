package com.app.app.routes;

import com.alibaba.fastjson.JSON;
import com.app.app.controll.SqlAccount;
import com.app.app.model.Response.GiveClient;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
public class Account {

    @GetMapping("/login")
    String login() throws Exception {
        GiveClient giveClient = new GiveClient();
        try  {
            SqlAccount sqlAccount = new SqlAccount();
            sqlAccount.p();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return JSON.toJSONString(giveClient);
    }

    @PostMapping("/register")
    String register() {
        GiveClient giveClient = new GiveClient();

        return "JSON.toJSONString()";
    }
}

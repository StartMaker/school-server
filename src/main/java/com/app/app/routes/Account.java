package com.app.app.routes;

import com.alibaba.fastjson.JSON;
import com.app.app.model.Response.GiveClient;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
public class Account {

    @GetMapping("/login")
    String login() {
        GiveClient giveClient = new GiveClient();
        return JSON.toJSONString(giveClient);
    }
}

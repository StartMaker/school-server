package com.app.app.routes;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class Account {

    @PostMapping("/login")
    String login() {
        return "Hello World!";
    }

    @GetMapping("/home")
    String home1() {
        return "hello word";
    }

    public static void main(String[] args) {
        SpringApplication.run(Account.class, args);
    }

}

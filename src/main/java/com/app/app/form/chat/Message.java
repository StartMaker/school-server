package com.app.app.form.chat;

import lombok.Data;

@Data
public class Message {
    int receiver;
    int sender;
    String message = "";
    String date = "";
}

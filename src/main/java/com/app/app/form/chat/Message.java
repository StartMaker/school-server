package com.app.app.form.chat;

import lombok.Data;

@Data
public class Message {
    public int receiver;
    public int sender;
    public String message;
    public String date;
    public boolean read = false;
    public String roomId;
}

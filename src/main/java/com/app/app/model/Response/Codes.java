package com.app.app.model.Response;

public class Codes {
    int code;
    String msg;

    public void status_1 () {
        this.code = 1;
        this.msg = "权限不足";
    }
}

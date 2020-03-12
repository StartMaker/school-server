package com.app.app.model.Response;

public class ResponseBody<T> {
    int code = 0;
    T data;
    String msg = "成功";

    private ResponseBody (T data) {
        this.data = data;
    }

    private ResponseBody (int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setStatus(Codes codes) {
        this.msg = codes.msg;
        this.code = codes.code;
    }
}

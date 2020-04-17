package com.app.app.model.Response;

import lombok.Data;

@Data
public class GiveClient {
    int code = 0;
    String msg = "请求成功";
    Object data = null;

    public void success () {
        this.code = 0;
        this.msg = "请求成功";
    }

    public void fail (String msg) {
        this.code = 1;
        this.msg = msg;
        this.data = null;
    }

    public void tokenError (String msg) {
        this.code = 2;
        this.msg = msg;
        this.data = null;
    }

    public void setData (Object data) {
        this.data = data;
    }
}

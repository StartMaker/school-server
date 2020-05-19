package com.app.app.model;

import lombok.Data;

@Data
public class MolResponse {
    int code = 0;
    String msg = "请求成功";
    Object data = null;

    public MolResponse success (Object data) {
        this.code = 0;
        this.msg = "请求成功";
        this.data = data;
        return this;
    }

    public MolResponse fail (String msg) {
        this.code = 1;
        this.msg = msg;
        this.data = null;
        return this;
    }

    public MolResponse tokenError (String msg) {
        this.code = 2;
        this.msg = msg;
        this.data = null;
        return this;
    }
}

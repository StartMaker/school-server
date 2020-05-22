package com.app.app.form.chat;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Params {

    @NotNull(message = "请上传日期")
    public String date;

    @NotNull(message = "请上传房间号")
    public String roomId;
}

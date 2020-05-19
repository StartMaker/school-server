package com.app.app.form.account;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class LoginParams {

    @NotNull(message = "请输入账号")
    public String name;

    @NotNull(message = "请输入密码")
    public String password;
}

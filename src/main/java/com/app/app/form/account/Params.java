package com.app.app.form.account;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class Params {

    @NotNull(message = "请输入账号")
    public String name;

    @NotNull(message = "请输入密码")
    public String password;

    @NotNull(message = "请输入电话")
//    @Pattern(regexp = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\\\d{8}$", message = "电话格式不正确")
    public String phone;

    @NotNull(message = "请设置用户权限")
    public String role;

    public int id;
}

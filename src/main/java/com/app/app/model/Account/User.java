package com.app.app.model.Account;

import lombok.Data;

@Data
public class User {
    String name;
    int id;
    String password;
    String phone;
    int role;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword () {
        return this.password;
    }
}

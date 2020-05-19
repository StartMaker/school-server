package com.app.app.routes;

import com.app.app.controll.SqlAccount;
import com.app.app.form.account.LoginParams;
import com.app.app.form.account.Params;
import com.app.app.model.MolAccount;
import com.app.app.model.MolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/account")
@ResponseBody
public class Account {

    @Autowired
    private SqlAccount sqlAccount;
    private MolResponse molResponse = new MolResponse();

    @PostMapping("/login")
    MolResponse login(@RequestBody @Valid LoginParams loginParams) throws Exception {

        MolAccount molAccount = sqlAccount.find(loginParams.name, loginParams.password);
        if (molAccount == null) {
            return molResponse.fail("账号不存在或密码不正确");
        }
        return molResponse.success(molAccount);
    }

    @PostMapping("/register")
    MolResponse register(@RequestBody @Valid Params params) throws Exception {
        return sqlAccount.insert(params);
    }

    @PostMapping("/update")
    MolResponse update(@RequestBody @Valid Params params) throws Exception {
        return sqlAccount.update(params);
    }
}

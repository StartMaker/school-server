package com.app.app.routes;

import com.app.app.controll.SqlMessage;
import com.app.app.form.chat.Params;
import com.app.app.model.MolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/message")
@ResponseBody
public class MessageRoute {

    @Autowired
    private SqlMessage sqlMessage;

    @GetMapping("/getByDate")
    public MolResponse getByDate (@RequestBody @Valid Params params) throws Exception {
        return sqlMessage.findForDate(params.roomId, params.date);
    }
}

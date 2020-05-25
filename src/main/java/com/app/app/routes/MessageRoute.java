package com.app.app.routes;

import com.app.app.controll.SqlMessage;
import com.app.app.model.MolResponse;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/message")
@ResponseBody
public class MessageRoute {

    @Autowired
    private SqlMessage sqlMessage;

    @GetMapping("/getByDate")
    public MolResponse getByDate (
            @RequestParam("roomId") String roomId,
            @RequestParam("date") String date
    ) throws Exception {
        System.out.println(StandardTokenizer.segment("李的鹏的生日是多久"));
        return sqlMessage.findForDate(roomId, date);
    }
}

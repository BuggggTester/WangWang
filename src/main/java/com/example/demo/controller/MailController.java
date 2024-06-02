package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    MailUtil mailUtil;

    //读取yml文件中username的值并赋值给form
    @Value("${spring.mail.username}")
    private String from;

    @RequestMapping("/sendEmail")
    public Object sendSimpleMail(@RequestParam(value = "emailReceiver") String emailReceiver) {
        JSONObject jsonObject = new JSONObject();
        String verifyCode = mailUtil.sendMail(emailReceiver);
        jsonObject.put("verifyCode", verifyCode);
        System.out.println(emailReceiver +":"+verifyCode);
        return jsonObject;
    }
}

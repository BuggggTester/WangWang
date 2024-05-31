package com.example.demo.controller;

import com.example.demo.utils.MailUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
@CrossOrigin
@Slf4j
@Controller
@RestController

public class MailController {

    @Autowired
    private MailUtil mailUtil;

//    @Resource
//    private TemplateEngine templateEngine;

    /**
     * 测试一个简单的、由自己撰写邮件标题和内容的邮件
     **/
    @RequestMapping("mail01")
    public String sendSimpleMail() {
        mailUtil.sendSimpleMail("912948333@qq.com","测试spring boot mail-主题","测试spring boot mail - 内容");
        return "发送成功";
    }

    /**
     * 测试一个简单的html邮件
     **/
    @RequestMapping("mail02")
    public String sendHtmlMail() throws MessagingException {

        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<body>\n" +
                "</html>\n";

        mailUtil.sendHtmlMail("liuxing121380110@163.com","hello world邮件",content);
        return "发送成功";
    }

    /**
     * 测试文档之类附件传输的邮件
     **/
    @RequestMapping("mail03")
    public String sendAttachmentsMail() throws MessagingException {
        String filePath = "src/main/resources/static/redis.docx";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<h1>附件传输</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailUtil.sendAttachmentsMail("liuxing121380110@163.com","附件传输",content, filePath);
        return "发送成功";
    }

    /**
     * 测试图片之类的、且显示在邮件内容上的邮件
     **/
    @RequestMapping("mail04")
    public String sendInlinkResourceMail() throws MessagingException {
        String imgPath = "src/main/resources/static/test01.png";
        String rscId = "test";
        String content = "<html>" +
                "<body>" +
                "<h3>hello world</h3>" +
                "<h1>html</h1>" +
                "<h1>图片邮件</h1>" +
                "<img src='cid:"+rscId+"'></img>" +
                "<body>" +
                "</html>";

        mailUtil.sendInlinkResourceMail("liuxing121380110@163.com","这是一封图片邮件",content, imgPath, rscId);
        return "发送成功";
    }

//    /**
//     * 测试一个HTML模板的邮件
//     **/
//    @RequestMapping("mail05")
//    public String testTemplateMailTest() throws MessagingException {
//        Context context = new Context();
//        context.setVariable("id","liuxing121380110");
//
//        String emailContent = templateEngine.process("emailTemplate", context);
//        mailUtil.sendHtmlMail("liuxing121380110@163.com","这是一封HTML模板邮件",emailContent);
//        return "发送成功";
//
//    }
}

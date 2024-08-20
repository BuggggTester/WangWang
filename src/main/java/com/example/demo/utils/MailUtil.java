package com.example.demo.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public String sendMail(String to) {
        String verifyCode = "123456";
        MimeMessagePreparator msg = mimeMessage -> {
            MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage);
            msgHelper.setTo(to);
            msgHelper.setText("this is an email");
            msgHelper.setFrom("tpzzz");
        };
        mailSender.send(msg);
        return verifyCode;
    }
}
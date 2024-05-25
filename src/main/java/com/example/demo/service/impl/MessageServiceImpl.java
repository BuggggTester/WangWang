package com.example.demo.service.impl;

import com.example.demo.entity.Message;
import com.example.demo.dao.MessageMapper;
import org.bouncycastle.asn1.x509.Time;
import com.example.demo.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class MessageServiceImpl {
    private MessageMapper messageMapper;
    void createMessage(String send, int receive, String title, String body, LocalDate send_date, LocalTime send_time){
        messageMapper.createMessage(send, receive, title, body, send_date, send_time);
    }

    List<Message> selectMessage(int receive){
        return messageMapper.selectMessage(receive);
    }

    int selectNumberOfMessage(int receive){
        return messageMapper.selectNumberOfMessage(receive);
    }

    int selectNumberOfUnreadMessage(int receive){
        return messageMapper.selectNumberOfUnreadMessage(receive);
    }

    List<Message> selectUnreadMessage(int receive){
        return messageMapper.selectUnreadMessage(receive);
    }

    List<Message> selectMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectMessageByDate(receive, send_date);
    }

    List<Message> selectUnreadMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectUnreadMessageByDate(receive, send_date);
    }

    void setRead(int message_id){
        messageMapper.setRead(message_id);
    }
}

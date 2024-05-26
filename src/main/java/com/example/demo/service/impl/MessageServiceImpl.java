package com.example.demo.service.impl;

import com.example.demo.entity.Message;
import com.example.demo.dao.MessageMapper;
import org.bouncycastle.asn1.x509.Time;
import com.example.demo.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Service("messageService")
public class MessageServiceImpl implements MessageService{
    private MessageMapper messageMapper;
    public void createMessage(String send, int receive, String title, String body, LocalDate send_date, LocalTime send_time){
        messageMapper.createMessage(send, receive, title, body, send_date, send_time);
    }

    public List<Message> selectMessage(int receive){
        return messageMapper.selectMessage(receive);
    }

    public int selectNumberOfMessage(int receive){
        return messageMapper.selectNumberOfMessage(receive);
    }

    public int selectNumberOfUnreadMessage(int receive){
        return messageMapper.selectNumberOfUnreadMessage(receive);
    }

    public List<Message> selectUnreadMessage(int receive){
        return messageMapper.selectUnreadMessage(receive);
    }

    public List<Message> selectMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectMessageByDate(receive, send_date);
    }

    public List<Message> selectUnreadMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectUnreadMessageByDate(receive, send_date);
    }

    public void setRead(int message_id){
        messageMapper.setRead(message_id);
    }
}

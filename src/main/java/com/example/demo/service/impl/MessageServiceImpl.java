package com.example.demo.service.impl;

import com.example.demo.entity.Message;
import com.example.demo.dao.MessageMapper;
import com.example.demo.service.MessageService;
import org.bouncycastle.asn1.x509.Time;
import com.example.demo.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Service("messageService")
public class MessageServiceImpl implements MessageService{
    @Resource
    private MessageMapper messageMapper;
    @Override
    public void createMessage(String send, int receive, String title, String body, LocalDate send_date, LocalTime send_time){
        messageMapper.createMessage(send, receive, title, body, send_date, send_time);
    }
    @Override
    public List<Message> selectMessage(int receive){
        return messageMapper.selectMessage(receive);
    }
    @Override
    public int selectNumberOfMessage(int receive){
        return messageMapper.selectNumberOfMessage(receive);
    }
    @Override
    public int selectNumberOfUnreadMessage(int receive){
        return messageMapper.selectNumberOfUnreadMessage(receive);
    }
    @Override
    public List<Message> selectUnreadMessage(int receive){
        return messageMapper.selectUnreadMessage(receive);
    }
    @Override
    public List<Message> selectMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectMessageByDate(receive, send_date);
    }
    @Override
    public List<Message> selectUnreadMessageByDate(int receive, LocalDate send_date){
        return messageMapper.selectUnreadMessageByDate(receive, send_date);
    }
    @Override
    public void setRead(int message_id){
        messageMapper.setRead(message_id);
    }
}

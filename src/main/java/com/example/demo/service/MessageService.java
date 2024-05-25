package com.example.demo.service;

import com.example.demo.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.bouncycastle.asn1.x509.Time;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Component
public interface MessageService {
    void createMessage(String send, int receive, String title, String body, LocalDate send_date, LocalTime send_time);

    List<Message> selectMessage(int receive);

    int selectNumberOfMessage(int receive);

    int selectNumberOfUnreadMessage(int receive);

    List<Message> selectUnreadMessage(int receive);

    List<Message> selectMessageByDate(int receive, LocalDate send_date);

    List<Message> selectUnreadMessageByDate(int receive, LocalDate send_date);

    void setRead(int message_id);

}

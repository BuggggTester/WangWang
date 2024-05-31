package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Slf4j
@Component
public class Message {
    private int message_id;
    private String send;
    private int receive;
    private String title;
    private String body;
    private Boolean ifread;
    private LocalDate send_date;
    private LocalTime send_time;
}

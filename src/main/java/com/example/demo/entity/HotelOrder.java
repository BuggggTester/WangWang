package com.example.demo.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Component
@Slf4j
public class HotelOrder {
    private String order_id;
    private Date order_time;
    private int user_id;
    private String  state;
    private double payment;
    private int h_id;
    private int r_id;
    private Date book_time;
    private Timestamp payTime;
    private String payway;
    @ManyToOne
    @JoinColumn(name = "h_id", referencedColumnName = "h_id")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "r_id", referencedColumnName = "r_id")
    private Room room;
}

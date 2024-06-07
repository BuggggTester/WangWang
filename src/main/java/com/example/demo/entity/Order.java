package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Component
@Slf4j
public class Order {
    private int order_id;
    private Date order_time;
    private int user_id;
    private String type;
    private String  state;
    private double payment;
    private int trip_id;
    private int carriage;
    private int row;
    private char seat;
    private Timestamp payTime;
    private String payway;
    private String from_place;
    private String to_place;
    private String seat_type;
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;

}

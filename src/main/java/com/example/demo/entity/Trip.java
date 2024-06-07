package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Data
@Component
public class Trip {
    private int trip_id;
    private String train_id;
    private Timestamp start_time;
    private Timestamp end_time;
    private String from_place;
    private String to_place;
    private int num_car;
    private int num_row;
    private String trip_chain;
    private String first_price_chain;
    private String second_price_chain;
    private String time_chain;
}
package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

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
}
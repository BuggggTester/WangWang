package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class Travel {
    private String photo;
    private String title;
    private String description;
    private double price;
    private int days;
    private int travel_id;
    private boolean state;
}

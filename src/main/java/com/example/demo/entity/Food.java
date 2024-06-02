package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class Food {
    private int food_id;
    private String food_name;
    private double price;
    private int trip_id;
    private String image;
}

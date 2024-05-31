package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class Hotel {
    private int h_id;
    private String name;
    private String address;
    private String tel;
    private float rating;
    private String script;
}

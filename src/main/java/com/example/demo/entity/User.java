package com.example.demo.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class User {
    private String password;
    private String user_name;
    private int user_id;
    private String identity;
    private String avatar;
    private String email;
    private double money;
}

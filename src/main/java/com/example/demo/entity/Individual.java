package com.example.demo.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class Individual {
    private int iid;
    private String identity;
    private String phone_number;
    private String name;
    private int user_id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;
}

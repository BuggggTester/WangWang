package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Entity
@Data
@Slf4j
@Component
@Table(name = "users") // 指定数据库表名为 "users"
public class User {
    private String password;
    private String user_name;
    @Id
    private int user_id;
    private String identity;
    private String avatar;
    private String email;
    private double money;
}

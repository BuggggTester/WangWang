package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    void createCustomer(int age, String password, String userName);
    User selectUserByName(String name);
    User selectAdmin(String userName);
    void updateAvatarById(String avatar, int userId);
    User selectUserById(int userId);
}

package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void createCustomer(int age, String password, String userName) {
        userMapper.createCustomer(age, password, userName);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public User selectAdmin(String userName) {
        return userMapper.selectAdmin(userName);
    }

    @Override
    public void updateAvatarById(String avatar, int userId) {
        userMapper.updateAvatarById(avatar,userId);
    }

    @Override
    public User selectUserById(int userId) {
        return userMapper.selectUserById(userId);
    }
}

package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into users (age, password, user_name, identity) values (#{age}, #{password},#{userName},'customer')")
    void createCustomer(int age, String password, String userName);
    @Select("select * from users where user_name = #{userName} limit 1")
    User selectUserByName(String userName);
    @Select("select * from users where user_name = #{userName} and identity = 'administrator' limit 1")
    User selectAdmin(String userName);
    @Update("update users set avatar = #{avatar} where user_name = #{userName}")
    void updateAvatarByName(String avatar, String userName);
    @Select("select * from users where user_id = #{userId} limit 1")
    User selectUserById(int userId);
}

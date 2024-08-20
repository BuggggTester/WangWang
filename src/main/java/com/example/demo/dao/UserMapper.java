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
    @Update("update users set avatar = #{avatar} where user_id = #{userId}")
    void updateAvatarById(String avatar, int userId);
    @Select("select * from users where user_id = #{userId} limit 1")
    User selectUserById(int userId);
    @Update("update users set email = #{email} where user_id = #{userId}")
    void setEmailById(int userId, String email);
    @Select("select count(*) from users where user_id = #{userId} and user_name = #{userName} and password = #{password}")
    int validateIdentity(int userId, String userName, String password);
    @Update("update users set password = #{password} where user_id = #{userId}")
    void updatePassword(String password, int userId);
    @Update("update users set money = money + #{payment} where user_id = #{userId}")
    void updateMoneyById(double payment, int userId);
}

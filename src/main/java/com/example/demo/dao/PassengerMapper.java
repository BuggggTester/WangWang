package com.example.demo.dao;

import com.example.demo.entity.Passenger;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PassengerMapper {
    @Select("select * from passengers where user_id = #{userId}")
    List<Passenger> selectPassengersById(int userId);
    @Insert("insert into passengers (identity, phone_number, name, user_id) VALUES (#{identity}, #{phoneNum}, #{name},#{userId})")
    void createPassenger(String identity,String phoneNum, String name, int userId);
}

package com.example.demo.dao;

import com.example.demo.entity.Passenger;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PassengerMapper {
    @Select("select * from passengers where user_id = #{userId}")
    List<Passenger> selectPassengersById(int userId);
    @Insert("insert into passengers (identity, phone_number, name, user_id) VALUES (#{identity}, #{phoneNum}, #{name},#{userId})")
    void createPassenger(String identity,String phoneNum, String name, int userId);
    @Update("update passengers set identity = #{identity} , phone_number = #{phone} , name =#{name} where pid = #{pid}")
    void updatePassenger(String identity, String phone, String name, int pid);
    @Select("select * from passengers where identity = #{identity} limit 1")
    Passenger selectPassengerByIdentity(String identity);
    @Select("select * from passengers where pid = #{pid} limit 1")
    Passenger selectPassengerById(int pid);
}

package com.example.demo.dao;

import com.example.demo.entity.Individual;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IndividualMapper {
    @Select("select * from individuals where user_id = #{userId}")
    List<Individual> selectIndividualsById(int userId);
    @Insert("insert into individuals (identity, phone_number, name, user_id) VALUES (#{identity}, #{phoneNum}, #{name},#{userId})")
    void createIndividual(String identity,String phoneNum, String name, int userId);
}

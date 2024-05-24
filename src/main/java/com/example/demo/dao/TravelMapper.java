package com.example.demo.dao;

import com.example.demo.entity.Travel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TravelMapper {
    @Insert("insert into travels(photo, title, description, price, days, state) VALUES (#{photo},#{title}, #{description}," +
            " #{price},#{days}, 1)")
    void createTravel(String photo, String title, String description, double price, int days);

    @Select("select * from travels where travel_id = #{travelId} limit 1")
    Travel selectTravelById(int travelId);
    @Update("update travels set photo = #{photo}, title = #{title}, description = #{description}, price = #{price}," +
            "days=#{days} where travel_id = #{travelId}")
    void updateTravelById(String photo ,String title, String description, double price, int days, int travelId);
    @Select("select * from travels where state = 1")
    List<Travel> selectAllTravels();
}

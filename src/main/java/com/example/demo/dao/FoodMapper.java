package com.example.demo.dao;

import com.example.demo.entity.food.Food;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from foods where trip_id = #{tripId}")
    List<Food> selectFoodsByTripId(int tripId);
    @Insert("insert into foods (food_name, price, trip_id, image) VALUES (" +
            "#{foodName}, #{price}, #{tripId}, #{image})")
    void createFood(String foodName, double price, int tripId, String image);
    @Update("update foods set image = #{image} where food_id = #{foodId}")
    void uploadFoodImage(String image, int foodId);

    @Insert("INSERT INTO food_reservation (food_id, trip_id, user_id, quantity) " +
            "VALUES (#{foodId}, #{tripId}, #{userId}, #{quantity})")
    void buyFood(int foodId, int tripId, int userId, int quantity);
}

package com.example.demo.dao;

import com.example.demo.entity.food.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from food where trip_id = #{tripId}")
    List<Food> selectFoodsByTripId(int tripId);

    @Insert("INSERT INTO food_reservation (food_id, trip_id, user_id, quantity) " +
            "VALUES (#{foodId}, #{tripId}, #{userId}, #{quantity})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reservationId", before=false, resultType=int.class)
    int buyFood(int foodId, int tripId, int userId, int quantity);

    @Delete("DELETE FROM food_reservation WHERE id = #{reservationId}")
    void cancelFood(@Param("reservationId") int reservationID);
}

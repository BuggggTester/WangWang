package com.example.demo.service;

import com.example.demo.entity.food.Food;
import com.example.demo.entity.food.FoodReservation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoodService {
    List<Food> selectFoodsByTripId(int tripId);
    void uploadFoodImage(String image, int foodId);

    FoodReservation selectFoodReservationById(int reservation_id);

    void buyFood(FoodReservation foodReservation);
    void createFood(String foodName, double price, int tripId, String image);
    List<Food> selectFoodByTripId(int trip_id);

//    void cancelFood(int restaurantId);
}

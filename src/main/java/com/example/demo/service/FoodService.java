package com.example.demo.service;

import com.example.demo.entity.food.Food;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoodService {
    List<Food> selectFoodsByTripId(int tripId);


    int buyFood(int foodId, int tripId, int userId, int quantity);

    void cancelFood(int restaurantId);
}

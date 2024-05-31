package com.example.demo.service;

import com.example.demo.entity.Food;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoodService {
    List<Food> selectFoodsByTripId(int tripId);

    void createFood(String foodName, double price, int tripId, String image);
    void uploadFoodImage(String image, int foodId);
}

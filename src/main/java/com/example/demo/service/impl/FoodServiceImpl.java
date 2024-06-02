package com.example.demo.service.impl;

import com.example.demo.dao.FoodMapper;
import com.example.demo.entity.food.Food;
import com.example.demo.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService {
    @Resource
    private FoodMapper foodMapper;
    @Override
    public List<Food> selectFoodsByTripId(int tripId) {
        return foodMapper.selectFoodsByTripId(tripId);
    }

    @Override
    public void createFood(String foodName, double price, int tripId, String image) {
        foodMapper.createFood(foodName, price, tripId, image);
    }

    @Override
    public void uploadFoodImage(String image, int foodId) {
        foodMapper.uploadFoodImage(image, foodId);
    }

    @Override
    public void buyFood(int foodId, int tripId, int userId, int quantity) {
        foodMapper.buyFood(foodId, tripId, userId, quantity);
    }
}

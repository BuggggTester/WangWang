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
    public int buyFood(int foodId, int tripId, int userId, int quantity) {
        return foodMapper.buyFood(foodId, tripId, userId, quantity);
    }

    @Override
    public void cancelFood(int reservationID) {
        foodMapper.cancelFood(reservationID);
    }
}

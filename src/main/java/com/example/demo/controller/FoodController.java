package com.example.demo.controller;

import com.example.demo.entity.food.Food;
import com.example.demo.entity.R;
import com.example.demo.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value ="/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping(value="/select/tripId")
    public List<Food> selectFoodsByTripId(@RequestParam("tripId")int tripId) {
        return foodService.selectFoodsByTripId(tripId);
    }

    @PostMapping("/buy")
    public R buyFood(@RequestBody Map<String, String> requestParams) {
        try {
            int foodId = Integer.parseInt(requestParams.get("foodId"));
            int tripId = Integer.parseInt(requestParams.get("tripId"));
            int userId = Integer.parseInt(requestParams.get("userId"));
            int quantity = Integer.parseInt(requestParams.get("quantity"));

            int result = foodService.buyFood(foodId, tripId, userId, quantity);
            Map<String, Object> map = new HashMap<>();
            map.put("result", result);
            return R.ok(map);
        } catch (NumberFormatException e) {
            return R.error("Failed to parse number: " + e.getMessage());
        } catch (Exception e) {
            return R.error("Failed to buy food: " + e.toString());
        }
    }

    @PostMapping("/cancel/{restaurantId}")
    public R cancelFood(@PathVariable int restaurantId) {
        try {
            foodService.cancelFood(restaurantId);
            return R.ok("Food order cancelled successfully!");
        } catch (Exception e) {
            return R.error("Failed to cancel food order: " + e.toString());
        }
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Food;
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
import java.util.Objects;
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
        List<Food> foodList = foodService.selectFoodsByTripId(tripId);
        return foodList;
    }
    @RequestMapping(value = "/create")
    public R createFood(@RequestParam("File") MultipartFile file, @RequestParam("foodName") String foodName, @RequestParam("price")double price,@RequestParam("tripId")int tripId) {
        String filePath = "./src/main/resources/static/images/foods/";
        String dataPath = "images/foods/";
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileNewName = UUID.randomUUID() + fileType;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileNewName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            foodService.createFood(foodName, price, tripId, dataPath+fileNewName);
        }catch (Exception e) {
            return R.error(e.toString());
        }
        return R.ok("创建成功！");
    }
    @PostMapping(value="/upload/image")
    public R uploadFile(@RequestParam("File") MultipartFile file) {
        String filePath = "./src/main/resources/static/images/foods";
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileNewName = UUID.randomUUID() + fileType;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileNewName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return R.error(e.toString());
        }
        return R.ok("上传成功！");
    }
}

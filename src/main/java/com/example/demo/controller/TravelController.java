package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.Travel;
import com.example.demo.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/travel")
public class TravelController {
    @Autowired
    private TravelService travelService;
    @RequestMapping(value="/create")
    public R createTravel(@RequestBody HashMap<String, String> travelMap) {
        String photo = travelMap.get("photo");
        String title = travelMap.get("title");
        String description = travelMap.get("description");
        double price = Double.parseDouble(travelMap.get("price"));
        int days = Integer.parseInt(travelMap.get("days"));
        //验证图片路径
        if(!photo.startsWith("@/assets/images/suggestTrips")) {
            return R.error("图片路径错误！");
        }
        //验证旅游天数
        if(days>100) {
            return R.error("天数过长！");
        }
        if(days<0) {
            return R.error("天数需大于0！");
        }
        try{
            travelService.createTravel(photo, title, description, price,days);
            return R.ok("创建成功！");
        }catch(Exception e){
            return R.error(e.toString());
        }
    }
    @RequestMapping(value = "/modify")
    public R modifyTravel(@RequestBody HashMap<String, String> travelMap) {
        String photo = travelMap.get("photo");
        String title = travelMap.get("title");
        String description = travelMap.get("description");
        double price = Double.parseDouble(travelMap.get("price"));
        int days = Integer.parseInt(travelMap.get("days"));
        int travelId = Integer.parseInt(travelMap.get("travel_id"));
        //此处加入验证
        if(0==1) {
            return R.error("error!");
        }
        try{
            travelService.updateTravelById(photo, title, description, price,days, travelId);
            return R.ok("修改成功！");
        }catch(Exception e){
            return R.error(e.toString());
        }
    }
    @RequestMapping(value = "/select")
    public R selectTravel(@RequestParam("travelId") int travelId) {
        try {
            Travel travel = travelService.selectTravelById(travelId);
            Map<String, Object> res = new HashMap<>();
            res.put("photo",travel.getPhoto());
            res.put("title",travel.getTitle());
            res.put("description",travel.getDescription());
            res.put("price",travel.getPrice());
            res.put("days",travel.getDays());
            res.put("travel_id",travel.getTravel_id());
            return R.ok(res);
        }catch (Exception e) {
            return R.error(e.toString());
        }
    }
    @RequestMapping(value="/selectall")
    public List<Travel> selectAllTravels() {
        try{
            return travelService.selectAllTravels();
        }catch (Exception e){
            return null;
        }
    }
}

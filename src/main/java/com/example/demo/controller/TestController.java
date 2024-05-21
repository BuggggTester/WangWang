package com.example.demo.controller;


import com.example.demo.entity.R;
import com.example.demo.entity.Trip;
import com.example.demo.service.OrderService;
import com.example.demo.service.TripService;
import com.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TripService tripService;
    @RequestMapping("/login")
    public R login(){
        String token = JwtUtils.genJwtToken("java1234");
        return R.ok().put("token",token);
    }
    @GetMapping("/{tripId}")
    public Trip test(@PathVariable int tripId) {
        return tripService.selectTripById(tripId);
    }
}


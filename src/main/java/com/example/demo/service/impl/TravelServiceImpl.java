package com.example.demo.service.impl;

import com.example.demo.dao.TravelMapper;
import com.example.demo.entity.Travel;
import com.example.demo.service.TravelService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("travelService")
public class TravelServiceImpl implements TravelService {
    @Resource
    private TravelMapper travelMapper;
    @Override
    public void createTravel(String photo, String title, String description, double price, int days) {
        travelMapper.createTravel(photo, title, description, price, days);
    }

    @Override
    public Travel selectTravelById(int travelId) {
        return travelMapper.selectTravelById(travelId);
    }

    @Override
    public void updateTravelById(String photo, String title, String description, double price, int days, int travelId) {
        travelMapper.updateTravelById(photo, title, description, price, days, travelId);
    }

    @Override
    public List<Travel> selectAllTravels() {
        return travelMapper.selectAllTravels();
    }
}

package com.example.demo.service;

import com.example.demo.entity.Travel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TravelService {
    void createTravel(String photo, String title, String description, double price, int days);
    Travel selectTravelById(int travelId);
    void updateTravelById(String photo ,String title, String description, double price, int days, int travelId);
    List<Travel> selectAllTravels();
}

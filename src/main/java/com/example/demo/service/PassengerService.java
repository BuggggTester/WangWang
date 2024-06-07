package com.example.demo.service;

import com.example.demo.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PassengerService {
    List<Passenger> selectPassengersById(int userId);

    void createPassenger(String identity,String phoneNum, String name, int userId);
}

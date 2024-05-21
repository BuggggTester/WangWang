package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.R;
import com.example.demo.entity.Trip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public interface TripService {
    List<Trip> selectAllTrips();
    Trip selectTripById(int tripId);
    void createTrip(String trainId, Timestamp startTime, Timestamp endTime, String fromPlace, String toPlace, int numCar, int numRow);
    List<Trip> selectTripByPlaceAndTime(String fromPlace, String toPlace, Timestamp startTime);
}

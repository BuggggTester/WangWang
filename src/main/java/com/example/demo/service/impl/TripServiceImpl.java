package com.example.demo.service.impl;

import com.example.demo.dao.TripMapper;
import com.example.demo.entity.Trip;
import com.example.demo.service.TripService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
@Service("tripService")
public class TripServiceImpl implements TripService {
    @Resource
    private TripMapper tripMapper;
    @Override
    public List<Trip> selectAllTrips() {
        return tripMapper.selectAllTrips();
    }

    @Override
    public Trip selectTripById(int tripId) {
        return tripMapper.selectTripById(tripId);
    }

    @Override
    public void createTrip(String trainId, Timestamp startTime, Timestamp endTime, String fromPlace, String toPlace, int numCar, int numRow) {
        tripMapper.createTrip(trainId, startTime, endTime, fromPlace, toPlace, numCar, numRow);
    }

    @Override
    public List<Trip> selectTripByPlaceAndTime(String fromPlace, String toPlace, Timestamp startTime) {
        return tripMapper.selectTripByPlaceAndTime(fromPlace, toPlace, startTime);
    }

    @Override
    public int countSoldSeats(int tripId, String fromPlace, String toPlace, String type) {
        return tripMapper.countSoldSeats(tripId, fromPlace, toPlace, type);
    }
}

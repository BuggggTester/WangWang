package com.example.demo.service.impl;

import com.example.demo.dao.PassengerMapper;
import com.example.demo.entity.Passenger;
import com.example.demo.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {
    @Resource
    private PassengerMapper passengerMapper;
    @Override
    public List<Passenger> selectPassengersById(int userId) {
        return passengerMapper.selectPassengersById(userId);
    }

    @Override
    public void createPassenger(String identity, String phoneNum, String name, int userId) {
        passengerMapper.createPassenger(identity, phoneNum, name, userId);
    }

    @Override
    public void updatePassenger(String identity, String phone, String name, int pid) {
        passengerMapper.updatePassenger(identity, phone, name, pid);
    }

    @Override
    public Passenger selectPassengerByIdentity(String identity) {
        return passengerMapper.selectPassengerByIdentity(identity);
    }
}

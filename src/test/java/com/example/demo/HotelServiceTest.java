package com.example.demo;

import com.example.demo.dao.HotelMapper;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.service.HotelService;
import com.example.demo.service.impl.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

@SpringBootTest
public class HotelServiceTest {

    private HotelService hotelService;

    @Autowired
    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }
}

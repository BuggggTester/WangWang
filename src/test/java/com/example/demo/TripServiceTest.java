package com.example.demo;

import com.example.demo.dao.OrderMapper;
import com.example.demo.service.OrderService;
import com.example.demo.service.TripService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;


@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    public void testCreateHotel() {
        System.out.println(tripService.selectAllTrips());

        assert(true);

    }
}

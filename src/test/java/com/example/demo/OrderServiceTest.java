package com.example.demo;

import com.example.demo.dao.OrderMapper;
import com.example.demo.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderMapper OrderMapper;

    @Test
    public void testCreateHotel() {
        System.out.println(orderService.selectAllOrders());

        assert(true);

    }
}

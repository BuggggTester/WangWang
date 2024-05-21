package com.example.demo.service.impl;

import com.example.demo.dao.OrderMapper;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Override
    public void createOrder(String orderId, Date orderTime, int userId, String type, String state, double payment, Integer tripId, int carriage, Integer row, Character seat, Timestamp payTime, String payway) {
        orderMapper.createOrder(orderId, orderTime, userId, type, state, payment, tripId, carriage, row, seat, payTime, payway);
    }

    @Override
    public void deleteOrderByCustomer(int tripId, int userId) {
        orderMapper.deleteOrderByCustomer(tripId, userId);
    }

    @Override
    public List<Order> selectOrdersByUser(int userId) {
        return orderMapper.selectOrdersByUser(userId);
    }

    @Override
    public List<Order> selectAllOrders() {
        return orderMapper.selectAllOrders();
    }

    @Override
    public List<Order> selectByDate(String date1, String date2) {
        return orderMapper.selectByDate(date1,date2);
    }

    @Override
    public List<Order> selectByLocation(String from, String to) {
        return orderMapper.selectByLocation(from,to);
    }

    @Override
    public List<Order> selectUnfinishedOrdersByUser(int userId) {
        return orderMapper.selectUnfinishedOrdersByUser(userId);
    }

    @Override
    public List<Order> selectNotDepartureOrdersByUser(int userId) {
        return orderMapper.selectNotDepartureOrdersByUser(userId);
    }

    @Override
    public List<Order> selectNotDepartureOrdersWithStartTime(int userId, Timestamp startTime) {
        return orderMapper.selectNotDepartureOrdersWithStartTime(userId, startTime);
    }

    @Override
    public List<Order> selectNotDepartureOrdersWithOrderTime(int userId, Timestamp orderTime) {
        return orderMapper.selectNotDepartureOrdersWithOrderTime(userId, orderTime);
    }

}

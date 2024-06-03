package com.example.demo.service;

import com.example.demo.entity.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public interface OrderService {
    void createOrder( Date orderTime, int userId, String type, String state, double payment,
                     Integer tripId, int carriage, Integer row, Character seat, Timestamp payTime, String payway,String fromPlace, String toPlace);
    void deleteOrderByCustomer(int orderId, int userId);
    List<Order> selectOrdersByUser(int userId);
    List<Order> selectAllOrders();
    List<Order> selectByDate(String date1, String date2);

    List<Order> selectByLocation(String from, String to);
    List<Order> selectUnfinishedOrdersByUser(int userId);
    List<Order> selectNotDepartureOrdersByUser(int userId);
    List<Order> selectNotDepartureOrdersWithStartTime(int userId, Timestamp startTime);
    List<Order> selectNotDepartureOrdersWithOrderTime(int userId, Timestamp orderTime);
    List<Order> selectOrdersByTripId(int tripId);

}

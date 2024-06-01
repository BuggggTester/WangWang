package com.example.demo.service;


import com.example.demo.common.constant.OrderType;
import com.example.demo.entity.Order;
import com.example.demo.entity.TotalOrder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public interface TotalOrderService {
    // 生成订单
    void createOrder(int userId, int reservationId, OrderType orderType, double payment);

    // 根据订单ID获取订单
    TotalOrder getOrder(int id);

    // 获取所有订单
    List<TotalOrder> getAllOrders(int userId);
    // 获取所有车票订单
    List<TotalOrder> getAllTrainTicketOrders(int userId);
    // 获取所有酒店订单
    List<TotalOrder> getAllHotelOrders(int userId);
    // 获取所有火车餐订单
    List<TotalOrder> getAllTrainMealOrders(int userId);
    // 获取最近10日订单(预览显示接口）
    List<TotalOrder> getLast10DaysOrders(int userId);
    // 根据类型和时间获取订单(用户自定义查询接口）
    List<TotalOrder> getOrdersByDateAndType(int userID, OrderType orderType, Timestamp startDate, Timestamp endDate);

    // 更新订单
    void updateOrder(TotalOrder order);

    // 删除订单
    void deleteOrder(int id);

    // 取消订单
    void cancelOrder(int reservationId);
}

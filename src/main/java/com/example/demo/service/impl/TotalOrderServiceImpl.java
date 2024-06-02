package com.example.demo.service.impl;

import com.example.demo.common.constant.OrderType;
import com.example.demo.dao.TotalOrderMapper;
import com.example.demo.entity.TotalOrder;
import com.example.demo.service.TotalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("TotalOrderService")
public class TotalOrderServiceImpl implements TotalOrderService {
    @Autowired
    private TotalOrderMapper totalOrderMapper;

    @Override
    public void createOrder(int userId, int reservationId, OrderType orderType, double payment) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        TotalOrder totalOrder = new TotalOrder();

        totalOrder.setOrderCreateTime(now);
        totalOrder.setPayment(payment);
        totalOrder.setOrderType(orderType);
        totalOrder.setReservationId(reservationId);
        totalOrder.setId(userId);

        totalOrderMapper.createOrder(totalOrder);
    }

    @Override
    public TotalOrder getOrder(int id) {
        return totalOrderMapper.getOrder(id);
    }

    @Override
    public List<TotalOrder> getAllOrders(int userId) {
        return totalOrderMapper.getAllOrders(userId);
    }

    @Override
    public List<TotalOrder> getAllTrainTicketOrders(int userId) {
        return totalOrderMapper.getAllTrainTicketOrders(userId);
    }

    public List<TotalOrder> getAllHotelOrders(int userId) {
        return totalOrderMapper.getAllHotelOrders(userId);
    }

    public List<TotalOrder> getAllTrainMealOrders(int userId) {
        return totalOrderMapper.getAllTrainMealOrders(userId);
    }

    public List<TotalOrder> getLast10DaysOrders(int userId) {
        return totalOrderMapper.getLast10DaysOrders(userId);
    }

    public List<TotalOrder> getOrdersByDateAndType(int userId, OrderType orderType, Timestamp startDate, Timestamp endDate) {
        return totalOrderMapper.getOrdersByDateAndType(userId, orderType, startDate, endDate);
    }

    public void updateOrder(TotalOrder order) {
        totalOrderMapper.updateOrder(order);
    }

    public void deleteOrder(int id) {
        totalOrderMapper.deleteOrder(id);
    }

    public void cancelOrder(int reservationId) {
        totalOrderMapper.cancelOrder(reservationId);
    }
}

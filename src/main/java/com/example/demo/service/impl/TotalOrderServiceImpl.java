package com.example.demo.service.impl;

import com.example.demo.common.constant.OrderType;
import com.example.demo.common.constant.PaymentMethod;
import com.example.demo.dao.TotalOrderMapper;
import com.example.demo.entity.TotalOrder;
import com.example.demo.entity.User;
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
    public void createOrder(TotalOrder totalOrder) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        totalOrder.setOrder_create_time(now);

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

    @Override
    public List<TotalOrder> getAllHotelOrders(int userId) {
        return totalOrderMapper.getAllHotelOrders(userId);
    }

    @Override
    public List<TotalOrder> getAllTrainMealOrders(int userId) {
        return totalOrderMapper.getAllTrainMealOrders(userId);
    }

    @Override
    public List<TotalOrder> getLast10DaysOrders(int userId) {
        return totalOrderMapper.getLast10DaysOrders(userId);
    }

    @Override
    public List<TotalOrder> getOrdersByDateAndType(int userId, OrderType orderType, Timestamp startDate, Timestamp endDate) {
        return totalOrderMapper.getOrdersByDateAndType(userId, orderType, startDate, endDate);
    }

    @Override
    public List<TotalOrder> getOrdersByType(int userId, OrderType orderType) {
        return totalOrderMapper.getOrdersByType(userId, orderType);
    }

    @Override
    public TotalOrder selectOrderById(int id) {
        return totalOrderMapper.selectOrderById(id);
    }

    @Override
    public int confirmOrder(int id) {
        return totalOrderMapper.confirmOrder(id);
    }

    @Override
    public void updateOrder(TotalOrder order) {
        totalOrderMapper.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(int id) {
        return totalOrderMapper.deleteOrder(id) == 1;
    }

    @Override
    public boolean cancelOrder(int Id) {
        return totalOrderMapper.cancelOrder(Id) == 1;
    }

    @Override
    public boolean finishOrder(int Id) {
        return totalOrderMapper.finishOrder(Id) == 1;
    }

    @Override
    public boolean setOrderPaymentMethod(int Id, PaymentMethod paymentMethod) {
        return totalOrderMapper.setOrderPaymentMethod(Id, paymentMethod) == 1;
    }

    @Override
    public boolean payOrder(int id) {
        return totalOrderMapper.payOrder(id) == 1;
    }
}

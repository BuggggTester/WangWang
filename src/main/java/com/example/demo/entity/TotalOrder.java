package com.example.demo.entity;

import com.example.demo.common.constant.OrderStatus;
import com.example.demo.common.constant.OrderType;
import com.example.demo.common.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Data
@Component
@Slf4j
@Entity
public class TotalOrder {
    //ID信息类
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int reservationId;

    //EnumType.STRING 是 @Enumerated 注解的一个枚举类型，
    // 表示枚举类型的值会被映射成数据库中的字符串类型。
    // 这意味着枚举的常量名称会被存储，而不是默认的整数值。
    // 订单种类与订单状态
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType orderType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus state = OrderStatus.PENDING_PAYMENT;

    //支付情况
    private double payment;
    private PaymentMethod paymentMethod;

    //时间戳
    private Timestamp payTime;
    private Timestamp finishTime;
    @Column(nullable = false)
    private Timestamp orderCreateTime;


}

package com.example.demo.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class Passenger {
    private int pid;
    private String identity;
    private String phone_number;
    private String name;
    private int user_id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "user_id")
    private User user;
}
//"failed: org.springframework.dao.DataIntegrityViolationException:
//### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wangwang`.`orders`, CONSTRAINT `orders_passengers_pid_fk` FOREIGN KEY (`pid`) REFERENCES `passengers` (`pid`))
//### The error may exist in com/example/demo/dao/OrderMapper.java (best guess)
//### The error may involve com.example.demo.dao.OrderMapper.createOrder-Inline
//### The error occurred while setting parameters
//### SQL: INSERT INTO orders (order_time, user_id, state, payment, trip_id, carriage, `row`, seat, payway, from_place, to_place, pid, seat_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
//### Cause: java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`wangwang`.`orders`, CONSTRAINT `orders_passengers_pid_fk` FOREIGN KEY (`pid`) REFERENCES `passengers` (`pid`))
//; Cannot add or update a child row: a foreign key constraint fails (`wangwang`.`orders`, CONSTRAINT `orders_passengers_pid_fk` FOREIGN KEY (`pid`) REFERENCES `passengers` (`pid`))"

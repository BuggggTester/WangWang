package com.example.demo.entity.food;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Entity
@Data
@Slf4j
@Component
public class FoodReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "trip_id", nullable = false)
    private int tripId;

    @Column(name = "food_id", nullable = false)
    private int foodId;

    private int quantity = 0;
}

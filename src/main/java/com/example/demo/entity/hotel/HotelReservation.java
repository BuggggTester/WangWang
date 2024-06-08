package com.example.demo.entity.hotel;

import com.example.demo.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Data
@Slf4j
@Component
public class HotelReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "check_in_date", nullable = false)
    private Date check_in_date;

    @Column(name="check_out_date", nullable = false)
    private Date check_out_date;
}


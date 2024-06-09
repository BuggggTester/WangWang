package com.example.demo.entity.hotel;
import com.example.demo.common.constant.RoomType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Slf4j
@Component
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType room_type;

    private Double price;
    private String description;
    private String picturePath;
    private int availableQuantity;

    @Column(name = "available", nullable = false, columnDefinition = "boolean default true")
    private boolean available = true;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HotelReservation> hotelReservations = new HashSet<>();

}

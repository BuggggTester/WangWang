package com.example.demo.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
@Component
public class Room {
    private int r_id;
    private String type;
    private int h_id;
    private String roomNum;
    private String picPath;

    @ManyToOne
    @JoinColumn(name = "h_id", referencedColumnName = "h_id")
    private Hotel hotel;
}

package com.example.demo.service;

import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.Room;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.List;

@Component
public interface HotelService {
    void createHotel(String name, String address);
    void setRoom(int hotelID, RoomType roomType, Double price, int quantity);

    List<Hotel> findHotelByAddress(String address);
    Hotel findHotelById(int id);
    boolean hasAvailableRoom(int hotelID, RoomType roomType, Date startdate, Date enddate);
    void bookRoom(int hotelID, RoomType roomType, Date startdate, Date enddate);
    void cancelRoom(int hotelID, RoomType roomType, Date startdate, Date enddate);
}



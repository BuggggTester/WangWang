package com.example.demo.service;

import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.HotelReservation;
import com.example.demo.entity.hotel.Room;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface HotelService {
    void createHotel(String name, String address);

    void setRoom(int hotelID, RoomType roomType, Double price, int quantity);

    List<Hotel> selectHotelByAddress(String address);

    Hotel selectHotelById(int id);

    boolean hasAvailableRoom(int hotelID, RoomType roomType, Date startdate, Date enddate);

    int countAvailableRooms(int hotelID, RoomType roomType, Date startdate, Date enddate);

    int bookRoom(int userId, int hotelID, RoomType roomType, Date startdate, Date enddate);

    void cancelRoom(int reservationID);

    void setHotelInfo(String name, String address, String description, String score);

    Double countLowestPrice(int hotelID);

    void updatePictureById(String picture, int hotelId);

    List<Room> getAvailableRoom(int hotelId, Date startdate, Date enddate);

    HotelReservation selectHotelReservationById(int hrId);

}



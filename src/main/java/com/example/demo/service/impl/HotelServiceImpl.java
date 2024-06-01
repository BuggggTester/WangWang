package com.example.demo.service.impl;

import com.example.demo.common.constant.RoomType;
import com.example.demo.dao.HotelMapper;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.Room;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("HotelService")
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public void createHotel(String name, String address) {
        hotelMapper.createHotel(name, address);
    }

    @Override
    public void setRoom(int hotelID, RoomType roomType, Double price, int quantity) {
        Hotel hotel = selectHotelById(hotelID);

        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setPrice(price);
        room.setQuantity(quantity);
        hotelMapper.setRoom(room);
    }

    @Override
    public List<Hotel> selectHotelByAddress(String address) {
        return hotelMapper.findHotelByAddress(address);
    }

    @Override
    public Hotel selectHotelById(int id) {
        return hotelMapper.findHotelById(id);
    }

    @Override
    public boolean hasAvailableRoom(int hotelID, RoomType roomType, Date startdate, Date enddate) {
        int availableRoomQuantity = hotelMapper.countAvailableRooms(hotelID, roomType, startdate, enddate);
        return availableRoomQuantity != 0;
    }

    @Override
    public void bookRoom(int hotelID, RoomType roomType, Date startdate, Date enddate) {
        int roomID = hotelMapper.getAvailableRoomId(hotelID, roomType, startdate, enddate);
        hotelMapper.insertReservation(roomID, startdate, enddate);
    }

    @Override
    public void cancelRoom(int reservationID) {
        hotelMapper.cancelRoom(reservationID);
    }
}

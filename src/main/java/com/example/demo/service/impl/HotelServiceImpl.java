package com.example.demo.service.impl;

import com.example.demo.common.constant.RoomType;
import com.example.demo.dao.HotelMapper;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.HotelReservation;
import com.example.demo.entity.hotel.Room;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelMapper hotelMapper;
    private final Hotel hotel;

    @Autowired
    public HotelServiceImpl(HotelMapper hotelMapper, Hotel hotel) {
        this.hotelMapper = hotelMapper;
        this.hotel = hotel;
    }

    @Override
    public void createHotel(String name, String address) {
        hotelMapper.createHotel(name, address);
    }

    @Override
    public void setRoom(int hotelID, RoomType roomType, Double price, int quantity) {
        Hotel hotel = selectHotelById(hotelID);

        Room room = new Room();
        room.setHotel(hotel);
        room.setRoom_type(roomType);
        room.setPrice(price);
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
    public int countAvailableRooms(int hotelID, RoomType roomType, Date startdate, Date enddate) {
        return hotelMapper.countAvailableRooms(hotelID, roomType, startdate, enddate);
    }

    @Override
    public int bookRoom(int userId, int hotelID, RoomType roomType, Date startdate, Date enddate) {
        int roomID = hotelMapper.getAvailableRoomId(hotelID, roomType, startdate, enddate);
        return hotelMapper.insertReservation(userId, roomID, startdate, enddate);
    }

    @Override
    public void cancelRoom(int reservationID) {
        hotelMapper.cancelRoom(reservationID);
    }

    @Override
    public void setHotelInfo(String name, String address, String description, String score) {
        hotelMapper.setHotelInfo(name, address, description, score);
    }

    @Override
    public Double countLowestPrice(int hotelID) {
        return hotelMapper.getLowestPriceByHotelId(hotelID);
    }

    @Override
    public void updatePictureById(String picture, int hotelId) {
        //hotelMapper.updatePictureById(picture, hotelId);
    }

    @Override
    public List<Room> getAvailableRoom(int hotelId, Date startDate, Date endDate) {
        return hotelMapper.getAvailableRoomByHotelId(hotelId, startDate, endDate);
    }


    @Override
    public HotelReservation selectHotelReservationById(int hrId) {
        return hotelMapper.selectHotelReservationById(hrId);
    }
}

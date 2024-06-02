package com.example.demo.controller;

import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Date;
import java.util.Map;

//
@Slf4j
@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/createHotel")
    public void createHotel(@RequestBody Map<String, String> hotelMap) {
        String name = hotelMap.get("name");
        String address = hotelMap.get("address");
        hotelService.createHotel(name, address);
    }

    @PostMapping("/{hotelID}/rooms")
    public void setRoom(@PathVariable int hotelID,
                        @RequestBody Map<String, String> roomInfo) {
        RoomType roomType = RoomType.valueOf(roomInfo.get("roomType"));
        Double price = Double.parseDouble(roomInfo.get("price"));
        int quantity = Integer.parseInt(roomInfo.get("quantity"));
        hotelService.setRoom(hotelID, roomType, price, quantity);
    }

    @GetMapping
    public List<Hotel> selectHotelByAddress(@RequestParam String address) {
        return hotelService.selectHotelByAddress(address);
    }

    @GetMapping("/{id}")
    public Hotel selectHotelById(@PathVariable int id) {
        return hotelService.selectHotelById(id);
    }

    @GetMapping("/checkAvailability")
    public boolean hasAvailableRoom(@RequestParam int hotelID,
                                    @RequestParam RoomType roomType,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,
                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate) {
        return hotelService.hasAvailableRoom(hotelID, roomType, startdate, enddate);
    }

    @PostMapping("/book")
    public void bookRoom(@RequestParam int hotelID,
                         @RequestParam RoomType roomType,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startdate,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date enddate) {
        hotelService.bookRoom(hotelID, roomType, startdate, enddate);
    }

    @DeleteMapping("/cancel/{reservationID}")
    public void cancelRoom(@PathVariable int reservationID) {
        hotelService.cancelRoom(reservationID);
    }
}

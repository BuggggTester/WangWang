package com.example.demo.controller;

import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.R;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    @Autowired
    private Hotel hotel;

    //测试通过
    @PostMapping("/createHotel")
    public R createHotel(@RequestBody Map<String, String> hotelMap) {
        String name = hotelMap.get("name");
        String address = hotelMap.get("address");
        hotelService.createHotel(name, address);
        return R.ok();
    }

    @PutMapping("/")
    public R setHotelInfo(@RequestBody Map<String, String> hotelMap) {
        String name = hotelMap.get("name") != null ? hotelMap.get("name") : "";
        String address = hotelMap.get("address") != null ? hotelMap.get("address") : "";
        String description = hotelMap.get("description") != null ? hotelMap.get("description") : "";
        String score = hotelMap.get("score") != null ? hotelMap.get("score") : "";
        hotelService.setHotelInfo(name, address, description, score);
        return R.ok();
    }

    //测试通过
    @PostMapping("/{hotelID}/rooms")
    public void setRoom(@PathVariable int hotelID,
                        @RequestBody Map<String, String> roomInfo) {
        RoomType roomType = RoomType.valueOf(roomInfo.get("roomType"));
        Double price = Double.parseDouble(roomInfo.get("price"));
        int quantity = Integer.parseInt(roomInfo.get("quantity"));
        hotelService.setRoom(hotelID, roomType, price, quantity);
    }

    //测试通过
    @GetMapping
    public List<Hotel> selectHotelByAddress(@RequestParam String address) {
        return hotelService.selectHotelByAddress(address);
    }

    //测试通过
    @GetMapping("/{id}")
    public Hotel selectHotelById(@PathVariable int id) {
        return hotelService.selectHotelById(id);
    }

    //
    @GetMapping("/checkAvailability")
    public R hasAvailableRoom(@RequestBody Map<String, String> requestParams) {
        try {
            int hotelID = Integer.parseInt(requestParams.get("hotelID"));
            RoomType roomType = RoomType.valueOf(requestParams.get("roomType").toUpperCase());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(requestParams.get("startDate"));
            Date endDate = dateFormat.parse(requestParams.get("endDate"));

            boolean available = hotelService.hasAvailableRoom(hotelID, roomType, startDate, endDate);
            return R.ok(available ? "Room is available" : "No available room");
        } catch (NumberFormatException e) {
            return R.error("Failed to parse number: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return R.error("Invalid room type: " + e.getMessage());
        } catch (ParseException e) {
            return R.error("Failed to parse date: " + e.getMessage());
        } catch (Exception e) {
            return R.error("Failed to check room availability: " + e.toString());
        }
    }

    // 测试通过
    @PostMapping("/book")
    public R bookRoom(@RequestBody Map<String, String> requestParams) {
        try {
            int userId = Integer.parseInt(requestParams.get("userId"));
            int hotelId = Integer.parseInt(requestParams.get("hotelId"));
            RoomType roomType = RoomType.valueOf(requestParams.get("roomType").toUpperCase());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(requestParams.get("startDate"));
            Date endDate = dateFormat.parse(requestParams.get("endDate"));

            int reservationId = hotelService.bookRoom(userId, hotelId, roomType, startDate, endDate);
            Map<String, Object> map = new HashMap<>();
            map.put("reservationId", reservationId);
            return R.ok(map);
        } catch (NumberFormatException e) {
            return R.error("Failed to parse number: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return R.error("Invalid room type: " + e.getMessage());
        } catch (ParseException e) {
            return R.error("Failed to parse date: " + e.getMessage());
        } catch (Exception e) {
            return R.error("Failed to book room: " + e.toString());
        }
    }

    @PostMapping("/cancel/{reservationID}")
    public R cancelRoom(@PathVariable int reservationID) {
        try {
            hotelService.cancelRoom(reservationID);
            return R.ok("Reservation cancelled successfully!");
        } catch (Exception e) {
            return R.error("Failed to cancel reservation: " + e.toString());
        }
    }
}

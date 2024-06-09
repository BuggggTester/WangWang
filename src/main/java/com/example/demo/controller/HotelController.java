package com.example.demo.controller;

import com.example.demo.common.constant.RoomType;
import com.example.demo.config.PathConfig;
import com.example.demo.entity.R;
import com.example.demo.entity.hotel.*;
import com.example.demo.entity.hotel.HotelReservation;
import com.example.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.config.PathConfig.hotelUrl;

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

    @RequestMapping("/")
    public R setHotelInfo(@RequestBody Map<String, String> hotelMap) {
        String name = hotelMap.get("name") != null ? hotelMap.get("name") : "";
        String address = hotelMap.get("address") != null ? hotelMap.get("address") : "";
        String description = hotelMap.get("description") != null ? hotelMap.get("description") : "";
        String score = hotelMap.get("score") != null ? hotelMap.get("score") : "";
        hotelService.setHotelInfo(name, address, description, score);
        return R.ok();
    }
    @RequestMapping("/hotelreservation")
    public HotelReservation selectHotelReservationById(@RequestParam("hrId")String hrId) {
        return hotelService.selectHotelReservationById(Integer.parseInt(hrId));
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
    @GetMapping("/selectHotelByAddress")
    public List<Hotel> selectHotelByAddress(@RequestParam("address") String address) {
        //System.out.println(hotelService.selectHotelByAddress(address));
        return putPriceHelpFunction(hotelService.selectHotelByAddress(address));
    }

    @GetMapping("/selectHotelByPriceASC")
    public List<Hotel> selectHotelByPriceASC(@RequestParam("address") String address) {
        List<Hotel> hotelList = hotelService.selectHotelByAddress(address);
        List<Hotel> sortedList =  hotelList.stream()
                .sorted((hotel1, hotel2) ->
                        Double.compare(hotelService.countLowestPrice(hotel1.getId())
                                , hotelService.countLowestPrice(hotel2.getId())))
                .toList();
        return putPriceHelpFunction(sortedList);
    }

    @GetMapping("/selectHotelByPriceDESC")
    public List<Hotel> selectHotelByPriceDESC(@RequestParam("address") String address) {
        List<Hotel> hotelList = hotelService.selectHotelByAddress(address);
        List<Hotel> sortedList = hotelList.stream()
                .sorted((hotel1, hotel2) ->
                        Double.compare(hotelService.countLowestPrice(hotel2.getId())
                                , hotelService.countLowestPrice(hotel1.getId())))
                .toList();
        return putPriceHelpFunction(sortedList);
    }

    @GetMapping("/selectHotelByScore")
    public List<Hotel> selectHotelByScore(@RequestParam("address") String address) {
        List<Hotel> hotelList = hotelService.selectHotelByAddress(address);
        hotelList = putPriceHelpFunction(hotelList);
        return hotelList.stream()
                .sorted((hotel1, hotel2) ->
                        Double.compare(Double.parseDouble(hotel2.getScore()),
                                Double.parseDouble(hotel1.getScore())))
                .toList();
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

    @GetMapping("/hotel/lowestPrice")
    public R getLowestPrice(@RequestParam("hotelId") int hotelId) {
        return R.ok().put("minPrice",Math.floor(hotelService.countLowestPrice(hotelId)));
    }
    @RequestMapping(value = "/upload/picture")
    public R updateAvatar(@RequestParam("picture") MultipartFile file, @RequestParam("hotelId") int hotelId) {
        Hotel hotel = hotelService.selectHotelById(hotelId);
        String filePath = hotelUrl;
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileNewName = UUID.randomUUID() + fileType;
        hotelService.updatePictureById(PathConfig.hotel + fileNewName, hotelId);
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+ fileNewName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return R.error(e.toString());
        }
        return R.ok("上传成功！");
    }

    @GetMapping("/hotel/getRoom")
    List<Room> getRoomByHotelIdAndDate(@RequestBody Map<String, String> requestParams) throws ParseException {
        int hotelId = Integer.parseInt(requestParams.get("hotelId"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(requestParams.get("startDate"));
        Date endDate = dateFormat.parse(requestParams.get("endDate"));
        List<Room> roomList;
        if (startDate.after(endDate)) {
            roomList = hotelService.getAvailableRoom(hotelId);
            for (Room room : roomList) {
                room.setAvailableQuantity(0);
            }
            return roomList;
        }
        roomList = hotelService.getAvailableRoom(hotelId, startDate, endDate);
        int singleRoomQuantity = hotelService.countAvailableRooms(hotelId, RoomType.SINGLE, startDate, endDate);
        int doubleRoomQuantity = hotelService.countAvailableRooms(hotelId, RoomType.DOUBLE, startDate, endDate);
        int suiteRoomQuantity = hotelService.countAvailableRooms(hotelId, RoomType.SUITE, startDate, endDate);
        for (Room room : roomList) {
            if (room.getRoom_type().equals(RoomType.SINGLE)) {
                room.setAvailableQuantity(singleRoomQuantity);
            }
            else if (room.getRoom_type().equals(RoomType.DOUBLE)) {
                room.setAvailableQuantity(doubleRoomQuantity);
            }
            else if (room.getRoom_type().equals(RoomType.SUITE)) {
                room.setAvailableQuantity(suiteRoomQuantity);
            }
        }
        return roomList;
    }

    List<Hotel> putPriceHelpFunction(List<Hotel> hotelList) {
        for (Hotel hotel : hotelList) {
            hotel.setLowestPrice(hotelService.countLowestPrice(hotel.getId()));
        }
        return hotelList;
    }


}

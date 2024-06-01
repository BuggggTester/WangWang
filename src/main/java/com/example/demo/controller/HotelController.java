package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

//
@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value ="/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping(value="/select/address")
    public List<Hotel> selectHotelByAddress(@RequestParam("address") String address) {
        return hotelService.selectHotelByAddress(address);
    }


}

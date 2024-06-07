package com.example.demo.controller;

import com.example.demo.entity.Passenger;
import com.example.demo.entity.R;
import com.example.demo.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.utils.Md5Utils.agenerate;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value ="/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @RequestMapping(value="/select/userId")
    public List<Passenger> selectPassengersByUserId(@RequestParam("userId")String userId) {
        try{
            return passengerService.selectPassengersById(Integer.parseInt(userId));
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    @RequestMapping(value="/create")
    public R createpassenger(@RequestBody HashMap<String, String> idMap) {
        try {
            String identity = idMap.get("identity");
            String phoneNum = idMap.get("phoneNum");
            String name = idMap.get("name");
            int userId = Integer.parseInt(idMap.get("userId"));
            passengerService.createPassenger(identity, phoneNum, name, userId);
            return R.ok("create passenger success");
        }catch (Exception e){
            return R.error(e.toString());
        }

    }
}

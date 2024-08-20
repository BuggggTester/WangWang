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
    public R createPassenger(@RequestBody HashMap<String, String> idMap) {
        try {
            String identity = idMap.get("identity");
            String phoneNum = idMap.get("phoneNum");
            String name = idMap.get("name");
            int userId = Integer.parseInt(idMap.get("userId"));
            Passenger passenger = passengerService.selectPassengerByIdentity(identity);
            if(passenger != null) {
                return R.error("passenger already exists");
            }
            passengerService.createPassenger(identity, phoneNum, name, userId);
            return R.ok("create passenger success");
        }catch (Exception e){
            return R.error(e.toString());
        }

    }
    @RequestMapping(value="/modify")
    public R updatePassenger(@RequestBody HashMap<String, String> idMap) {
        try{
            String identity = idMap.get("identity");
            String phoneNum = idMap.get("phoneNum");
            String name = idMap.get("name");
            int pid = Integer.parseInt(idMap.get("pid"));
            //TODO: 加入验证
            if(phoneNum.length()!=18){
                return R.error("illegal phone");
            }
            if(identity.length()!= 18) {
                return R.error("illegal identity");
            }
            passengerService.updatePassenger(identity, phoneNum, name, pid);
            return R.ok("modify passenger success");
        }catch (Exception e) {
            return R.error(e.toString());
        }
    }
}

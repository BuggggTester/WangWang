package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.Trip;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/trip")
public class TripController {
    @Autowired
    private TripService tripService;
    @ResponseBody
    @RequestMapping(value = "/create")
    public R createTrip(@RequestBody Map<String, String> tripOrder) {
//        String trainId, Timestamp startTime, java.sql.Timestamp endTime, String fromPlace, String toPlace, int numCar, int numRow
        String trainId = tripOrder.get("trainId");
        Timestamp startTime = Timestamp.valueOf(tripOrder.get("startTime"));
        Timestamp endTime = Timestamp.valueOf(tripOrder.get("endTime"));
        String fromPlace = tripOrder.get("fromPlace");
        String toPlace = tripOrder.get("toPlace");
        int numCar = Integer.parseInt(tripOrder.get("numCar"));
        int numRow = Integer.parseInt(tripOrder.get("numRow"));
        tripService.createTrip(trainId, startTime, endTime, fromPlace, toPlace, numCar, numRow);
        return R.ok("创建成功！");
    }
    @PostMapping(value = "/select/place/time")
    public List<Trip> selectTripsByPlaceAndTime(@RequestBody Map<String, String> tripMap) {
        String fromPlace = tripMap.get("fromPlace");
        String toPlace = tripMap.get("toPlace");
        Timestamp startTime = Timestamp.valueOf(tripMap.get("startTime"));
        List<Trip> trips = tripService.selectTripByPlaceAndTime(fromPlace,toPlace,startTime);
        //如果trip不是查询本日的，移除这个trip
        trips.removeIf(trip -> (trip.getStart_time().getTime() - startTime.getTime()) > (1000 * 60 * 60 * 24));
        return trips;
    }
    @RequestMapping(value = "/sum")
    public R countRestSeats(@RequestParam("tripId") int tripId) {
        Trip trip =  tripService.selectTripById(tripId);
        int sum = trip.getNum_car() * trip.getNum_row() * 5;//每一趟列车的座位总数
        int now = tripService.countSoldSeats(tripId);
        if(sum < now) {
            return R.error("get failed!");
        }
        else{
            return R.ok().put("restSeats", sum-now);
        }
    }

}

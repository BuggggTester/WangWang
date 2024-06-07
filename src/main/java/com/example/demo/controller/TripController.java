package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.Trip;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @RequestMapping(value = "/select/place/time")
    public List<Trip> selectTripsByPlaceAndTime(@RequestBody Map<String, String> tripMap) {
        String fromPlace = tripMap.get("fromPlace");
        String toPlace = tripMap.get("toPlace");
        Timestamp startTime = Timestamp.valueOf(tripMap.get("startTime"));
        List<Trip> trips = tripService.selectTripByPlaceAndTime(fromPlace,toPlace,startTime);
        //如果trip不是查询本日的，移除这个trip
        trips.removeIf(trip -> (trip.getStart_time().getTime() - startTime.getTime()) > (1000 * 60 * 60 * 24));

        return trips;
    }
    @RequestMapping(value="/select/tripId")
    public Trip selectTripById(@RequestParam("tripId")int tripId){
        return tripService.selectTripById(tripId);
    }
    @RequestMapping(value = "/sum")
    public R countRestSeatsAndTime(@RequestParam("tripId") int tripId,
                            @RequestParam("fromPlace")String fromPlace,
                            @RequestParam("toPlace")String toPlace) {
        Trip trip =  tripService.selectTripById(tripId);
        int secondSum = (trip.getNum_car()-1) * trip.getNum_row() * 5;//每一趟列车的座位总数
        int firstSum = trip.getNum_row() * 5;
        int secondNow = tripService.countSoldSeats(tripId, fromPlace,toPlace, "first");
        int firstNow = tripService.countSoldSeats(tripId,fromPlace, toPlace,"second");
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] timeArray =trip.getTime_chain().split("_");
        String[] stationArray = trip.getTrip_chain().split("-");
        int from = 0;
        int to = 0;
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(fromPlace)) {
                from = i;
                break;
            }
        }
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(toPlace)) {
                to = i;
                break;
            }
        }

        String time1 = timeArray[from];
        String time2 = timeArray[to];
        // 解析时间字符串为 LocalDateTime 对象
        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);

        // 计算两个时间之间的时间差
        Duration duration = Duration.between(dateTime1, dateTime2);

        // 将时间差转换为 x天x小时x分 的格式输出
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        String time;
        if(days != 0) {
         time = days + "天 " + hours + "小时 " + minutes + "分";
        }else{
            if(hours !=0) {
                time = hours + "小时 " + minutes + "分";
            }else{
                time = minutes + "分";
            }
        }
        if(secondSum < secondNow || firstSum < firstNow) {
            return R.error("get failed!");
        }
        else{
            return Objects.requireNonNull(Objects.requireNonNull(R.ok().put("firstSeats", firstSum - firstNow)).put("secondSeats", secondSum - secondNow)).put("time",time);
        }
    }
    @RequestMapping( value = "/minPrice") 
    public R getMinPriceById(@RequestParam("tripId")int tripId,
                             @RequestParam("fromPlace") String fromPlace,
                             @RequestParam("toPlace")String toPlace) {
        Trip trip = tripService.selectTripById(tripId);
        String priceChain = trip.getSecond_price_chain();
        String[] stationArray = trip.getTrip_chain().split("-");
        String[] priceArray = priceChain.split("-");
        int from = 0;
        int to = 0;
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(fromPlace)) {
                from = i;
                break;
            }
        }
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(toPlace)) {
                to = i;
                break;
            }
        }
        double sum = 0;
        if(from >= to) {
            return R.error("station error");
        }
        for(int i = from; i<to; i++) {
            sum = sum + Double.parseDouble(priceArray[i]);
        }
        return R.ok().put("minPrice", sum);
    }
    @RequestMapping(value="/price/fromPlace/toPlace/tripId")
    public R getPriceByStation(@RequestParam("fromPlace")String fromPlace,
                               @RequestParam("toPlace")String toPlace,
                               @RequestParam("tripId") String tripId,
                               @RequestParam("type")String type) {
        if(!type.equals("first") && !type.equals("second")) {
            return R.error("type error");
        }
        Trip trip = tripService.selectTripById(Integer.parseInt(tripId));
        if(trip == null || trip.getTrip_chain() == null) {
            return R.error("no trip");
        }
        String[] priceArray;
        if(type.equals("first")) {
            priceArray = trip.getFirst_price_chain().split("-");
        }else{
            priceArray = trip.getSecond_price_chain().split("-");
        }
        String[] stationArray;
        int from = 0;
        int to = 0;
        stationArray = trip.getTrip_chain().split("-");
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(fromPlace)) {
                from = i;
                break;
            }
        }
        for(int i=0; i< stationArray.length; i++) {
            if(stationArray[i].equals(toPlace)) {
                to = i;
                break;
            }
        }
        double sum = 0;
        if(from >= to) {
            return R.error("station error");
        }
        for(int i = from; i<to; i++) {
            sum = sum + Double.parseDouble(priceArray[i]);
        }
        return R.ok().put("price", sum);
    }
}

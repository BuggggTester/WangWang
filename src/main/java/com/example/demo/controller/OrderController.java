package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.R;
import com.example.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.GenerateOrderId.getUUID;
@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    Order order;

    @ResponseBody
    @RequestMapping(value = "/create")
    public R createOrder(@RequestBody Map<String, String> orderMap) {
        try {
            String orderId = getUUID();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Date orderTime = ft.parse(orderMap.get("order_time"));
            int userId = Integer.parseInt(orderMap.get("user_id"));
            String type = orderMap.get("type");
            String state = orderMap.get("state");
            double payment = Double.parseDouble(orderMap.get("payment"));
            Integer tripId = orderMap.containsKey("trip_id") ? Integer.parseInt(orderMap.get("trip_id")) : null;
            int carriage = Integer.parseInt(orderMap.get("carriage"));
            Integer row = orderMap.containsKey("row") ? Integer.parseInt(orderMap.get("row")) : null;
            Character seat = orderMap.containsKey("seat") ? orderMap.get("seat").charAt(0) : null;
            Timestamp payTime = orderMap.containsKey("payTime") ? Timestamp.valueOf(orderMap.get("payTime")) : null;
            String payway = orderMap.get("payway");

            orderService.createOrder(orderId, orderTime, userId, type, state, payment, tripId, carriage, row, seat, payTime, payway);

            return R.ok("订单创建成功！");
        } catch (ParseException | NumberFormatException e) {
            return R.error("failed: " + e.getMessage());
        } catch (Exception e) {
            return R.error("failed: " + e.toString());
        }
    }
    @RequestMapping(value="/select/history/{userId}")
    public List<Order> selectOrdersByUser(@PathVariable int userId){
        List<Order> orders = orderService.selectOrdersByUser(userId);
        return orders;
    }
    @GetMapping(value="/select/unfinished/{userId}")
    public List<Order> selectUnfinishedOrdersByUser(@PathVariable int userId){
        return orderService.selectUnfinishedOrdersByUser(userId);
    }
    @GetMapping(value="/select/all")
    public List<Order> selectAllOrders(){
        return orderService.selectAllOrders();
    }
    @GetMapping(value = "/select/date")
    public List<Order> selectByDate(@RequestBody Map<String,String> dateMap){
        try {
            String date1 = dateMap.get("date1");
            String date2 = dateMap.get("date2");
            return orderService.selectByDate(date1, date2);
        }catch (Exception e){
            System.out.println("发生错误"+e.toString());
            return null;
        }
    }
    @GetMapping(value = "/delete/{tripId}/{userId}")
    public R cancelOrderByCustomer(@PathVariable int tripId, @PathVariable int userId){
        try{
            orderService.deleteOrderByCustomer(tripId, userId);
            return R.ok("删除成功！");
        }catch(Exception e){
            return R.error(e.toString());
        }
    }
    @GetMapping(value = "/select/notdeparture/{userId}")
    public List<Order> selectNotDepartureOrdersByUser(@PathVariable int userId){
        return orderService.selectNotDepartureOrdersByUser(userId);
    }
    @GetMapping(value="/select/{from}/{to}")
    public List<Order> selectByLocation(@PathVariable String from, @PathVariable String to){
        List<Order> orders = orderService.selectByLocation(from,to);
        return orders;
    }
    @GetMapping(value = "/select/notdeparture/{userId}/startTime")
    public List<Order> selectNotDepartureByStartTime(@PathVariable int userId, @RequestParam("startTime") String startTime) {
        List<Order> list = null;
        Timestamp time = Timestamp.valueOf(startTime);
        try {
            list = orderService.selectNotDepartureOrdersWithStartTime(userId, time);
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return list;
    }
    @GetMapping(value = "/select/notdeparture/{userId}/orderTime")
    public List<Order> selectNotDepartureByOrderTime(@PathVariable int userId, @RequestParam("orderTime") String orderTime) {
        List<Order> list = null;
        Timestamp time = Timestamp.valueOf(orderTime);
        try {
            list = orderService.selectNotDepartureOrdersWithStartTime(userId, time);
        } catch(Exception e){
            System.out.println(e.toString());
        }
        return list;
    }
}

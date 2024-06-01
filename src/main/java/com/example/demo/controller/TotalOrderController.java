package com.example.demo.controller;

import com.example.demo.common.constant.OrderType;
import com.example.demo.entity.R;
import com.example.demo.entity.TotalOrder;
import com.example.demo.service.TotalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.GenerateOrderId.getUUID;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin
@Slf4j
public class TotalOrderController {

    @Autowired
    private TotalOrderService totalOrderService;


    @PostMapping("/create")
    public R createOrder(@RequestBody Map<String, String> orderMap) {
        try {
            int userId = Integer.parseInt(orderMap.get("userId"));
            int reservationId = Integer.parseInt(orderMap.get("reservationId"));
            OrderType orderType = OrderType.valueOf(orderMap.get("orderType").toUpperCase());
            double payment = Double.parseDouble(orderMap.get("payment"));

            totalOrderService.createOrder(userId, reservationId, orderType, payment);

            return R.ok("Order created successfully!");
        } catch (NumberFormatException e) {
            return R.error("Failed to parse number: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return R.error("Invalid order type: " + e.getMessage());
        } catch (Exception e) {
            return R.error("Failed to create order: " + e.toString());
        }
    }

    @GetMapping("/get/{id}")
    public TotalOrder getOrder(@PathVariable int id) {
        return totalOrderService.getOrder(id);
    }

    @GetMapping("/getAll/{userId}")
    public List<TotalOrder> getAllOrders(@PathVariable int userId) {
        return totalOrderService.getAllOrders(userId);
    }

    @GetMapping("/getAllTrainTickets/{userId}")
    public List<TotalOrder> getAllTrainTicketOrders(@PathVariable int userId) {
        return totalOrderService.getAllTrainTicketOrders(userId);
    }

    @GetMapping("/getAllHotels/{userId}")
    public List<TotalOrder> getAllHotelOrders(@PathVariable int userId) {
        return totalOrderService.getAllHotelOrders(userId);
    }

    @GetMapping("/getAllTrainMeals/{userId}")
    public List<TotalOrder> getAllTrainMealOrders(@PathVariable int userId) {
        return totalOrderService.getAllTrainMealOrders(userId);
    }

    @GetMapping("/getLast10DaysOrders/{userId}")
    public List<TotalOrder> getLast10DaysOrders(@PathVariable int userId) {
        return totalOrderService.getLast10DaysOrders(userId);
    }

    @GetMapping("/getOrdersByDateAndType")
    public List<TotalOrder> getOrdersByDateAndType(@RequestParam int userId, @RequestParam OrderType orderType, @RequestParam Timestamp startDate, @RequestParam Timestamp endDate) {
        return totalOrderService.getOrdersByDateAndType(userId, orderType, startDate, endDate);
    }

    @PutMapping("/update")
    public void updateOrder(@RequestBody TotalOrder order) {
        totalOrderService.updateOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable int id) {
        totalOrderService.deleteOrder(id);
    }

    @DeleteMapping("/cancel/{reservationId}")
    public void cancelOrder(@PathVariable int reservationId) {
        totalOrderService.cancelOrder(reservationId);
    }
}

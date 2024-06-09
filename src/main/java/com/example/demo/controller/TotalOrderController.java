package com.example.demo.controller;

import com.example.demo.common.constant.OrderStatus;
import com.example.demo.common.constant.OrderType;
import com.example.demo.common.constant.PaymentMethod;
import com.example.demo.entity.R;
import com.example.demo.entity.TotalOrder;
import com.example.demo.entity.User;
import com.example.demo.entity.food.Food;
import com.example.demo.entity.food.FoodReservation;
import com.example.demo.entity.hotel.HotelReservation;
import com.example.demo.service.FoodService;
import com.example.demo.service.HotelService;
import com.example.demo.service.TotalOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.GenerateOrderId.getUUID;

@RestController
@RequestMapping(value = "/totalorder")
@CrossOrigin
@Slf4j
public class TotalOrderController {

    @Autowired
    private TotalOrderService totalOrderService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private User user;


    @PostMapping("/create")
    public R createOrder(@RequestBody TotalOrder totalOrder) {
        try {
//            int userId = Integer.parseInt(orderMap.get("userId"));
//            int reservationId = Integer.parseInt(orderMap.get("reservationId"));
//            OrderType orderType = OrderType.valueOf(orderMap.get("orderType").toUpperCase());
//            double payment = Double.parseDouble(orderMap.get("payment"));

            totalOrderService.createOrder(totalOrder);

            return R.ok("Order created successfully!").put("id",totalOrder.getId());
        } catch (NumberFormatException e) {
            return R.error("Failed to parse number: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return R.error("Invalid order type: " + e.getMessage());
        } catch (Exception e) {
            return R.error("Failed to create order: " + e.toString());
        }
    }
    @RequestMapping(value="/confirm")
    public R confirmFoodOrder(@RequestParam("id")int id){
        totalOrderService.confirmOrder(id);
        return R.ok("confirm success");
    }
    @GetMapping("/get/{id}")
    public TotalOrder getOrder(@PathVariable int id) {
        try {
            return totalOrderService.getOrder(id);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getAll/{userId}")
    public List<TotalOrder> getAllOrders(@PathVariable int userId) {
        try {
            return totalOrderService.getAllOrders(userId);
        } catch (Exception e) {
            return null;
        }
    }
    @RequestMapping(value="/getAllFoodReservations")
    public List<FoodReservation> getAllFoodOrders(@RequestParam("userId")int userId) {
        List<TotalOrder> totalOrders = totalOrderService.getOrdersByType(userId, OrderType.valueOf("TRAIN_MEAL"));
        List<FoodReservation> res = new ArrayList<>();
        for(TotalOrder totalOrder: totalOrders) {
            FoodReservation foodReservation = foodService.selectFoodReservationById(totalOrder.getReservation_id());
            if(foodReservation == null) {
                continue;
            }
            foodReservation.setOrder_time(totalOrder.getOrder_create_time());
            foodReservation.setState(totalOrder.getState());
            foodReservation.setTid(totalOrder.getId());
            res.add(foodReservation);
        }
        return res;
    }
    @GetMapping("/getAllTrainTickets/{userId}")
    public List<TotalOrder> getAllTrainTicketOrders(@PathVariable int userId) {
        return totalOrderService.getAllTrainTicketOrders(userId);
    }

    @GetMapping("/getAllHotelReservations")
    public List<HotelReservation> getAllHotelOrders(@RequestParam("userId") String userId) {
        List<TotalOrder> totalOrders = totalOrderService.getAllHotelOrders(Integer.parseInt(userId));
        List<HotelReservation> res = new ArrayList<>();
        for(TotalOrder totalOrder: totalOrders) {
            HotelReservation hotelReservation = hotelService.selectHotelReservationById(totalOrder.getReservation_id());
            res.add(hotelReservation);
        }
        return res;
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

    @GetMapping("/getOrderByType")
    public List<TotalOrder> getOrderByType(@RequestBody Map<String, String> orderMap) {
        int userId = Integer.parseInt(orderMap.get("userId"));
        OrderType orderType = OrderType.valueOf(orderMap.get("orderType").toUpperCase());
        return totalOrderService.getOrdersByType(userId, orderType);
    }

    // 没用（先留着）
    @PutMapping("/update")
    public void updateOrder(@RequestBody TotalOrder order) {
        totalOrderService.updateOrder(order);
    }


    @DeleteMapping("/delete/{id}")
    public R deleteOrder(@PathVariable int id) {
        if (totalOrderService.deleteOrder(id)) {
            return R.ok("Order deleted successfully!");
        }
        return R.error("Failed to delete order: " + id);
    }

    @RequestMapping("/cancel")
    public R cancelOrder(@RequestParam("id") int Id) {
        if (totalOrderService.cancelOrder(Id)) {
            return R.ok("Order canceled successfully!");
        }
        return R.error("Failed to cancel order: " + Id);
    }

    @PutMapping("finish/{Id}")
    public R finishOrder(@PathVariable int Id) {
        if (totalOrderService.finishOrder(Id)) {
            return R.ok("Order finished successfully!");
        }
        return R.error("Failed to finish order: " + Id);
    }

    @RequestMapping("/pay")
    public R payOrder(@RequestParam("id")int id) {
        if (totalOrderService.payOrder(id)) {
            return R.ok("Order payed successfully!");
        }
        return R.error("Failed to pay order: " + id);
    }

    @PutMapping("setPaymentMethod")
    public R SetOrderPaymentMethod(@RequestBody Map<String, String> orderMap) {
        int id = Integer.parseInt(orderMap.get("userId"));
        PaymentMethod paymentMethod = PaymentMethod.valueOf(orderMap.get("paymentMethod").toUpperCase());
        if (totalOrderService.setOrderPaymentMethod(id, paymentMethod)) {
            return R.ok("Payment method set successfully!");
        }
        return R.error("Failed to payment method: " + paymentMethod);
    }
}

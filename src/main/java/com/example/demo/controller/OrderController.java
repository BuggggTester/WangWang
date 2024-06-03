package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.R;
import com.example.demo.entity.Trip;
import com.example.demo.service.OrderService;
import com.example.demo.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    TripService tripService;
    @Autowired
    Order order;

    @ResponseBody
    @RequestMapping(value = "/create")
    public R createOrder(@RequestBody Map<String, String> orderMap) {
        try {
            String orderId = getUUID();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Date orderTime = ft.parse(orderMap.get("order_time"));
            int userId = Integer.parseInt(orderMap.get("userId"));
            String type = orderMap.get("type");
            String state = orderMap.get("state");
            String fromPlace = orderMap.get("fromPlace");
            String toPlace = orderMap.get("toPlace");
            double payment = Double.parseDouble(orderMap.get("payment"));
            Integer tripId = orderMap.containsKey("tripId") ? Integer.parseInt(orderMap.get("tripId")) : null;
            int carriage;
            //TODO: carriage之后弃用：变为随机分配
            Integer row;
            //TODO: row之后弃用：变为随机分配
            Character seat = orderMap.containsKey("seat") ? orderMap.get("seat").charAt(0) : null;
            Timestamp payTime = orderMap.containsKey("payTime") ? Timestamp.valueOf(orderMap.get("payTime")) : null;
            String payway = orderMap.get("payway");
            //获取Map数据
            //TODO: 验证值合理性
            if (tripId == null) {
                return R.error("tripId error");
            }
            //TODO：选座算法
            //获取该车次信息
            Trip trip = tripService.selectTripById(tripId);
            //获取所有订单信息
            List<Order> orders = orderService.selectOrdersByTripId(tripId);
            //处理车次的站点，为每个站点创建一个索引值
            HashMap<String, Integer> stationMap = new HashMap<>();
            String stationChain = trip.getTrip_chain();
            String[] stations = stationChain.split("-");
            for (int i = 0; i < stations.length; i++) {
                stationMap.put(stations[i], i);
            }
            int[][][][] p = new int[50][30][30][8];// 1st dimension: place; 2nd: carriage; 3rd: row; 4th: seat
            for (Order tmp : orders) {
                if (stationMap.get(tmp.getTo_place()) > stationMap.get(fromPlace) && stationMap.get(tmp.getFrom_place()) < stationMap.get(toPlace)) {//选取订单
                    for (int i = stationMap.get(tmp.getFrom_place()); i < stationMap.get(tmp.getTo_place()); i++) {
                        p[i][tmp.getCarriage()][tmp.getRow()][tmp.getSeat() - 'A' + 1] = 1;
                    }
                }
            }
            //init map 'p'
            //according to the order's seat, try to get an available seat equal to the order's.
            int from = stationMap.get(fromPlace);
            int to = stationMap.get(toPlace);
            int flag = 1;
            for (int i = 1; i <= trip.getNum_car(); i++) {
                for (int j = 1; j <= trip.getNum_row(); j++) {
                    for (int k = from; k <= to; k++) {
                        if (p[k][i][j][seat - 'A' + 1] == 1) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        carriage = i;
                        row = j;
                        orderService.createOrder(orderId, orderTime, userId, type, state, payment, tripId, carriage, row, seat, payTime, payway, fromPlace, toPlace);
                        return R.ok("order create success!");
                    } else {
                        flag = 1;
                    }
                }
            }
            flag = 1;
            //如果没有合适的座位，随机分配
            for (int i = 1; i <= trip.getNum_car(); i++) {
                for (int j = 1; j <= trip.getNum_row(); j++) {
                    for (int q = 1; q <= 6; q++) {
                        if (q == 5)
                            continue;
                        else {
                            for (int k = from; k < to; k++) {
                                if (p[k][i][j][q] == 1) {
                                    flag = 0;
                                    break;
                                }
                            }
                            if(flag == 1) {
                                carriage = i;
                                row = j;
                                seat = (char) (q + 'A' -1);
                                orderService.createOrder(orderId, orderTime, userId, type, state, payment, tripId, carriage, row, seat, payTime, payway, fromPlace, toPlace);
                                return R.ok("order create success!").put("carriage",carriage).put("row",row).put("seat", seat);
                            } else {
                                flag = 1;
                            }
                        }
                    }
                }
            }
        } catch (ParseException | NumberFormatException e) {
            return R.error("failed: " + e.getMessage());
        } catch (Exception e) {
            return R.error("failed: " + e.toString());
        }
            return R.ok("no seats");
    }

    @RequestMapping(value = "/select/history/{userId}")
    public List<Order> selectOrdersByUser(@PathVariable int userId) {
        List<Order> orders = orderService.selectOrdersByUser(userId);
        return orders;
    }

    @GetMapping(value = "/select/unfinished/{userId}")
    public List<Order> selectUnfinishedOrdersByUser(@PathVariable int userId) {
        return orderService.selectUnfinishedOrdersByUser(userId);
    }

    @GetMapping(value = "/select/all")
    public List<Order> selectAllOrders() {
        return orderService.selectAllOrders();
    }

    @GetMapping(value = "/select/date")
    public List<Order> selectByDate(@RequestBody Map<String, String> dateMap) {
        try {
            String date1 = dateMap.get("date1");
            String date2 = dateMap.get("date2");
            return orderService.selectByDate(date1, date2);
        } catch (Exception e) {
            System.out.println("发生错误" + e.toString());
            return null;
        }
    }

    @GetMapping(value = "/delete/{tripId}/{userId}")
    public R cancelOrderByCustomer(@PathVariable int tripId, @PathVariable int userId) {
        try {
            orderService.deleteOrderByCustomer(tripId, userId);
            return R.ok("删除成功！");
        } catch (Exception e) {
            return R.error(e.toString());
        }
    }

    @GetMapping(value = "/select/notdeparture/{userId}")
    public List<Order> selectNotDepartureOrdersByUser(@PathVariable int userId) {
        return orderService.selectNotDepartureOrdersByUser(userId);
    }

    @GetMapping(value = "/select/{from}/{to}")
    public List<Order> selectByLocation(@PathVariable String from, @PathVariable String to) {
        List<Order> orders = orderService.selectByLocation(from, to);
        return orders;
    }

    @GetMapping(value = "/select/notdeparture/{userId}/startTime")
    public List<Order> selectNotDepartureByStartTime(@PathVariable int userId, @RequestParam("startTime") String startTime) {
        List<Order> list = null;
        Timestamp time = Timestamp.valueOf(startTime);
        try {
            list = orderService.selectNotDepartureOrdersWithStartTime(userId, time);
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return list;
    }

    public static void main(String[] args) {

    }
}

package com.example.demo.dao;

import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO orders (order_time, user_id, state, payment, trip_id, carriage, `row`, seat, payway, from_place, to_place, pid, seat_type) " +
            "VALUES (#{order_time}, #{user_id}, #{state}, #{payment}, #{trip_id}, #{carriage}, #{row}, #{seat}, #{payway}, #{from_place}, #{to_place}, #{pid}, #{seat_type})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID() AS order_id", keyProperty = "order_id", before = false, resultType = int.class)
    void createOrder(Order order);
//    @Insert("INSERT INTO orders (order_time, user_id, state, payment, trip_id, carriage, `row`, seat, payway, from_place, to_place) " +
//            "VALUES (#{orderTime}, #{userId}, #{state}, #{payment}, #{tripId}, #{carriage}, #{row}, #{seat}, #{payway}, #{fromPlace}, #{toPlace})")
//    void createOrder(
//            @Param("orderTime") Date orderTime,
//            @Param("userId") int userId,
//            @Param("state") String state,
//            @Param("payment") double payment,
//            @Param("tripId") Integer tripId,
//            @Param("carriage") int carriage,
//            @Param("row") Integer row,
//            @Param("seat") Character seat,
//            @Param("payway") String payway,
//            @Param("fromPlace") String fromPlace,
//            @Param("toPlace") String toPlace
//    );
    //    @Select("select * from orders where user_id = #{userId}")
    //    List<Order> selectOrdersByUser(int userId);
    @Update("update orders set state = 'canceled' where order_id = #{orderId}")
    void deleteOrderByCustomer(int orderId, int userId);
    @Results({
            @Result(property = "trip", column = "trip_id",
                    one = @One(select = "com.example.demo.dao.TripMapper.selectTripById")),
            @Result(property = "passenger", column="pid", one =@One(select = "com.example.demo.dao.PassengerMapper.selectPassengerById"))
    })
    @Select("select * from orders where user_id = #{userId}")
    List<Order> selectOrdersByUser(@Param("userId") int userId);
    @Update("update orders set state = 'payed', payTime = current_timestamp where order_id = #{orderId}")
    void confirmOrderById(int orderId);
    @Select("select * from orders")
    List<Order> selectAllOrders();

    @Select("select * from orders where order_time between #{date1} and #{date2}")
    List<Order> selectByDate(String date1, String date2);

    @Select("select * from orders\n" +
            "join trips t on t.trip_id = orders.trip_id\n" +
            "where t.from_place=#{from} and t.to_place=#{to}")
    List<Order> selectByLocation(String from, String to);
    @Results({
            @Result(property = "trip", column = "trip_id",
                    one = @One(select = "com.example.demo.dao.TripMapper.selectTripById"))
    })
    @Select("select * from orders where user_id = #{userId} and state='notpayed'")
    List<Order> selectUnfinishedOrdersByUser(@Param("userId") int userId);
    @Results({
            @Result(property = "trip", column = "trip_id",
                one = @One(select = "com.example.demo.dao.TripMapper.selectTripById")
            )
    })
    @Select("select * from orders join trips t on t.trip_id = orders.trip_id where user_id = #{userId} " +
            " and t.start_time > now()")
    List<Order> selectNotDepartureOrdersByUser(@Param("userId") int userId);
    @Results({
            @Result(property = "trip", column = "trip_id",
                    one = @One(select = "com.example.demo.dao.TripMapper.selectTripById")
            )
    })
    @Select("select * from orders join trips t on t.trip_id = orders.trip_id where user_id = #{userId} " +
            "and state='payed' and t.start_time > now() and t.start_time = #{startTime}")
    List<Order> selectNotDepartureOrdersWithStartTime(@Param("userId") int userId, Timestamp startTime);
    @Results({
            @Result(property = "trip", column = "trip_id",
                    one = @One(select = "com.example.demo.dao.TripMapper.selectTripById")
            )
    })
    @Select("select * from orders join trips t on t.trip_id = orders.trip_id where user_id = #{userId} " +
            "and state='payed' and t.start_time > now() and order_time = #{orderTime}")
    List<Order> selectNotDepartureOrdersWithOrderTime(@Param("userId") int userId, Timestamp orderTime);

    @Select("select * from orders where trip_id = #{tripId}")
    List<Order> selectOrdersByTripId(int tripId);
}

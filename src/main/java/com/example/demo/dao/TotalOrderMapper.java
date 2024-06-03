package com.example.demo.dao;

import com.example.demo.common.constant.OrderType;
import com.example.demo.entity.TotalOrder;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface TotalOrderMapper {
    @Insert("INSERT INTO total_order (user_id, reservation_id, order_type, payment, order_create_time) " +
            "VALUES (#{userId}, #{reservationId}, #{orderType}, #{payment}, #{orderCreateTime})")
    void createOrder(TotalOrder totalOrder);

    @Select("SELECT * FROM total_order WHERE id = #{id}")
    TotalOrder getOrder(int id);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId}")
    List<TotalOrder> getAllOrders(int userId);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_type = 'TRAIN_TICKET'")
    List<TotalOrder> getAllTrainTicketOrders(int userId);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_type = 'HOTEL'")
    List<TotalOrder> getAllHotelOrders(int userId);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_type = 'TRAIN_MEAL'")
    List<TotalOrder> getAllTrainMealOrders(int userId);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_create_time > NOW() - INTERVAL 10 DAY")
    List<TotalOrder> getLast10DaysOrders(int userId);

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_type = #{orderType} AND order_create_time BETWEEN #{startDate} AND #{endDate}")
    List<TotalOrder> getOrdersByDateAndType(@Param("userId") int userId, @Param("orderType") OrderType orderType, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    @Update("UPDATE total_order SET user_id = #{userId}, reservation_id = #{reservationId}, order_type = #{orderType}, state = #{state}, payment = #{payment}, payment_method = #{paymentMethod}, pay_time = #{payTime}, finish_time = #{finishTime}, order_create_time = #{orderCreateTime} WHERE id = #{id}")
    void updateOrder(TotalOrder order);

    @Delete("DELETE FROM total_order WHERE id = #{id}")
    void deleteOrder(int id);

    @Delete("DELETE FROM total_order WHERE reservation_id = #{reservationId}")
    void cancelOrder(int reservationId);
}

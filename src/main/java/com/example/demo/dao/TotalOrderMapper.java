package com.example.demo.dao;

import com.example.demo.common.constant.OrderType;
import com.example.demo.common.constant.PaymentMethod;
import com.example.demo.entity.TotalOrder;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface TotalOrderMapper {

    //        以下是用户在购买界面需要调用的接口
    //  创建订单
    //  在创建reservation之前，如果reservation创建失败则不能创建订单。
    @Insert("INSERT INTO total_order (user_id, reservation_id, order_type, payment, order_create_time) " +
            "VALUES (#{userId}, #{reservationId}, #{orderType}, #{payment}, #{orderCreateTime})")
    void createOrder(TotalOrder totalOrder);
    // 支付时调用
    @Update("UPDATE total_order SET state = 'PAID', pay_time = NOW() WHERE id = #{id}")
    int payOrder(@Param("id") int id);
    // 支付前选择支付方式时调用
    @Update("UPDATE total_order SET payment_method = #{paymentMethod} WHERE id = #{id}")
    int setOrderPaymentMethod(int id, PaymentMethod paymentMethod);






    // 以下是查询接口（用于用户界面查询/管理员界面查询）
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

    @Select("SELECT * FROM total_order WHERE user_id = #{userId} AND order_type = #{orderType}")
    List<TotalOrder> getOrdersByType(@Param("userId") int userId, @Param("orderType") OrderType orderType);

    // 用户在（我的订单界面可选操作）（可能还有其他需求）
    @Delete("DELETE FROM total_order WHERE id = #{id}")
    int deleteOrder(int id);

    @Update("UPDATE total_order SET state = 'CANCELLED', finish_time = NOW() WHERE id = #{id}")
    int cancelOrder(@Param("id") int id);



    // 在时间到达后自动调用（暂时不知道怎么用）
    @Update("UPDATE total_order SET state = 'FINISHED', finish_time = NOW() WHERE id = #{id}")
    int finishOrder(@Param("id") int id);
    // ，。。。
    @Update("UPDATE total_order SET user_id = #{userId}, reservation_id = #{reservationId}, order_type = #{orderType}, state = #{state}, payment = #{payment}, payment_method = #{paymentMethod}, pay_time = #{payTime}, finish_time = #{finishTime}, order_create_time = #{orderCreateTime} WHERE id = #{id}")
    void updateOrder(TotalOrder order);

}

package com.example.demo.dao;


import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.Room;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


@Mapper
public interface HotelMapper {
    @Insert("INSERT INTO Hotel (name, address) VALUES (#{name}, #{address})")
    void createHotel(@Param("name") String name,
                     @Param("address") String address);

    @Insert("INSERT INTO Room (hotel_id, room_type, price) VALUES (#{hotel.id}, #{roomType}, #{price})")
    void setRoom(Room room);

    @Select("select * from Hotel where address = #{address}")
    List<Hotel> findHotelByAddress(String address);

    @Select("select * from Hotel where id = #{id}")
    Hotel findHotelById(int id);

    @Select("SELECT COUNT(*) FROM Room r " +
            "LEFT JOIN hotel_reservation res ON r.id = res.room_id " +
            "AND NOT ((res.check_in_date >= #{endDate} OR res.check_out_date <= #{startDate}))" +
            "WHERE r.hotel_id = #{hotelId} AND r.room_type = #{roomType} AND r.available = true ")
    int countAvailableRooms(@Param("hotelId") int hotelId,
                            @Param("roomType") RoomType roomType,
                            @Param("startDate") Date startDate,
                            @Param("endDate") Date endDate);

    @Select("SELECT r.id FROM Room r " +
            "LEFT JOIN hotel_reservation res ON r.id = res.room_id " +
            "AND NOT ((res.check_in_date >= #{endDate} OR res.check_out_date <= #{startDate})) " +
            "WHERE r.hotel_id = #{hotelId} AND r.room_type = #{roomType} AND r.available = true " +
            "ORDER BY r.id LIMIT 1")
    int getAvailableRoomId(@Param("hotelId") int hotelId,
                                @Param("roomType") RoomType roomType,
                                @Param("startDate") Date startDate,
                                @Param("endDate") Date endDate);

    @Insert("INSERT INTO hotel_reservation (user_id, room_id, check_in_date, check_out_date) " +
            "VALUES (#{user_id}, #{roomId}, #{checkInDate}, #{checkOutDate})")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="reservationId", before=false, resultType=int.class)
    int insertReservation(@Param("user_id") int userId,
                           @Param("roomId") int roomId,
                           @Param("checkInDate") Date checkInDate,
                           @Param("checkOutDate") Date checkOutDate);

    @Delete("DELETE FROM hotel_reservation WHERE id = #{reservationId}")
    void cancelRoom(@Param("reservationId") int reservationId);
}

package com.example.demo.dao;


import com.example.demo.common.constant.RoomType;
import com.example.demo.entity.hotel.Hotel;
import com.example.demo.entity.hotel.HotelReservation;
import com.example.demo.entity.hotel.Room;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;


@Mapper
public interface HotelMapper {
    @Insert("INSERT INTO hotel (name, address, picture_path) VALUES (#{name}, #{address},'default.png')")
    void createHotel(@Param("name") String name,
                     @Param("address") String address);

    @Insert("INSERT INTO room (hotel_id, room_type, price) VALUES (#{hotel.id}, #{roomType}, #{price})")
    void setRoom(Room room);
    @Select("select * from hotel where id = #{hotelId} limit 1")
    Hotel selectHotelById(int hotelId);
    @Select("SELECT * FROM hotel WHERE address LIKE CONCAT('%', #{address}, '%')")
    List<Hotel> findHotelByAddress(String address);

    @Select("select * from hotel where id = #{id}")
    Hotel findHotelById(int id);

    @Select("SELECT COUNT(*) FROM room r " +
            "LEFT JOIN hotel_reservation res ON r.id = res.room_id " +
            "AND NOT ((res.check_in_date >= #{endDate} OR res.check_out_date <= #{startDate}))" +
            "WHERE r.hotel_id = #{hotelId} AND r.room_type = #{roomType} ")
    int countAvailableRooms(@Param("hotelId") int hotelId,
                            @Param("roomType") RoomType roomType,
                            @Param("startDate") Date startDate,
                            @Param("endDate") Date endDate);

    @Select("SELECT r.id FROM room r " +
            "LEFT JOIN hotel_reservation res ON r.id = res.room_id " +
            "AND NOT ((res.check_in_date >= #{endDate} OR res.check_out_date <= #{startDate})) " +
            "WHERE r.hotel_id = #{hotelId} AND r.room_type = #{roomType} " +
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

    @Update("UPDATE ")
    void setHotelInfo(String name, String address, String description, String score);


    @Insert("insert into food_reservation (food_id, quantity, trip_id, user_id) values (#{foodId}, #{quantity}, #{tripId}, #{userId})")
    void createFoodReservation(int foodId, int quantity,int tripId, int userId);

    @Select("SELECT MIN(r.price) FROM room r WHERE r.hotel_id = #{hotelId}")
    Double getLowestPriceByHotelId(@Param("hotelId") int hotelId);
    @Results({
            @Result(property = "hotel", column="hotel_id", one = @One(select="com.example.demo.dao.HotelMapper.selectHotelById")),
    })

    @Select("select * from room where id = #{roomId} limit 1")
    Room selectRoomById(int roomId);

    @Update("update hotel set picture_path = #{picture} where id = #{hotelId}")
    void updatePictureById(String picture, int hotelId);
    @Results({
            @Result(property = "room", column = "room_id", one = @One(select = "com.example.demo.dao.HotelMapper.selectRoomById")),
            @Result(property = "user", column = "user_id", one = @One(select = "com.example.demo.dao.UserMapper.selectUserById"))
    })

    @Select("select * from hotel_reservation where id = #{hrId} limit 1")
    HotelReservation selectHotelReservationById(int hrId);


    @Select("SELECT * FROM room r " +
            "LEFT JOIN hotel_reservation res ON r.id = res.room_id " +
            "AND NOT ((res.check_in_date >= #{endDate} OR res.check_out_date <= #{startDate}))" +
            "WHERE r.hotel_id = #{hotelId}")
    List<Room> getAvailableRoomByHotelId (@Param("hotelId") int hotelId,
                                          @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate);

    @Select("select * from room where hotel_id == #{hotelId}")
    List<Room> getAvailableRoom(int hotelId);
}

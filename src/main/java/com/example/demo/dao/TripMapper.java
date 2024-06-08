package com.example.demo.dao;

import com.example.demo.entity.Trip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface TripMapper {
    @Select("select * from trips")
    List<Trip> selectAllTrips();
    @Select("SELECT * FROM trips WHERE trip_id = #{tripId}")
    Trip selectTripById(@Param("tripId") int tripId);
    @Insert("Insert into trips (train_id, start_time, end_time, from_place, to_place, num_car, num_row, trip_chain)" +
            "values ( #{trainId}, #{startTime}, #{endTime}, #{fromPlace}, #{toPlace}, #{numCar}, #{numRow}, #{trip_chain})")
    void createTrip(String trainId, Timestamp startTime, Timestamp endTime, String fromPlace, String toPlace, int numCar, int numRow);
    @Select("select * from trips where trip_chain like CONCAT('%', #{fromPlace}, '%', #{toPlace}, '%') and start_time" +
            " >= #{startTime} order by start_time")
    List<Trip> selectTripByPlaceAndTime(String fromPlace, String toPlace, Timestamp startTime);
    @Select("select count(*) from orders join trips t on t.trip_id = orders.trip_id where orders.trip_id = #{tripId} " +
            "and seat_type = #{type} and trip_chain like CONCAT('%', #{fromPlace}, '%', #{toPlace}, '%')")
    int countSoldSeats(int tripId, String fromPlace, String toPlace, String type);
    @Select("SELECT trip_id FROM trips WHERE train_id = #{train_id} AND DATE(start_time) = DATE(#{time}) LIMIT 1")
    int selectIdByTrainAndTime(String train_id, Timestamp time);
}

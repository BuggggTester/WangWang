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
    @Insert("Insert into trips (train_id, start_time, end_time, from_place, to_place, num_car, num_row)" +
            "values ( #{trainId}, #{startTime}, #{endTime}, #{fromPlace}, #{toPlace}, #{numCar}, #{numRow})")
    void createTrip(String trainId, Timestamp startTime, Timestamp endTime, String fromPlace, String toPlace, int numCar, int numRow);
    @Select("select * from trips where from_place = #{fromPlace} and to_place = #{toPlace} and start_time = #{startTime}")
    List<Trip> selectTripByPlaceAndTime(String fromPlace, String toPlace, Timestamp startTime);
}

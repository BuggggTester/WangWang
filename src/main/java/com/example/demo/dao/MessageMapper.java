package com.example.demo.dao;

import com.example.demo.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("insert into messages (send, receive, title, body, send_date, send_time) values (#{send}, #{receive}, #{title}, #{body}, #{send_date}, #{send_time});")
    void createMessage(String send, int receive, String title, String body, LocalDate send_date, LocalTime send_time);

    @Select("select * from messages where receive = #{receive} order by send_date DESC, send_time DESC;")
    List<Message> selectMessage(int receive);
    @Select("select count(*) from messages where receive = #{receive} order by send_date DESC, send_time DESC;")
    int selectNumberOfMessage(int receive);

    @Select("select count(*) from messages where receive = #{receive} and ifread = false order by send_date DESC, send_time DESC;")
    int selectNumberOfUnreadMessage(int receive);

    @Select("select * from messages where receive = #{receive} and ifread = false order by send_date DESC, send_time DESC;")
    List<Message> selectUnreadMessage(int receive);

    @Select("select * from messages where receive = #{receive} and send_date < #{send_date} order by send_date DESC, send_time DESC;")
    List<Message> selectMessageByDate(int receive, LocalDate send_date);

    @Select("select * from messages where receive = #{receive} and send_date < #{send_date} and ifread = false order by send_date DESC, send_time DESC;")
    List<Message> selectUnreadMessageByDate(int receive, LocalDate send_date);

    @Update("update * messages set ifread = ture where messafe_id = #{messafe_id};")
    void setRead(int message_id);
}

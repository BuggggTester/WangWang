package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.entity.R;
import com.example.demo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @PostMapping(value = "/create")
    public R createMessage(@RequestBody HashMap<String, String> messageMap){
        String send = messageMap.get("send");
        int receive = Integer.parseInt(messageMap.get("receive"));
        String title = messageMap.get("title");
        String body = messageMap.get("body");
        LocalDate send_date = LocalDate.now();
        LocalTime send_time = LocalTime.now();
        try {
            messageService.createMessage(send, receive, title, body, send_date, send_time);
            return R.ok("消息推送成功！");
        }catch (Exception e){
            return R.error("消息推送失败！");
        }
    }

    @GetMapping(value = "/baseselect")
    public List<Message> selectMessage(@RequestParam("receive") int receive){
        List<Message> messages = messageService.selectMessage(receive);
        return messages;
    }
    @GetMapping(value = "/numberselect")
    public int selectNumberOfMessage(@RequestParam("receive") int receive){
        int num = messageService.selectNumberOfMessage(receive);
        return num;
    }
    @GetMapping(value = "/unreadnumberselect")
    public int selectNumberOfUnreadMessage(@RequestParam("receive") int receive){
        int num = messageService.selectNumberOfUnreadMessage(receive);
        return num;
    }
    @GetMapping(value = "/unreadselect")
    public List<Message> selectUnreadMessage(@RequestParam("receive") int receive){
        List<Message> messages = messageService.selectUnreadMessage(receive);
        return messages;
    }
    @GetMapping(value = "/dateselect")
    public List<Message> selectMessageByDate(@RequestParam("receive") int receive, @RequestParam("send_date") LocalDate send_date){
        List<Message> messages = messageService.selectMessageByDate(receive, send_date);
        return messages;
    }
    @GetMapping(value = "/unreaddateselect")
    public List<Message> selectUnreadMessageByDate(@RequestParam("receive") int receive, @RequestParam("send_date") LocalDate send_date){
        List<Message> messages = messageService.selectUnreadMessageByDate(receive, send_date);
        return messages;
    }
    @PutMapping(value = "/setread")
    public R setRead(@RequestParam("message_id") int message_id){
        try {
            messageService.setRead(message_id);
            return R.ok("已设置为已读。");
        }catch (Exception e){
            return R.error(e.toString());
        }
    }
}

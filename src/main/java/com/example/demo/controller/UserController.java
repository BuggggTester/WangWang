package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/create")
    public R createCustomer(@RequestBody HashMap<String, String> userMap){
        String userName = userMap.get("userName");
        String password = userMap.get("password");
        int age = Integer.parseInt(userMap.get("age"));
        try {
            userService.createCustomer(age, password, userName);
            return R.ok("创建成功！");
        }catch (Exception e){
            return R.error(e.toString());
        }

    }
    @GetMapping(value="/select")
    public List<User> selectUserByName(@RequestParam("userName") String userName) {
        List<User> users = userService.selectUserByName(userName);
        return users;
    }
    @GetMapping(value="/setcookie")
    public R setCookie(HttpServletResponse response, @RequestParam("userName") String userName) {
        try {
            Cookie cookie = new Cookie("userName", userName);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            return R.ok("add cookie success!");
        }catch(Exception e){
            return R.error(e.toString());
        }

    }
    @GetMapping(value = "/readcookie")
    public R readCookie(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Map<String, Object> response = new HashMap<>();
            for(Cookie cookie: cookies) {
                response.put(cookie.getName(),cookie.getValue());
            }
            return R.ok(response);
        }
        return R.ok("no cookies");
    }
}

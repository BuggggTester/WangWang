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
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.example.demo.config.PathConfig.avatar;
import static com.example.demo.config.PathConfig.avatarUrl;

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
            User user = userService.selectUserByName(userName);
            if(user.getUser_name().equals(userName)){
                return R.error("customer already exists!");
            }
            userService.createCustomer(age, password, userName);
            return R.ok("创建成功！");
        }catch (Exception e){
            return R.error(e.toString());
        }

    }
    @RequestMapping(value="/login")
    public R loginValidate(@RequestBody HashMap<String, String> userMap) {
        String userName = userMap.get("userName");
        String password = userMap.get("password");
        try {
            User user = userService.selectUserByName(userName);
            if (userName.equals(user.getUser_name()) && password.equals(user.getPassword())) {
                Map<String, Object> res = new HashMap<>();
                res.put("userId", user.getUser_id());
                res.put("userName", user.getUser_name());
                res.put("password", user.getPassword());
                //判断用户头像是否存在，不存在返回默认头像default.png
                if(user.getAvatar() == null || user.getAvatar().isEmpty()) {
                    res.put("avatar", avatar + "default.png");
                }else {
                    res.put("avatar",avatar + user.getAvatar());
                }
                res.put("msg", "login success");
                return R.ok(res);
            }else{
                return R.ok("login failed");
            }
        }catch(Exception e) {
            return R.error(e.toString());
        }
    }
    @RequestMapping(value = "/select/userId")
    public User selectUserById(@RequestParam("userId") int userId) {
        User user = userService.selectUserById(userId);
        user.setAvatar(avatar + user.getAvatar());
        if(user.getUser_name().isEmpty()) {
            return new User();
        }else{
            return user;
        }
    }
    @GetMapping(value="/select/admin")
    public User selectAdmin(@RequestParam("userName") String userName) {
        return userService.selectAdmin(userName);
    }
    @GetMapping(value="/select")
    public R selectUserByName(@RequestParam("userName") String userName) {
        User user = userService.selectUserByName(userName);
        Map<String, Object> userMap = new HashMap<>();
        if(user.getUser_name().isEmpty()){
            userMap.put("user_name", "");
            userMap.put("password","");
            return R.ok(userMap);
        }
        userMap.put("user_name", userName);
        userMap.put("password", user.getPassword());
        return R.ok(userMap);
    }
    @RequestMapping(value = "/update/avatar")
    public R updateAvatar(@RequestParam("avatar") MultipartFile file, @RequestParam("userId") int userId) {

        User user = userService.selectUserById(userId);

        String origin = user.getAvatar();
        if(!origin.equals("default.png")){
            File file1 = new File(avatarUrl + origin);
            file1.delete();//如果原来有头像，则删除
        }
        String filePath = avatarUrl;
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileNewName = UUID.randomUUID() + fileType;
        String finalName = fileNewName;
        userService.updateAvatarById(finalName, userId);
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath+ fileNewName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return R.error(e.toString());
        }
        return R.ok("上传成功！");
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

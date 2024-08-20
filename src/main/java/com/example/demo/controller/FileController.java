package com.example.demo.controller;

import com.example.demo.entity.R;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
@CrossOrigin
@Slf4j
@Controller
@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private UserService userService;
    @PostMapping(value="/upload")
    public R uploadFile(@RequestParam("File") MultipartFile file) {
        String filePath = "./src/main/resources/images/";
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String fileNewName = UUID.randomUUID() + fileType;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileNewName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return R.error(e.toString());
        }
        return R.ok("上传成功！");
    }
    @RequestMapping(value="/get")
    public R getAvatarByUserId(@RequestParam("userName") String userName) {
        User user = userService.selectUserByName(userName);
        if(user == null || user.getUser_name().isEmpty()) {
            return R.error("No user searched!");
        }
        String url = user.getAvatar();
        if(url.isEmpty()||url.equals("")) {
            return R.ok("no avatar");
        }
        return R.ok("success!").put("avatar",url);
    }
}

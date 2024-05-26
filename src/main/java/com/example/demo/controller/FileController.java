package com.example.demo.controller;
import com.example.demo.entity.R;
import com.example.demo.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/common/file")
public class FileController {
    @Autowired
    @Qualifier("localFileService")
    private FileService fileService;

    /**
     * 批量文件上传接口
     */
    @RequestMapping("/uploadList")
    public R uploadBatch(@RequestParam("imageList") List<MultipartFile> fileList) throws IOException {
        ArrayList<String> ans = new ArrayList<>();
        for (MultipartFile file : fileList) {
            String url = fileService.uploadFile(file, UUID.randomUUID().toString().substring(0, 10)
                    + "-" + file.getOriginalFilename());
            ans.add(url);
        }
        return R.ok().put("data", ans);
    }

    /**
     * 上传接口
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("image") MultipartFile file) throws IOException {
        String url = fileService.uploadFile(file, UUID.randomUUID().toString().substring(0, 10)
                + "-" + file.getOriginalFilename());
        return R.ok().put("data", url);
    }

    /**
     * 下载接口
     */
    @RequestMapping("/download/{fileName}")
    public void download(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        fileService.downloadFile(fileName, request, response);
    }

    /**
     * 删除接口
     */
    @RequestMapping("/delete")
    public R deleteFile(@RequestParam String fileName) {
        boolean flag = fileService.deleteFile(fileName);
        return R.ok().put("data", flag);
    }
}


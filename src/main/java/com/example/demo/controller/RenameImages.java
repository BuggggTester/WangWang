package com.example.demo.controller;

import java.io.File;

import static com.example.demo.config.PathConfig.hotelUrl;
import static com.example.demo.config.PathConfig.roomUrl;

public class RenameImages {
    public static void renameImagesInFolder(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            int count = 1;
            for (File file : files) {
                if (file.isFile()) {
                    String newName = folderPath + File.separator +"a"+ count + ".jpg";
                    file.renameTo(new File(newName));
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String folderPath = roomUrl; // 替换为你的文件夹路径
        renameImagesInFolder(folderPath);
        System.out.println("Images renamed and extensions changed successfully!");
    }
}

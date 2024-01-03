package com.kh.zangzac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;

@Controller
public class ChatController {
   
   
   private final ImageStorage imageStorage;

    @Autowired
    public ChatController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
    
    
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
       
       name="choi";
       
        imageStorage.saveImage(file, name);
        
        
        return "index";
    }
    
    @PostMapping("/delete")
    public String uploadImage() {
       
       imageStorage.deleteImage("cb1d55f4-e2c4-42b1-96b9-948d8883b3c5.png","choi");
        
        return "index";
    }


}
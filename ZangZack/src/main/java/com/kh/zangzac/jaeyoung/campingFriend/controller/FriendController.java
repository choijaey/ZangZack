package com.kh.zangzac.jaeyoung.campingFriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.jaeyoung.campingFriend.model.vo.CampingFriend;


@Controller
public class FriendController {
	
	private final ImageStorage imageStorage;

    @Autowired
    public FriendController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

	@GetMapping("champingFriend.jy")
    public String champingFriendPage() {
    	return "views/jaeyoung/champingFriendPage";
    }
	
	@GetMapping("campingFriendWriteView.jy")
	public String campingFriendWriteView() {
		
		return "views/jaeyoung/champingFriendWrite";
		
	}
	
	@GetMapping("champingFriendWrite.jy")
	public String champingFriendWrite(@ModelAttribute CampingFriend cf,@RequestParam("file")MultipartFile file) {
		
		return "redirect:champingFriend.jy";
		
	}
	
	
	
}

package com.kh.zangzac.jaeyoung.chat.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.jaeyoung.chat.model.service.ChatService;
import com.kh.zangzac.jaeyoung.chat.model.vo.Chatter;

import lombok.Getter;
import lombok.Setter;

@Controller
@Getter
@Setter
public class ChatController {
	
	
	private final ImageStorage imageStorage;
	
	//채팅자들 저장하는 변수
	private ArrayList<ArrayList<Chatter>> chatterList = new ArrayList<ArrayList<Chatter>>();
	
	@Autowired
	ChatService cService;

    @Autowired
    public ChatController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
    
    
    @GetMapping("chatListView.jy")
    public String chatListView() {
    	
    	
    	
    	return "views/jaeyoung/chatRoomList";
    	
    }
    
    @GetMapping("chatRoom.jy")
    public String chatRoom() {
    	
        
        return "views/jaeyoung/chatRoomPage";
    }


}

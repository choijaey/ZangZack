package com.kh.zangzac.jaeyoung.chat.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.jaeyoung.chat.model.service.ChatService;
import com.kh.zangzac.jaeyoung.chat.model.vo.ChatRoom;

import lombok.Getter;
import lombok.Setter;

@Controller
@Getter
@Setter
public class ChatController {
	
	
	private final ImageStorage imageStorage;
	
	
	@Autowired
	ChatService cService;

    @Autowired
    public ChatController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
    
    
    @GetMapping("chatListView.jy")
    public String chatListView(Model model) {
    	// 채팅방 정보들 가져와서 보내기
    	ArrayList<ChatRoom> list = cService.chatRoomList();
    	model.addAttribute("list", list);
    	return "views/jaeyoung/chatRoomList";
    	
    }
    
    @GetMapping("/enterChatRoom.jy")
    public String chatRoom(@RequestParam("chatRoomId") String roomName) {
    	
    	
    	
        //chatRoomId
        return "views/jaeyoung/chatRoomPage";
    }


}

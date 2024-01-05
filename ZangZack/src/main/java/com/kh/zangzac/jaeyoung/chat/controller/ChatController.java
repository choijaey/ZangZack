package com.kh.zangzac.jaeyoung.chat.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.jaeyoung.chat.ChatFileManager;
import com.kh.zangzac.jaeyoung.chat.model.service.ChatService;
import com.kh.zangzac.jaeyoung.chat.model.vo.ChatRoom;
import com.kh.zangzac.jaeyoung.chat.model.vo.Chatter;


@Controller
public class ChatController {
	
	
	private final ImageStorage imageStorage;
	
	
	@Autowired
	ChatService cService;

    @Autowired
    public ChatController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
    
    @Autowired
    ChatFileManager cFileManager;
    
    
    @GetMapping("chatListView.jy")
    public String chatListView(Model model) {
    	// 채팅방 정보들 가져와서 보내기
    	ArrayList<ChatRoom> list = cService.chatRoomList();
    	model.addAttribute("list", list);
    	return "views/jaeyoung/chatRoomList";
    	
    }
    
    @GetMapping("/enterChatRoom.jy")
    public String chatRoom(@RequestParam("chatRoomId") String roomName,Model model) {
    	
    	//채팅참가자 불러오기
    	ArrayList<Chatter> list = cService.chatterList(roomName);
    	
    	//채팅내역 불러오기
    	//예시: "1" 방의 채팅 로그 읽어오기
    	//unReadChatterCount
        JSONArray chatLogs = cFileManager.readChatLog(roomName);
        
        
        
    	model.addAttribute("roomName", roomName);
    	model.addAttribute("list", list);
    	model.addAttribute("chatLogs", chatLogs);

    	
        //chatRoomId
        return "views/jaeyoung/chatRoomPage";
    }


}

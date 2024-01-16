package com.kh.zangzac.jaeyoung.eventBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.zangzac.jaeyoung.eventBoard.model.service.EventBoardService;

@Controller
public class EventBoardController {
	
	@Autowired
	EventBoardService ebService;
	
	@GetMapping("eventBoardListView.jy")
	public String eventBoardListView() {
		
		return "views/jaeyoung/champingEvent"; 
	}
	
	@GetMapping("champingEventAdminWrite.jy")
	public String champingEventAdminWrite() {
		
		return "views/jaeyoung/admin/champingEventAdminWrite"; 
	}
	
	
	

}

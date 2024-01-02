package com.kh.zangzac.seongun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CampBoardController {
	
	
	@GetMapping("campBoard.su")
	public String campBoardListView() {
		return "views/seongun/boardList";
	}
	
	@GetMapping("recipe.su")
	public String recipe() {
		return "views/seongun/recipe";
	}
}

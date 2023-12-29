package com.kh.zangzac.yoonseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CampController {
	
	
	@GetMapping("campSearch.fo")
	public String campSearch() {
		return "views/yoonseo/campSearch";
	}
	
	@GetMapping("campList.fo")
	public String campList() {
		return "views/yoonseo/campList";
	}
	
	@GetMapping("campDetail.fo")
	public String campDetail() {
		return"views/yoonseo/campDetail";
	}

}

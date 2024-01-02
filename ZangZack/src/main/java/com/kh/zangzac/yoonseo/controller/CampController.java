package com.kh.zangzac.yoonseo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class CampController {
	
	
	@GetMapping("campSearch.ys")
	public String campSearch() {
		return "views/yoonseo/campSearch";
	}
	
	@GetMapping("campList.ys")
	public String campList() {
		return "views/yoonseo/campList";
	}
	
	@GetMapping("campDetail.ys")
	public String campDetail() {
		return"views/yoonseo/campDetail";
	}
	
	@GetMapping("detailWrite.ys")
	public String detailWriter() {
		return "views/yoonseo/detailWrite";
	}
	@GetMapping("recommendWrite.ys")
	public String recommendWrite () {
		return "views/yoonseo/recommendWrite";
	}
	

}

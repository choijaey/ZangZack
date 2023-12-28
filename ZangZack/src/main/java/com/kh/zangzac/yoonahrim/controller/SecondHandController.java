package com.kh.zangzac.yoonahrim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondHandController {
	
	@GetMapping("edit.do")
    public String editPage() {
        return "/editSecondHand";
    }
	
	@GetMapping("detail.do")
	public String detailPage() {
		return "/secondHandDetail";
	}
	
	@GetMapping("write.do")
	public String writePage() {
		return "/writeSecondHand";
	}
	
	
	@GetMapping("selectCategory.do")
	public String selectCategory() {
		return "/selectCategory";
	}
	
	
	@GetMapping("chating.do")
	public String chating() {
		return "/chatingRoom";
	}
}

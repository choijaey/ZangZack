package com.kh.zangzac.yoonahrim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondHandController {
	
	@GetMapping("secondHand.do")
	public String secondHand() {
		return "yoonahrim/secondHandList";
	}
	
	@GetMapping("edit.do")
    public String editPage() {
        return "yoonahrim/editSecondHand";
    }
	
	@GetMapping("detail.do")
	public String detailPage() {
		return "yoonahrim/secondHandDetail";
	}
	
	@GetMapping("write.do")
	public String writePage() {
		return "yoonahrim/writeSecondHand";
	}
	
	
	@GetMapping("selectCategory.do")
	public String selectCategory() {
		return "yoonahrim/selectCategory";
	}
	
	
	@GetMapping("chating.do")
	public String chating() {
		return "yoonahrim/chatingRoom";
	}
}

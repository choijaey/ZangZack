package com.kh.zangzac.yoonahrim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.zangzac.yoonahrim.model.service.secondHandService;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

@Controller
public class SecondHandController {
	
	@Autowired
	private secondHandService spService;
	
	
	@GetMapping("secondHand.ah")
	public String secondHand() {
		return "views/yoonahrim/secondHandList";
	}
	
	//중고 게시글 수정
	@GetMapping("edit.ah")
    public String editPage() {
        return "views/yoonahrim/editSecondHand";
    }
	
	@PostMapping("update.ah")
	public String updatePage(@ModelAttribute secondHandProduct sp, @RequestParam("spAddressStreet") String spAddressStreet, 
			 @RequestParam("spAddressDetail") String spAddressDetail, Model model) {
		
		String spAddress = null;
		if(!spAddressStreet.equals("")) {
			spAddress = spAddressStreet + " " + spAddressDetail;
		}
		sp.setSpAddress(spAddress);
		System.out.println(sp);
		
		int result = spService.updateSeconHand(sp);
			if(result > 0) {
				return "views/yoonahrim/secondHandList";
			} else {
				model.addAttribute("error", "게시판 등록에 실패했습니다.");
		        return "views/yoonahrim/writeSecondHand";
			}
	}
	
	//상세페이지
	@GetMapping("detail.ah")
	public String detailPage() {
		return "views/yoonahrim/secondHandDetail";
	}
	
	
	//중고 게시글 작성
	@GetMapping("write.ah")
	public String writePage() {
		return "views/yoonahrim/writeSecondHand";
	}
	
	@PostMapping("/insert.ah")
	public String insertPage(@ModelAttribute secondHandProduct sp, @RequestParam("spAddressStreet") String spAddressStreet, 
							 @RequestParam("spAddressDetail") String spAddressDetail, Model model) {
		String spAddress = null;
		if(!spAddressStreet.equals("")) {
			spAddress = spAddressStreet + " " + spAddressDetail;
		}
		sp.setSpAddress(spAddress);
		System.out.println(sp);
		
		int result = spService.insertSeconHand(sp);
			if(result > 0) {
				return "views/yoonahrim/secondHandList";
			} else {
				model.addAttribute("error", "게시판 등록에 실패했습니다.");
		        return "views/yoonahrim/writeSecondHand";
			}
	}
	
	
	@GetMapping("selectCategory.ah")
	public String selectCategory() {
		return "views/yoonahrim/selectCategory";
	}
	
	
	@GetMapping("chating.ah")
	public String chating() {
		return "views/yoonahrim/chatingRoom";
	}
}

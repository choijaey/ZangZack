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
	
	//중고 메인 페이지로 이동
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
		
		//로그인한 User의 게시물을 update 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		
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
	
	//중고 게시글 상세페이지
	@GetMapping("detail.ah")
	public String detailPage() {
		//정보를 가져와서 list에 담은 다음 화면에 뿌리면 됨.
		
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
		//로그인한 User의 게시물을 insert 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		
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
	
	//체크리스트 화면이동
	@GetMapping("selectCategory.ah")
	public String selectCategory() {
		//화면 이동
		
		return "views/yoonahrim/selectCategory";
	}
	
	//채팅 화면 이동
	@GetMapping("chating.ah")
	public String chating() {
		return "views/yoonahrim/chatingRoom";
	}
}

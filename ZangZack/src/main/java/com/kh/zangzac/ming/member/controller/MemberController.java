package com.kh.zangzac.ming.member.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.zangzac.ming.member.model.exception.MemberException;
import com.kh.zangzac.ming.member.model.service.MemberService;
import com.kh.zangzac.ming.member.model.vo.Member;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@GetMapping("signUp.me")
	public String sign() {
		return "views/ming/member/sign";
	}
	
	@PostMapping("/insertMember.me")
	public String insertMember(@ModelAttribute Member m, @RequestParam("sample6_postcode") String sample6_postcode,
								@RequestParam("sample6_address") String sample6_address,@RequestParam("sample6_detailAddress") String sample6_detailAddress,
								@RequestParam("sample6_extraAddress") String sample6_extraAddress, @RequestParam("existingNickname") String existingNickname) {
		
		String address = null;
		if(!sample6_postcode.trim().equals("")) {
			 address = sample6_postcode + "@" + sample6_address + "@" + sample6_detailAddress + "@" + sample6_extraAddress;
		}
		m.setAddress(address);
		
		 m.setNickName(existingNickname + "#" + generateRandomNumbers()); // 랜덤닉네임
		m.setPwd(bcrypt.encode(m.getPwd()));
		m.getId();
		
		int result = mService.insertMember(m);
		if(result > 0) {
		   return "redirect:home.do";
	    } else {
	    	throw new MemberException("회원가입을 실패하였습니다.");
	    }
	}
	
	private String generateRandomNumbers() {
	    // 4자리 랜덤 숫자 생성
	    Random random = new Random();
	    int randomNum = random.nextInt(9000) + 1000; // 1000 이상 9999 이하의 숫자
	    return String.valueOf(randomNum);
	}
	
	@GetMapping("agreement.me")
	public String agreement() {
		return "views/ming/member/agreement";
	}
	
}

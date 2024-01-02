package com.kh.zangzac.ming.member.controller;

import java.util.Random;

import org.mybatis.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.zangzac.ming.member.model.exception.MemberException;
import com.kh.zangzac.ming.member.model.service.MemberService;
import com.kh.zangzac.ming.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	//private Logger logger = LoggerFactory.getLogger(MemberController.class); 왜인지 오류가 남 나중에 해야지 
	
	@GetMapping("signUp.me")
	public String sign() {
		return "views/ming/member/sign";
	}
	
	//회원가입
	@PostMapping("/insertMember.me")
	public String insertMember(@ModelAttribute Member m, @RequestParam("sample6_postcode") String sample6_postcode,
								@RequestParam("sample6_address") String sample6_address,@RequestParam("sample6_detailAddress") String sample6_detailAddress,
								@RequestParam("sample6_extraAddress") String sample6_extraAddress, @RequestParam("existingNickname") String existingNickname) {
		
		String address = null;
		if(!sample6_postcode.trim().equals("")) {
			 address = sample6_postcode + "@" + sample6_address + "@" + sample6_detailAddress + "@" + sample6_extraAddress;
		}
		m.setMemberAddress(address);
		
		 m.setMemberNickName(existingNickname + "#" + generateRandomNumbers()); // 랜덤닉네임
		m.setMemberPwd(bcrypt.encode(m.getMemberPwd()));
		m.getMemberId();
		
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
	
	//개인정보 동의
	@GetMapping("agreement.me")
	public String agreement() {
		return "views/ming/member/agreement";
	}
	
	//로그인
	@PostMapping("login.me")
	public String loginUser(@ModelAttribute Member m , Model model) {
		System.out.println(m);
		Member loginUser = mService.login(m);
		
		if(loginUser != null) {
			if(bcrypt.matches(m.getMemberPwd(), loginUser.getMemberPwd())) {
				model.addAttribute("loginUser",loginUser);
				return "views/ming/member/agreement";
				
			}else {
				model.addAttribute("msg","실패");
				return "sign";
			}
			
		}else {
			model.addAttribute("msg","실패");
			return "sign";
		}
		
		
	}
	
	//로그아웃
	@GetMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
}

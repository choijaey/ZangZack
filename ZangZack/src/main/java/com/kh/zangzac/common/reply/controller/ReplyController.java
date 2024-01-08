package com.kh.zangzac.common.reply.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.zangzac.common.reply.model.service.ReplyService;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.ming.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;

@SessionAttributes("loginUser")
@Controller
public class ReplyController {
	@Autowired
	private ReplyService rService;
	
	@GetMapping("insertReply.rep")
	public String insertReply(@ModelAttribute("Reply") Reply reply, HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			 reply.setMemberId(loginUser.getMemberId()); 
		}
	    
		ArrayList<Reply> list = rService.selectReply(reply);
		int result = rService.insertReply(reply);
		return null;
	}
}

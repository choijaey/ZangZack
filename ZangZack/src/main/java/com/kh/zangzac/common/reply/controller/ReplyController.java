package com.kh.zangzac.common.reply.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.reply.model.service.ReplyService;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.ming.member.model.vo.Member;

import jakarta.servlet.http.HttpSession;

@SessionAttributes("loginUser")
@Controller
public class ReplyController {
	@Autowired
	private ReplyService rService;
	
	@PostMapping("insertReply.rep")
	@ResponseBody
	public Map<String, Object> insertReply(@ModelAttribute("Reply") Reply reply,@RequestParam(value="page", defaultValue="1") int page, HttpSession session) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			 reply.setMemberId(loginUser.getMemberId()); 
		}
	    
		int result = rService.insertReply(reply);
		ArrayList<Reply> list = rService.selectReply(reply);
		int listCount = countReply(reply);
	    PageInfo pi = Pagination.getPageInfo(page, listCount, 5);
	    Map<String, Object> map = new HashMap<>();
	    map.put("list", list);
	    map.put("rPi", pi);
	    return map;
	}

	private int countReply(Reply reply) {
		return rService.countReply(reply);
	}
	
}

package com.kh.zangzac.common.heart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.zangzac.common.heart.model.service.HeartService;
import com.kh.zangzac.common.heart.model.vo.Heart;

@Controller
public class HeartController {
	@Autowired
	private HeartService hService;
	
	@PostMapping("selectHeart.like")
	@ResponseBody
	public void selectHeart(@ModelAttribute Heart h) {
		hService.selectHeart(h);
	}
}

package com.kh.zangzac.common.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.yoonseo.camp.model.service.CampService;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

@Controller
public class BoardCondition {
	
	@Autowired
	private CampService cService;
	
	public SelectCondition selectBoard(int x, int y) {
		SelectCondition b = new SelectCondition();
		b.setBoardNo(x);
		b.setBoardType(y);
		return b;
	}
	
	@GetMapping("/")
	public String main(Model model) {
		
		String recomendation = "Y";
		ArrayList<CampingGround> list = cService.getMainList(recomendation);
		System.out.println(list.size());
		
		ArrayList<Integer> intArrayList = new ArrayList<>();

		for (CampingGround cg : list) {
		    intArrayList.add(cg.getCgNo());
		}
		System.out.println(intArrayList);
		
		
 		ArrayList<CampingGround> photoList = cService.selectMainPhoto(intArrayList);
 		
 		System.out.println(photoList);
 		
		if(list != null) {
			model.addAttribute("photoList", photoList);
			return"index";
		}else {
			return"index";
		}
	
	}
}

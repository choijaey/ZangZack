package com.kh.zangzac.seongun.common;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

@Controller
public class WorkController {
	
	public void addModel(Model model, PageInfo pi, ArrayList<CampBoard> list, String msg, String loc) {
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		model.addAttribute("msg", msg);
	    model.addAttribute("loc", loc);
	}

	public Attachment setAttachment(String[] returnArr, int i) {
		Attachment a = new Attachment();
		if(i==0) {
			a.setPhotoRename(returnArr[0]);
			a.setPhotoPath(returnArr[1]);
			a.setBoardType(1);
			a.setPhotoLevel(0);
		}else {
			a.setPhotoRename(returnArr[0]);
			a.setPhotoPath(returnArr[1]);
			a.setBoardType(1);
			a.setPhotoLevel(1);
		}
		
		return a;
	}
	
}

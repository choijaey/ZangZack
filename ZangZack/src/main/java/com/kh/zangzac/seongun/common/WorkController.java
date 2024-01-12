package com.kh.zangzac.seongun.common;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.heart.model.vo.Heart;
import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.controller.ReplyController;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

@Controller
public class WorkController {
	@Autowired
	private ReplyController rController;
	
	public void addListModel(Model model, PageInfo pi, ArrayList<CampBoard> list, String msg, String loc) {
		model.addAttribute("pi", pi);
		model.addAttribute("list", list);
		model.addAttribute("msg", msg);
	    model.addAttribute("loc", loc);
	}

	public Photo setAttachment(String[] returnArr, int i) {
		Photo a = new Photo();
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

	public SelectCondition selectBoard(int cbNo, int i) {
		SelectCondition b = new SelectCondition();
		b.setBoardNo(cbNo);
		b.setBoardType(i);
		return b;
	}

	public void BoardDetail(Model model,CampBoard bList, int maxPage, ArrayList<Photo> pList, int page) {
		if(bList != null) {
			model.addAttribute("bList", bList);
			model.addAttribute("pList", pList);
			model.addAttribute("page", page);
			model.addAttribute("maxPage", maxPage);
		}
		
	}

	public int countReply(int cbNo, int i) {
		SelectCondition b = selectBoard(cbNo, i);
		
		int listCount = rController.countReply(b);
		
		return Pagination.getReplyPageInfo(1, listCount, 10).getMaxPage();
	}

	public Heart addHeart(int cbNo, String memberId, int i) {
		Heart h = new Heart();
		h.setBoardNo(cbNo);
		h.setMemberId(memberId);
		h.setBoardType(i);
		return h;
	}
	
}

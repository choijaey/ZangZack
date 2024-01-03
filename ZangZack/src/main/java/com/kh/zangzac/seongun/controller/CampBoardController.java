package com.kh.zangzac.seongun.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.model.service.CampBoardService;
import com.kh.zangzac.seongun.model.vo.CampBoard;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CampBoardController {
	
	@Autowired
	private CampBoardService cService;
	
	@GetMapping("campBoard.su")
	public String campBoardListView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		int listCount = cService.getListCount(1);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,1);
		
		return "views/seongun/boardList";
	}
	
	@GetMapping("recipe.su")
	public String recipe() {
		return "views/seongun/recipe";
	}
}

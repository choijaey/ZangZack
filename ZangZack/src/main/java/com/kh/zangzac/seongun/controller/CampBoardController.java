package com.kh.zangzac.seongun.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.ming.member.model.vo.Member;
import com.kh.zangzac.seongun.model.service.CampBoardService;
import com.kh.zangzac.seongun.model.vo.CampBoard;

import jakarta.servlet.http.HttpServletRequest;

@SessionAttributes("loginUser")
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
		if(!list.isEmpty()) {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", null);
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/listBoard";
		}else {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", "작성된 게시판이 없습니다!");
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/listBoard";
		}
	}
	
	@GetMapping("cardBoard.su")
	public String campBoardCardView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		int listCount = cService.getListCount(0);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,1);
		if(!list.isEmpty()) {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", null);
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/cardBoard";
		}else {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", "작성된 게시판이 없습니다!");
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/cardBoard";
		}
	}
	
	@GetMapping("albumBoard.su")
	public String campBoardAlbumView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		int listCount = cService.getListCount(0);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,1);
		if(!list.isEmpty()) {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", null);
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/albumBoard";
		}else {
			model.addAttribute("pi",pi);
			model.addAttribute("list", list);
			model.addAttribute("msg", "작성된 게시판이 없습니다!");
			model.addAttribute("loc", request.getRequestURI());
			return "views/seongun/campboard/albumBoard";
		}
	}
	
	@GetMapping("recipe.su")
	public String recipe() {
		return "views/seongun/recipe";
	}
	
	@GetMapping("writeCampBoardView.su")
	public String writeCampBoard() {
		return "views/seongun/campboard/writeBoard";
	}
	
	@PostMapping("insertCampBoard.su")
	public String insertCampBoard(@ModelAttribute CampBoard board, @RequestParam("file") ArrayList<MultipartFile> files,HttpServletRequest request) {
		board.setMemberId(((Member)request.getSession().getAttribute("loginUser")).getMemberId());
		System.out.println(board);
		
		for(MultipartFile f : files) {
			System.out.println(f);
		}
		return "redirect:/campBoard.su";
	}
}

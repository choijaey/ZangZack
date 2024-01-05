package com.kh.zangzac.seongun.campboard.controller;

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

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.ming.member.model.vo.Member;
import com.kh.zangzac.seongun.campboard.model.service.CampBoardService;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;
import com.kh.zangzac.seongun.common.WorkController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@SessionAttributes("loginUser")
@Controller
public class CampBoardController {
	
	private final ImageStorage imageStorage;
	
	@Autowired
	public CampBoardController(ImageStorage imageStorage) {
		this.imageStorage = imageStorage;
	}
	
	@Autowired
	private CampBoardService cService;
    
	@Autowired
	private WorkController sWork;
	
	@GetMapping("campBoard.su")
	public String campBoardListView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		int listCount = cService.getListCount(0);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,0);
		
		String msg = list.isEmpty() ? "작성된 게시판이 없습니다!" : null;
		sWork.addModel(model, pi, list, msg, request.getRequestURI());
		return "views/seongun/campboard/listBoard";
	}
	
	@GetMapping("cardBoard.su")
	public String campBoardCardView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		int listCount = cService.getListCount(0);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,0);
		
		String msg = list.isEmpty() ? "작성된 게시판이 없습니다!" : null;
		sWork.addModel(model, pi, list, msg, request.getRequestURI());
	    
		return "views/seongun/campboard/cardBoard";
	}
	
	@GetMapping("albumBoard.su")
	public String campBoardAlbumView(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		int listCount = cService.getListCount(0);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampBoard> list = cService.selectBoardList(pi,0);
		
		System.out.println(list.toString());
		String msg = list.isEmpty() ? "작성된 게시판이 없습니다!" : null;
		sWork.addModel(model, pi, list, msg, request.getRequestURI());
		
	    return "views/seongun/campboard/albumBoard";
	}
	
	@GetMapping("recipe.su")
	public String recipe() {
		return "views/seongun/recipe";
	}
	
	@GetMapping("selectBoard.su")
	public String campBoardView(@RequestParam("cbNo") int cbNo, @RequestParam(value="page", defaultValue="1") int page, HttpSession session,Model model) {
		Member loginUser = (Member)session.getAttribute("loginUser");
		Reply sendReply = new Reply();
		sendReply.setBoardNo(cbNo);
		sendReply.setBoardType(4);
		
		String id = null;
		if(loginUser != null) {
			id = loginUser.getMemberId(); 
		}
	    
	    CampBoard bList = cService.selectBoard(cbNo, id);
	    ArrayList<Reply> rList = cService.selectReply(sendReply);
		
	    if(bList != null) {
			model.addAttribute("b", bList);
			model.addAttribute("page", page);
			model.addAttribute("list", rList);
			return "views/seongun/campboard/boardDetail";
		}else {
			
			return "redirect:/campBoard.su";
		}
	}
	
	@PostMapping("insertCampBoard.su")
	public String insertCampBoard(@ModelAttribute CampBoard board, @RequestParam("file") ArrayList<MultipartFile> files,HttpServletRequest request, Model model) {
		board.setMemberId(((Member)request.getSession().getAttribute("loginUser")).getMemberId());
		int resultB = 0;
		int resultA = 0;
		
		ArrayList<Attachment> fileList = new ArrayList<>();
		
		for(int i=0; i<files.size(); i++) {
			MultipartFile upload = files.get(i); //파일 하나씩 뽑아오기.
			String[] returnArr = imageStorage.saveImage(upload, "seongun");
			
			if(returnArr != null) {
				Attachment a = sWork.setAttachment(returnArr, i);
				
				fileList.add(a);
			}
			
		}
		if(fileList.isEmpty()) {
			resultB = cService.insertCampBoard(board);
		}else {
			resultB = cService.insertCampBoard(board);
			for(Attachment a : fileList) {
				a.setBoardNo(board.getCbNo());
			}
			resultA = cService.insertAttmCampBoard(fileList);
		}
		
		if(fileList.isEmpty()) {
			if(resultB > 0) {
				return "redirect:/campBoard.su";
			}else {
				model.addAttribute("msg", "게시글 작성에 실패했습니다.재작성 부탁드립니다.");
				return "views/seongun/campboard/writeBoard";
			}
		}else {
			if(resultA > 0 && resultB > 0) {
				return "redirect:/campBoard.su";
			}else if(resultA > 0 && resultB <= 0){
				model.addAttribute("msg", "이미지 업로드를 실패했습니다. 이미지 없이 게시판이 작성되었습니다!");
				return "redirect:/campBoard.su";
			}else {
				model.addAttribute("msg", "게시글 작성에 실패했습니다.재작성 부탁드립니다..");
				return "views/seongun/campboard/writeBoard";
			}
		}
	}
	
}

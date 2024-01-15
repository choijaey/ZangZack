package com.kh.zangzac.jaeyoung.campingReview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.service.PhotoService;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.service.ReplyService;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.jaeyoung.campingReview.model.service.CampingReviewService;
import com.kh.zangzac.jaeyoung.campingReview.model.vo.CampingReview;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CampingReviewController {
	
	private final ImageStorage imageStorage;
	
	@Autowired
    public CampingReviewController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
	
	@Autowired
	CampingReviewService crService;
	
	@Autowired
	ReplyService rService;
	
	@Autowired
	PhotoService pService;
	
	
	@GetMapping("champingReviewListView.jy")
	public String champingReviewListView(@RequestParam(value="page", defaultValue="1") int page,@RequestParam(value="searchText", defaultValue="") String searchText,Model model,HttpServletRequest request) {
		
		if(searchText==null) {
			searchText="";
		}
		int listCount = crService.getListCount(searchText);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 5);
		ArrayList<CampingReview> list = crService.selectBoardList(pi,searchText);
		
		//사진들 가져오기
		for(CampingReview cr : list) {
			SelectCondition sc = new SelectCondition();
		      sc.setBoardNo(cr.getCrNo());
		      sc.setBoardType(8);
		      ArrayList<Photo> pList = pService.selectBoardPhoto(sc);
		      cr.setPhotos(pList);
		      cr.setThumnail(pList.get(0)); //썸네일 넣기
		}
		
		model.addAttribute("searchText", searchText);
		 model.addAttribute("list", list);
		 model.addAttribute("loc", request.getRequestURI());
		 model.addAttribute("pi", pi);
		
		return "views/jaeyoung/champingRiewList";
	}
	
	@GetMapping("campingReviewRightView.jy")
	public String campingReviewRightView() {
		return "views/jaeyoung/champingReviewWrite";
	}
	
	
	@PostMapping("campingReviewWirte.jy")
	public String campingReviewWirte(@RequestParam(value = "file", required = false) ArrayList<MultipartFile> files,
			@RequestParam("thumnail") MultipartFile thumnail,@ModelAttribute CampingReview cr) {
		
		//글 등록
		int result = crService.insertCampingReview(cr);
		
		//사진 넣기
        String name = "jaeyoung";
        ArrayList<Photo> list = new ArrayList<Photo>();
        
        String[] returnArr = imageStorage.saveImage(thumnail, name);
        //구글 클라우드에 사진 저장
        if (returnArr != null) {
        	Photo a = new Photo();
        	a.setPhotoRename(returnArr[0]);
            a.setPhotoPath(returnArr[1]);
            a.setPhotoLevel(0);
            a.setBoardNo(cr.getCrNo()); 
            a.setBoardType(8);
            list.add(a);
        }
        
        if (files != null && !files.isEmpty()) {
            // files가 비어있지 않은 경우, 파일 처리 로직 수행
            for (MultipartFile file : files) {
            	
            	String[] returnArr2 = imageStorage.saveImage(file, name);
                // 파일 처리 로직
            	 if (returnArr != null) {
                 	Photo b = new Photo();
                 	b.setPhotoRename(returnArr2[0]);
                     b.setPhotoPath(returnArr2[1]);
                     b.setPhotoLevel(1);
                     b.setBoardNo(cr.getCrNo()); 
                     b.setBoardType(8);
                     list.add(b);
                 }
            }
        }
		
        System.out.println(list);
        //DB에 사진 저장
        pService.insertPhotoCampBoard(list);
		
		return "views/jaeyoung/champingRiewList";
	}
	
	
	@GetMapping("campingReviewDetail.jy")
	public String campingReviewDetail(@RequestParam("crNo")int crNo,Model model) {
		
		CampingReview cr = crService.selectDetailCr(crNo);
		model.addAttribute("cr", cr);
		
		// 사진 가져오기
		  SelectCondition sc = new SelectCondition();
	      sc.setBoardNo(crNo);
	      sc.setBoardType(8);
	      ArrayList<Photo> pList = pService.selectBoardPhoto(sc);
	      cr.setPhotos(pList);
	      
	     
	    //전체 리스트의 댓글들 저장하기
	     ArrayList<Reply> replyList = rService.selectReply(sc);
	     cr.setReplys(replyList);
		
		return "views/jaeyoung/champingReviewDetail";
	}
	
	
	
	@GetMapping(value = "/campingReviewInsertReply.jy", produces = "application/json")
	@ResponseBody
	public Map<String, Object> insertReply(@ModelAttribute Reply reply) {
		  
		  reply.setBoardType(8);
		  int result = rService.insertReply(reply);
		  
		  System.out.println(reply);
		  Reply r = rService.selectReplyOne(reply);
		  
		  
	      Map<String, Object> map = new HashMap<>();
	      
	      if(result >0) {
	    	  map.put("replyNo", r.getReplyNo());
	      }else {
	    	  map.put("data", "no");
	      }
	      
	       return map;
	  }
	
	
	@GetMapping(value = "/campingReviewUpdateReply.jy", produces = "application/json")
	@ResponseBody
	public Map<String, Object> campingReviewUpdateReply(@ModelAttribute Reply reply) {
		  
		  reply.setBoardType(8);
		  int result = rService.updateReply(reply);
		  
		  
	      Map<String, Object> map = new HashMap<>();
	      
	      if(result >0) {
	    	  map.put("replyNo", reply.getReplyNo());
	      }else {
	    	  map.put("replyNo", "no");
	      }
	      
	       return map;
	  }
	
	
	@GetMapping(value = "/campingReviewDeleteReply.jy", produces = "application/json")
	@ResponseBody
	public Map<String, Object> campingReviewDeleteReply(@ModelAttribute Reply reply) {
		  
		  reply.setBoardType(8);
		  
		 int result = rService.deleteReply(reply);
		  
	      Map<String, Object> map = new HashMap<>();
	      
	      if(result >0) {
	    	  map.put("replyNo", reply.getReplyNo());
	      }else {
	    	  map.put("replyNo", "no");
	      }
	      
	       return map;
	  }
	
	
	@GetMapping("campingReviewEdit.jy")
	public String campingReviewEdit(@RequestParam("crNo")int crNo,Model model) {
		
		CampingReview cr = crService.selectDetailCr(crNo);
		model.addAttribute("cr", cr);
		
		// 사진 가져오기
		  SelectCondition sc = new SelectCondition();
	      sc.setBoardNo(crNo);
	      sc.setBoardType(8);
	      ArrayList<Photo> pList = pService.selectBoardPhoto(sc);
	      cr.setPhotos(pList);
	      cr.setThumnail(pList.get(0));
	      
		return "views/jaeyoung/champingReviewEdit";
	}

}

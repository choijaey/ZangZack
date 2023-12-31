package com.kh.zangzac.yoonahrim.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.service.secondHandService;
import com.kh.zangzac.yoonahrim.model.vo.secondHandException;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;
import com.kh.zangzac.ming.member.model.vo.Member;
import jakarta.servlet.http.HttpSession;

@Controller
public class SecondHandController {
	
	@Autowired
	private secondHandService spService;
	
	private final ImageStorage imageStorage;

    @Autowired
    public SecondHandController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
    
	//중고 메인 페이지로 이동
	@GetMapping("secondHand.ah")
	public String secondHand(@ModelAttribute secondHandProduct sp, HttpSession session, Model model) {
		//String id = ((Member)session.getAttribute("loginUser")).getId();
		//ArrayList<HashMap<String, Object>> list =  spService.selectSeconHand(id);
		//System.out.println(list);
		//model.addAttribute("list", list);
		
		return "views/yoonahrim/secondHandList";
	}
	
	//중고 게시글 불러오기
	@PostMapping("edit.ah")
    public String editPage(@ModelAttribute secondHandProduct sp, @RequestParam("spNo") int spNo, HttpSession session, Model model) {
		
		String memberId = "3";
		
		//String id = ((Member)session.getAttribute("loginUser")).getMemberId();
		ArrayList<secondHandProduct> sList=  spService.selectMyList(memberId);
		ArrayList<Attachment> aList = spService.selectAttachmentList(sp);
		
		model.addAttribute("list", sList);
		model.addAttribute("aList", aList);
		
        return "views/yoonahrim/editSecondHand";
    }
	
	//중고 게시글 수정
	@PostMapping("update.ah")
	public String updatePage(@ModelAttribute secondHandProduct sp, Model model, @RequestParam("inputGroupFile") ArrayList<MultipartFile> inputGroupFile,
							 @RequestParam("spAddressStreet") String spAddressStreet, @RequestParam("spAddressDetail") String spAddressDetail) {
		
		//로그인한 User의 게시물을 update 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		String spAddress = null;
		
		if (!spAddressStreet.equals("")) {
	        spAddress = spAddressStreet + "," + spAddressDetail;
	    }
	    sp.setSpAddress(spAddress);
		
		int result = spService.updateSecondHand(sp);
		
		String name = "ahrim";
	    ArrayList<Attachment> detailList = new ArrayList<>();
	    int result1 = 0;
	    int result2 = 0;
	    
	    
	    // rename 이랑 경로 뱉어냄.
	    for (int i = 0; i < inputGroupFile.size(); i++) {
	        MultipartFile upload = inputGroupFile.get(i); // 파일 하나씩 뽑아오기.
	        String[] returnArr = imageStorage.saveImage(upload, name);
	        Attachment a = new Attachment();
	        
	        if (returnArr != null) {
	            if(i == 0) {
	            	a.setPhotoRename(returnArr[0]);
	 	            a.setPhotoPath(returnArr[1]);
	 	            a.setPhotoLevel(0);
	            }else {
	            	a.setPhotoRename(returnArr[0]);
	 	            a.setPhotoPath(returnArr[1]);
	 	            a.setPhotoLevel(1);
	            }
	            detailList.add(a);
	        } 
	       
	    }
	    result1 = spService.insertSecondHand(sp);
	    
	    for (int i = 0; i < inputGroupFile.size(); i++) {
	        Attachment a = detailList.get(i);
	        if (i == 0) {
	            a.setPhotoLevel(0);
	        } else {
	            a.setPhotoLevel(1);
	        }
	    }
	    
	    // 상세사진 저장
	    for (Attachment a : detailList) {
	        a.setBoardNo(sp.getSpNo());
	    }
	    
	    if(!detailList.isEmpty()) {
	    	 result2 = spService.updateAttmSecondHand(detailList);
	    }
	    
	    if (result1 + result2 == detailList.size() + 1) {
	        return "views/yoonahrim/secondHandDetail";
	    } else {
	        throw new secondHandException("게시판 등록 실패");
	    }
	    
		/*
		if(result > 0) {
			return "views/yoonahrim/secondHandDetail";
		}else {
			throw new secondHandException("게시물 업데이트를 실패하였습니다.");
		}
		*/
	}
	
	//중고 게시글 상세페이지
	@GetMapping("detail.ah")
	public String detailPage(@ModelAttribute secondHandProduct sp, HttpSession session, Model model) {
		//정보를 가져와서 list에 담은 다음 화면에 뿌리면 됨.
		
		String memberId = "3";
		
		//String id = ((Member)session.getAttribute("loginUser")).getMemberId();
		ArrayList<secondHandProduct> sList=  spService.selectMyList(memberId);
		model.addAttribute("list", sList);
		
		return "views/yoonahrim/secondHandDetail";
	}
	
	//중고 게시글 등록
	@GetMapping("write.ah")
	public String writePage() {
		return "views/yoonahrim/writeSecondHand";
	}
	
	@PostMapping("/insert.ah" )
	public String insertPage(@ModelAttribute secondHandProduct sp, @RequestParam("spAddressStreet") String spAddressStreet, 
							 @RequestParam("spAddressDetail") String spAddressDetail,  @RequestParam("inputGroupFile") ArrayList<MultipartFile> inputGroupFile, Model model) {
		//로그인한 User의 게시물을 insert 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		
		String spAddress = null;
	    
	    if (!spAddressStreet.equals("")) {
	        spAddress = spAddressStreet + "," + spAddressDetail;
	    }
	    sp.setSpAddress(spAddress);
	    
	    String name = "ahrim";
	    ArrayList<Attachment> detailList = new ArrayList<>();
	    int result1 = 0;
	    int result2 = 0;
	    
	    // rename 이랑 경로 뱉어냄.
	    for (int i = 0; i < inputGroupFile.size(); i++) {
	        MultipartFile upload = inputGroupFile.get(i); // 파일 하나씩 뽑아오기.
	        String[] returnArr = imageStorage.saveImage(upload, name);
	        Attachment a = new Attachment();
	        
	        if (returnArr != null) {
	            if(i == 0) {
	            	a.setPhotoRename(returnArr[0]);
	 	            a.setPhotoPath(returnArr[1]);
	 	            a.setPhotoLevel(0);
	            }else {
	            	a.setPhotoRename(returnArr[0]);
	 	            a.setPhotoPath(returnArr[1]);
	 	            a.setPhotoLevel(1);
	            }
	            detailList.add(a);
	        } 
	    }
	    result1 = spService.insertSecondHand(sp);
	    
	    for (int i = 0; i < inputGroupFile.size(); i++) {
	        Attachment a = detailList.get(i);
	        if (i == 0) {
	            a.setPhotoLevel(0);
	        } else {
	            a.setPhotoLevel(1);
	        }
	    }
	    
	    // 상세사진 저장
	    for (Attachment a : detailList) {
	        a.setBoardNo(sp.getSpNo());
	    }
	    
	    if(!detailList.isEmpty()) {
	    	 result2 = spService.insertAttmSecondHand(detailList);
	    }
	    
	    if (result1 + result2 == detailList.size() + 1) {
	        return "views/yoonahrim/secondHandList";
	    } else {
	        throw new secondHandException("게시판 등록 실패");
	    }
	}
		
	
	//예약
	//예약 중 -> 예약완료
	//booking.ah
	public String booking() {
		return "views/yoonahrim/secondHandDetail";
	}
	
	//예약 중 -> 예약완료
	//bookingUndo.ah
	public String bokingundo() {
		return "views/yoonahrim/secondHandDetail";
	}
	
	//판매
	//판매완료 -> 삭제
	//soldout.ah
	public String soldout() {
		return "views/yoonahrim/secondHandDetail";
	}
	
	//삭제 -> 판매완료
	//delete.ah
	public String delete() {
		return "views/yoonahrim/secondHandDetail";
	}
	
	
	//체크리스트 화면이동
	@GetMapping("selectCategory.ah")
	public String selectCategory() {
		return "views/yoonahrim/selectCategory";
	}
	
	//채팅 화면 이동
	@GetMapping("chating.ah")
	public String chating() {
		return "views/yoonahrim/chatingRoom";
	}
	
	
}

package com.kh.zangzac.yoonahrim.spBoard.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.ming.member.model.vo.Member;
import com.kh.zangzac.yoonahrim.spBoard.model.service.secondHandService;
import com.kh.zangzac.yoonahrim.spBoard.model.vo.secondHandException;
import com.kh.zangzac.yoonahrim.spBoard.model.vo.secondHandProduct;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


/* 전체적으로 loginUser랑 board_Type 다 넣어두기*/
@SessionAttributes("loginUser")
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
	public String secondHand(@ModelAttribute secondHandProduct sp, Model model, 
							 @RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) {
		
		
		int listCount = spService.getListCount();
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 8);
		ArrayList<Photo> aList = spService.selectPhotoSeconHand(null);
		
		int spNo = sp.getSpNo();
		ArrayList<secondHandProduct> sList =  spService.selectSeconHand(pi, 4);
		
		
		model.addAttribute("aList", aList);
		model.addAttribute("sList", sList);
		model.addAttribute("pi", pi);
		model.addAttribute("loc", request.getRequestURI());
		
		return "views/yoonahrim/secondHandList";
	}
	
	//중고 게시글 불러오기
	@GetMapping("edit.ah")
    public String editPage(@ModelAttribute secondHandProduct sp, @RequestParam("spNo") Integer spNo, HttpSession session, Model model) {
		
		//로그인한 User의 게시물을 update 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		String id = ((Member)session.getAttribute("loginUser")).getMemberId();
		sp.setMemberId(id);
		
		ArrayList<secondHandProduct> sList=  spService.selectMyList(sp);
		ArrayList<Photo> aList = spService.selectAttachmentList(spNo);
		
		model.addAttribute("aList", aList);
		model.addAttribute("list", sList);
		
        return "views/yoonahrim/editSecondHand";
    }
	
	
	//중고 게시글 수정
	@PostMapping("update.ah")
	public String updatePage(@ModelAttribute secondHandProduct sp, Model model, @RequestParam("file") ArrayList<MultipartFile> files, HttpSession session, HttpServletRequest request,
							 @RequestParam("spAddressStreet") String spAddressStreet, @RequestParam("deleteAttm") String[] deleteAttm, 
							 RedirectAttributes redirectAttributes, @RequestParam("spAddressDetail") String spAddressDetail) {
		
		
		//로그인한 User의 게시물을 update 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		String id = ((Member)session.getAttribute("loginUser")).getMemberId();
		sp.setMemberId(id);
		
		String spAddress = null;
		
		if (!spAddressStreet.equals("")) {
	        spAddress = spAddressStreet + "," + spAddressDetail;
	    }
	    sp.setSpAddress(spAddress);
	    
	    //1. 새로 추가한 파일들 저장
	    String name = "ahrim";
	    ArrayList<Photo> aList = spService.selectAttachmentList(sp.getSpNo());
	   
	    int result1 = 0;
	    int result2 = 0;
	    
	    ArrayList<Photo> list = new ArrayList<>();
	      //files 새로 추가한 파일들이 들어가 있는곳
	      for(int i = 0; i < files.size(); i++) {
	         MultipartFile upload = files.get(i);
	         
	         if(!upload.getOriginalFilename().equals("")) {
	            //파일이 들어왔으면.
	            String[] returnArr = imageStorage.saveImage(upload, name);
	            
	            if(returnArr != null) {
	               Photo a = new Photo();
	               a.setBoardNo(sp.getSpNo());
	               a.setBoardType(4);
	               a.setPhotoRename(returnArr[0]);
	               a.setPhotoPath(returnArr[1]);
	               list.add(a);
	            }
	         }
	      }

	    
	    // 2. 삭제될 첨부파일 처리
	    ArrayList<String> delRename = new ArrayList<>();
		ArrayList<Integer> delLevel = new ArrayList<>();
		
		 for(int i =0; i < deleteAttm.length; i++) { //삭제할 파일들을
	         String rename = deleteAttm[i]; //rename에 담음
	         if(!rename.equals("none")) { //none이 아니면
	            String[] split = rename.split("/");// 슬래시로 잘라서
	            delRename.add(split[0]); //0번은 여기 담고
	            delLevel.add(Integer.parseInt(split[1]));//1번은 여기 담음.
	         }
	      }
		
		
		int deleteAttmResult = 0;
		boolean existBeforeAttm = true;
		
		if(!delRename.isEmpty()) {
			deleteAttmResult = spService.deleteAttm(delRename);
			
			if(deleteAttmResult > 0) {
				for(String rename : delRename) {
					imageStorage.deleteImage(rename,name);
				}
			}
			
			 if(delRename.size() == deleteAttm.length) {
		            existBeforeAttm = false;
		         }else {
		            for(int level : delLevel) { 
		               if(level == 0) { //지운 사진의 레벨이 0이면 
		                  spService.updatePhotoLevel(sp.getSpNo());//사진중 가장적은 사진번호에 level 0 주기
		                  break;
		               }
		            }
		         }
		      }
		
		for(int i = 0; i < list.size(); i++) {
			Photo a = list.get(i);
			a.setBoardNo(sp.getSpNo());
			
			if(existBeforeAttm) {
				a.setPhotoLevel(1);
			} else {
				if(i == 0) {
					a.setPhotoLevel(0);
				} else {
					a.setPhotoLevel(1);
				}
			}
		}
		
		result1 = spService.updateSecondHand(sp);
			if(!list.isEmpty()) {
		    	 result2 = spService.insertAttmSecondHand(list);
		    }
		
	    if (result1 >= 0 && result2 >= 0) {
	        return "redirect:secondHand.ah";
	    } else {
	        throw new secondHandException("게시판 등록 실패");
	    }
		
	}
	
	//중고 게시글 상세페이지
	@GetMapping("detail.ah")
	public String detailPage(@ModelAttribute secondHandProduct sp, HttpSession session, Model model) {
		// 사용자가 로그인한 경우에만 세션 정보를 활용하도록 처리
		if (session.getAttribute("loginUser") != null) {
			String id = ((Member)session.getAttribute("loginUser")).getMemberId();
			sp.setMemberId(id);
		}
			int spNo = sp.getSpNo();
		
		ArrayList<secondHandProduct> sList=  spService.selectMyList(sp);
		ArrayList<Photo> aList = spService.selectAttachmentList(spNo);
		ArrayList<Reply> rList = spService.selectReply(spNo);
		//댓글 개수를 가져오는 로직
	    //int replyCount = replyService.getReplyCountByPostId(postId);
		
		 // aList를 spNo의 순서로 정렬
	    Collections.sort(aList, Comparator.comparingInt(Photo::getPhotoNo));
		
		model.addAttribute("aList", aList);
		model.addAttribute("slist", sList);
		model.addAttribute("rList", rList);
		//model.addAttribute("replyCount", replyCount); // 댓글 개수
		return "views/yoonahrim/test2";
	}
	
	//중고 게시글 등록
	@GetMapping("write.ah")
	public String writePage() {
		return "views/yoonahrim/writeSecondHand";
	}
	
	@PostMapping("/insert.ah" )
	public String insertPage(@ModelAttribute secondHandProduct sp, @RequestParam("spAddressStreet") String spAddressStreet, HttpSession session,
							 @RequestParam("spAddressDetail") String spAddressDetail,  @RequestParam("inputGroupFile") ArrayList<MultipartFile> inputGroupFile, Model model) {
		//로그인한 User의 게시물을 insert 하기 위해
		//로그인 유저의 id와 memebr_id가 일치할때 업도르 될 수 있도록 해야함
		String id = ((Member)session.getAttribute("loginUser")).getMemberId();
		sp.setMemberId(id);
		
		String spAddress = null;
	    
	    if (!spAddressStreet.equals("")) {
	        spAddress = spAddressStreet + "," + spAddressDetail;
	    }
	    sp.setSpAddress(spAddress);
	    
	    String name = "ahrim";
	    ArrayList<Photo> detailList = new ArrayList<>();
	    int result1 = 0;
	    int result2 = 0;
	    
	    // rename 이랑 경로 뱉어냄.
	    for (int i = 0; i < inputGroupFile.size(); i++) {
	        MultipartFile upload = inputGroupFile.get(i); // 파일 하나씩 뽑아오기.
	        String[] returnArr = imageStorage.saveImage(upload, name);
	        Photo a = new Photo();
	        
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
	    
	    // 상세사진 저장
	    for (Photo a : detailList) {
	        a.setBoardNo(sp.getSpNo());
	        
	    }
	    
	    if(!detailList.isEmpty()) {
	    	 result2 = spService.insertAttmSecondHand(detailList);
	    }
	    
	    if (result1 + result2 == detailList.size() + 1) {
	        return "redirect:secondHand.ah";
	    } else {
	        throw new secondHandException("게시판 등록 실패");
	    }
	}
	
	
	//예약
	//예약 중 -> 예약완료
	//booking.ah
	@GetMapping("booking.ah")
	public String booking(@RequestParam("spNo") int spNo) {
		
		 int result = spService.updateBooking(spNo);
		
		 if (result > 0) {
		        // 예약 성공
		        return "redirect:secondHand.ah";
		    } else {
		        // 예약 실패
		        throw new secondHandException("예약 실패");
		    }
	}
	
	//예약 중 -> 예약완료
	//bookingUndo.ah
	@GetMapping("bookingUndo.ah")
	public String bokingundo(@RequestParam("spNo") int spNo) {
		
		int result = spService.updateBookingundo(spNo);
		
		 if (result > 0) {
		        // 예약 취소
		        return "redirect:secondHand.ah";
		    } else {
		        // 예약 실패
		        throw new secondHandException("예약 실패");
		    }
		
	}
	
	//판매
	//판매완료 -> 삭제
	//soldout.ah
	@GetMapping("soldout.ah")
	public String soldout(@RequestParam("spNo") int spNo) {
		
		int result = spService.soldout(spNo);
		
		 if (result > 0) {
		        // 판매 완료
		        return "redirect:secondHand.ah";
		    } else {
		        // 판매 완료 실패
		        throw new secondHandException("판매완료 실패");
		    }
	}
	
	//삭제 -> 판매완료
	//delete.ah
	@GetMapping("delete.ah")
	public String markDelete(@RequestParam("spNo") int spNo) {
		
		int result = spService.markDelete(spNo);
		
		 if (result > 0) {
		        // 삭제
		        return "redirect:secondHand.ah";
		    } else {
		        // 삭제 실패
		        throw new secondHandException("삭제 실패");
		    }
		
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
	
	public String deleteAttm(@RequestParam("spNo") int spNo, Model model) {
		
		int result = spService.deleteAttmForN(spNo);
		
		if(result > 0) {
			return "redirect:secondHand.ah";
		} else {
			throw new secondHandException("삭제 실패");
		}
	}
	
	@GetMapping("admin.ah")
	public String adminSecondPage(@ModelAttribute secondHandProduct sp, Model model ,@RequestParam(value="page", defaultValue="1")int page, HttpServletRequest request) {
		
		int listCount = spService.getListCount();
		int currentPage = page;
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 8);
		ArrayList<secondHandProduct> list = spService.selectBoardList(pi,4);
		ArrayList<secondHandProduct> sList=  spService.selectAdminList(sp);
		ArrayList<Photo> aList = spService.selectAttachmentList(sp.getSpNo());
		
		if(list != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			model.addAttribute("sList", sList);
			model.addAttribute("aList", aList);
			return "views/yoonahrim/adminSecondHand";
		}else {
			throw new secondHandException("없다");
		}
	}
	
	@PostMapping("editAdmin.ah")
	public String editAdmin(@ModelAttribute secondHandProduct sp) {
		int result = spService.updateAdminInfo(sp);
		
		System.out.println(sp);
		if(result > 0) {
			return "redirect:admin.ah";
		}else {
			throw new secondHandException("업데이트 실패함.");
		}
		
	}
	
	@GetMapping("searchSecondHand.ah")
	   public String campSerchList(@ModelAttribute secondHandProduct sp,@RequestParam("region") String region,
	                              @RequestParam("type") String type,
	                              @RequestParam(value="page", defaultValue="1") int page,
	                              Model model) {
	      
		  HashMap<String, String> map = new HashMap<>();
		  map.put("region", region);
		  map.put("type", type);
		  int result = spService.searchSpCount(map);
		  
		  int currentPage = page;
	      PageInfo pi = Pagination.getPageInfo(currentPage, result, 8);
		  
	      ArrayList<secondHandProduct> spList = spService.searchSpList(map);
	      ArrayList<Photo> aList = spService.selectPhotoSeconHand(null);
	      
	      if(spList != null) {
	    	 model.addAttribute("aList", aList);
	         model.addAttribute("result", result);
	         model.addAttribute("spList", spList);
	         model.addAttribute("region", region);
	         model.addAttribute("type", type);
	         model.addAttribute("pi", pi);
	         
	         return "views/yoonahrim/searchSecondHand";
	      }else {
	         throw new secondHandException("검색에 실패하였습니다");
	      }
	      
	   }
	
	
	
}
	
	


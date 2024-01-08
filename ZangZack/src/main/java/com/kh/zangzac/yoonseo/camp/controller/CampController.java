package com.kh.zangzac.yoonseo.camp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.yoonseo.camp.model.exception.CampException;
import com.kh.zangzac.yoonseo.camp.model.service.CampService;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CampController {
	
	@Autowired
	private CampService cService;
	
	private final ImageStorage imageStorage;

    @Autowired
    public CampController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
	
	
	@GetMapping("campSearch.ys")
	public String campSearch(@RequestParam(value="page", defaultValue="1") int page,
			                  Model model, HttpServletRequest request) {
		
		int listCount = cService.getListCount(3); //내 보드타입은 3 번이니까
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 7); //7개씩 보이게 할거야
		ArrayList<CampingGround> cList = cService.selectCampList(pi,3);
		ArrayList<CampingGround> mapList = cService.selectMapList(3);
		
		
		
		if(cList != null) {
			model.addAttribute("loc", request.getRequestURI());
			model.addAttribute("cList", cList);
			model.addAttribute("pi", pi);
			model.addAttribute("mapList", mapList);
			
			return "views/yoonseo/campSearch";
		}else {
			throw new CampException("캠프 목록 조회 실패");
		}
	}
	
	@GetMapping("campList.ys")
	public String campList(@RequestParam(value="page", defaultValue="1") int page,
			               Model model, HttpServletRequest request) {
		
		String recomendation = "Y";
		int listCount = cService.getRecomendationCount(recomendation);
		
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<CampingGround> list = cService.selectRecomendationList(pi,recomendation);
		
	    ArrayList<Photo> photo = cService.selectOnePhoto(0);
	    
	    
	   
	    ArrayList<String> infoArray = new ArrayList<>();
	    ArrayList<Double> pointArray = new ArrayList<>();
	    
        String content ="";
	    for (CampingGround cg : list) {
	        if(cg != null && cg.getCgImgInfo() != null) {
	        	String cgAmenity = cg.getCgAmenity();
	        	String cgInfo = cg.getCgImgInfo();
	        	cg.calculateCgPoint();
	        	
	        	String[] infoList = cgInfo.split(",");
	        	for(String info : infoList) {
	 	        	infoArray.add(info.trim());
	 	        }
	        	String[] contents = cgAmenity.split("\\.");
	 	        if (contents.length > 0) {
	 	            content = contents.length > 0 ? contents[0] : "";
	 	        }
	 	        
	        } 
	        // 문자열을 "."으로 분리하여 배열로 얻고, 0번째 인덱스의 값만 리스트에 추가합니다.
	        
	    }
	     
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("loc", request.getRequestURI());
			model.addAttribute("pi", pi);
			model.addAttribute("photo", photo);
			

			
			return "views/yoonseo/campList";
			
		}else {
			throw new CampException("추천 목록 조회 실패");
		}
				
	}
	
	@GetMapping("campDetail.ys")
	public String selectCampDetail(@RequestParam("no") int no,
			                       @RequestParam("page") int page,
			                       Model model) {
		
		CampingGround camp = cService.selectCampingDetail(no);
		
		ArrayList<Photo> campList = cService.selectPhoto(no); //캠핑장 사진
		
	    String info = camp.getCgImgInfo();
	    String[] infoArray = info.split(",");
	  
	    double point = camp.getCgStarPoint()*100/5;//별점에 적용시킬 width % 계산식.
	    
		if(camp != null) {
		   model.addAttribute("camp", camp);
		   model.addAttribute("list", campList);
		   model.addAttribute("page", page);
		   model.addAttribute("infoArray", infoArray);
		   model.addAttribute("point", point);
		  
		   
		   return"views/yoonseo/campDetail";   
		}else {
			throw new CampException("상세보기를 실패했습니다");
		}
		
		
	}
	
	@GetMapping("detailWrite.ys")
	public String detailWriter() {
		return "views/yoonseo/detailWrite";
	}
	@GetMapping("recommendWrite.ys")
	public String recommendWrite () {
		return "views/yoonseo/recommendWrite";
	}
	
	@PostMapping("campInsert.ys")
	public String campInsert(@ModelAttribute CampingGround camp,
			                 @RequestParam("campImg") ArrayList<MultipartFile> campFiles
			                
			                 ) {
		
		ArrayList<Photo> campList = new ArrayList<>();
		
		String name = "yoonseo";
		
		int result1 = 0;
		int result2 = 0;
		
		
		for(int i = 0; i < campFiles.size(); i++) {
			MultipartFile campUpload = campFiles.get(i);
			String[] returnArr = imageStorage.saveImage(campUpload, name);
			
			if(returnArr != null) {
				Photo a = new Photo();
				if(i == 0) {
					a.setPhotoLevel(0);
				}else {
					a.setPhotoLevel(1);
				}
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				
				campList.add(a);
			}
		}
		
		
		
		result1 = cService.insertCamp(camp);
		
		//캠프상세사진 저장
		for(Photo a : campList ) {
			a.setBoardNo(camp.getCgNo());
		}
		result2 = cService.insertCampImg(campList);
		
	
		
		if(result1 + result2  == campList.size()+ 1) {
			return "views/yoonseo/campDetail";
		}else {
			throw new CampException("캠핑장 등록 실패");
		}
	}
	
	@GetMapping("campUpdate.ys")
	public String campUpdate() {
		return "views/yoonseo/campUpdate";
	}
	
	
	

}

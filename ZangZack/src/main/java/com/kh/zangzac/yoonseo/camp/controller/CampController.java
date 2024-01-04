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
import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
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
	public String campList() {
		return "views/yoonseo/campList";
	}
	
	@GetMapping("campDetail.ys")
	public String selectCampDetail(@RequestParam("no") int no,
			                       @RequestParam("page") int page,
			                       Model model) {
		
		CampingGround camp = cService.selectCampingDetail(no);
		ArrayList<Attachment> list = cService.selectPhoto(no);
		
		if(camp != null) {
		   model.addAttribute("camp", camp);
		   model.addAttribute("list", list);
		   model.addAttribute("page", page);
		   
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
			                 @RequestParam("campImg") ArrayList<MultipartFile> campFiles,
			                 @RequestParam("infoImg") ArrayList<MultipartFile> infoFiles
			                 ) {
		
		ArrayList<Attachment> campList = new ArrayList<>();
		ArrayList<Attachment> infoList = new ArrayList<>();
		String name = "yoonseo";
		
		int result1 = 0;
		int result2 = 0;
		int result3 =0;
		
		
		for(int i = 0; i < campFiles.size(); i++) {
			MultipartFile campUpload = campFiles.get(i);
			String[] returnArr = imageStorage.saveImage(campUpload, name);
			
			if(returnArr != null) {
				Attachment a = new Attachment();
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				a.setPhotoLevel(1);
				campList.add(a);
			}
		}
		
		for(int i = 0; i < infoFiles.size(); i++) {
			MultipartFile upload = infoFiles.get(i);
			String[] returnArr = imageStorage.saveImage(upload, name);
			
			if(returnArr != null) {
				Attachment a = new Attachment();
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				a.setPhotoLevel(1);
				
				infoList.add(a);
			}
		}
		
		result1 = cService.insertCamp(camp);
		
		//캠프상세사진 저장
		for(Attachment a : campList ) {
			a.setBoardNo(camp.getCgNo());
		}
		result2 = cService.insertCampImg(campList);
		
		//시설사진 저장
		for(Attachment a : infoList) {
			a.setBoardNo(camp.getCgNo());
		}
		result3 = cService.insertInfoImg(infoList);
		
		if(result1 + result2 + result3 == campList.size() + infoList.size() + 1) {
			return "views/yoonseo/campDetail";
		}else {
			throw new CampException("캠핑장 등록 실패");
		}
	}
	
	
	

}

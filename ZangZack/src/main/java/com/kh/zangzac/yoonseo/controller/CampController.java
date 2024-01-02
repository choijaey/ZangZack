package com.kh.zangzac.yoonseo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonseo.model.vo.CampingGround;
import com.kh.zangzac.yoonseo.model.servcie.CampService;


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
	public String campSearch() {
		return "views/yoonseo/campSearch";
	}
	
	@GetMapping("campList.ys")
	public String campList() {
		return "views/yoonseo/campList";
	}
	
	@GetMapping("campDetail.ys")
	public String campDetail() {
		return"views/yoonseo/campDetail";
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
		
		
		for(int i = 0; i < campFiles.size(); i++) {
			MultipartFile campUpload = campFiles.get(i);
			String[] returnArr = imageStorage.saveImage(campUpload, name);
			
			if(returnArr != null) {
				Attachment a = new Attachment();
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				a.setPhotoLevel(0);
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
				a.setPhotoLevel(0);
				
				infoList.add(a);
			}
		}
		
		result1 = cService.insertCamp(camp);
		
		return null;
	}

}

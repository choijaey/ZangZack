package com.kh.zangzac.jaeyoung.campingReview.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.photo.model.service.PhotoService;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.service.ReplyService;
import com.kh.zangzac.jaeyoung.campingReview.model.service.CampingReviewService;
import com.kh.zangzac.jaeyoung.campingReview.model.vo.CampingReview;

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
	public String champingReviewListView() {
		
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

}

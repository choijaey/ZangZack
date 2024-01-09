package com.kh.zangzac.jaeyoung.campingFriend.controller;

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
import com.kh.zangzac.common.photo.model.service.PhotoService;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.jaeyoung.campingFriend.model.service.CampingFriendService;
import com.kh.zangzac.jaeyoung.campingFriend.model.vo.CampingFriend;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class FriendController {
	
	private final ImageStorage imageStorage;
	
	@Autowired
	PhotoService pService;
	
	@Autowired
	CampingFriendService cService;

    @Autowired
    public FriendController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }

	@GetMapping("champingFriend.jy")
    public String champingFriendPage(Model model) {
		
		 // 전체 숫자
	      int listCount = cService.listCount();
	      PageInfo pi = Pagination.getPageInfo(1, listCount, 3);
	      
	      //전체 리스트 가져오기~~
	      ArrayList<CampingFriend> list = cService.cfLimitList(pi);
	      
	      model.addAttribute("list", list);
	      model.addAttribute("maxPage", pi.getMaxPage());
	      
    	return "views/jaeyoung/champingFriendPage";
    }
	
	
   @GetMapping("cfList.jy")
   @ResponseBody
   public Map<String, Object> replyLimitList(@RequestParam(value="currentPage", defaultValue="1") int page, HttpServletRequest request ) {
	  // 전체 숫자
      int listCount = cService.listCount();
      PageInfo pi = Pagination.getPageInfo(page, listCount, 3);
      
      //전체 리스트 가져오기~~
      ArrayList<CampingFriend> list = cService.cfLimitList(pi);
       
      Map<String, Object> map = new HashMap<>();
      
      
       map.put("list", list);
       map.put("Pi", pi);
       map.put("loc", request.getRequestURI());
       
       return map;
   }
	
	
	
	
	
	@GetMapping("campingFriendWriteView.jy")
	public String campingFriendWriteView() {
		
		return "views/jaeyoung/champingFriendWrite";
		
	}
	
	@PostMapping("champingFriendWrite.jy")
	public String champingFriendWrite(@ModelAttribute CampingFriend cf,@RequestParam("file")MultipartFile file) {
		
		
		//파일 넣기 
		int result = cService.insertCf(cf);
		
		//사진 넣기
        String name = "jaeyoung";
        String[] returnArr = imageStorage.saveImage(file, name);
        Photo a = new Photo();
        ArrayList<Photo> list = new ArrayList<Photo>();
        
        //구글 클라우드에 사진 저장
        if (returnArr != null) {
        	a.setPhotoRename(returnArr[0]);
            a.setPhotoPath(returnArr[1]);
            a.setPhotoLevel(0);
            a.setBoardNo(cf.getCfNo()); 
            a.setBoardType(7);
            list.add(a);
        }
        
        //DB에 사진 저장
	    pService.insertPhotoCampBoard(list);
	    
		return "redirect:champingFriend.jy";
		
	}
	
	
	
}

package com.kh.zangzac.seongun.recipe.conroller;

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
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.service.PhotoService;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.seongun.common.WorkController;
import com.kh.zangzac.seongun.recipe.model.service.RecipeService;
import com.kh.zangzac.seongun.recipe.model.vo.CookwareList;
import com.kh.zangzac.seongun.recipe.model.vo.Recipe;

import jakarta.servlet.http.HttpServletRequest;

@SessionAttributes("loginUser")
@Controller
public class RecipeController {
	
	private final ImageStorage imageStorage;
	
	@Autowired
	private RecipeService rService;
	
	@Autowired 
	private PhotoService pService;
    
	@Autowired
	private WorkController sWork;
	
	@Autowired
	public RecipeController(ImageStorage imageStorage) {
		this.imageStorage = imageStorage;
	}
	
	@GetMapping("recipe.su")
	public String recipe(@RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		int listCount = 0;
		listCount = rService.getListCount();
		
		int currentPage = page;
	    
	    PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 3);
	    
	    ArrayList<Recipe> list = rService.recipeList(pi);
	    System.out.println(list);
	    
	    String msg = null; 
	    sWork.addRec(list, msg, pi , model,request.getRequestURI());
		return "views/seongun/recipe/recipe";
	}
	
	//레시피 작성 메소드
	@GetMapping("insertRecipeView.su")
	public String insertRecipeView() {
		return "views/seongun/recipe/writeRecipe";
	}
	
	@PostMapping("insertRecipe.su")
	public String insertRecipe(@ModelAttribute Recipe recipe,@RequestParam("cookCategoryNo")int[] categoryNo, @RequestParam("file") ArrayList<MultipartFile> files,HttpServletRequest request, Model model) {
		int resultB = 0;
		int resultA = 0;
		int resultC = 0;
		int max = categoryNo.length;
		ArrayList<Photo> fileList = new ArrayList<>();
		ArrayList<CookwareList> cookList = new ArrayList<>();
		for(int i=0; i<files.size(); i++) {
			MultipartFile upload = files.get(i); //파일 하나씩 뽑아오기.
			String[] returnArr = imageStorage.saveImage(upload, "seongun");
			
			if(returnArr != null) {
				Photo a = sWork.setAttachment(returnArr, i, 2);
				fileList.add(a);
			}
			
		}
		
		resultB = rService.insertRecipe(recipe);
		int set = recipe.getRecipeNo();
		if(!fileList.isEmpty()) {
			for(Photo a : fileList) {
				a.setBoardNo(set);
			}
			resultA = pService.insertPhotoCampBoard(fileList);
		}
		
		if(max > 0) {
			for(int i = 0; i < max; i++) {
				CookwareList c = sWork.setCook(set, categoryNo[i]);
				cookList.add(c);
			}
			resultC = rService.insertCookList(cookList);
		}
		if(fileList.isEmpty()) {
			if(resultB > 0) {
				return "redirect:recipe.su";
			}else {
				model.addAttribute("msg", "게시글 작성에 실패했습니다.재작성 부탁드립니다.");
				return "views/seongun/recipe/writeRecipe";
			}
		}else {
			if(resultA > 0 && resultB > 0) {
				return "redirect:recipe.su";
			}else if(resultA > 0 && resultB <= 0){
				model.addAttribute("msg", "이미지 업로드를 실패했습니다. 이미지 없이 게시판이 작성되었습니다!");
				return "redirect:recipe.su";
			}else {
				model.addAttribute("msg", "게시글 작성에 실패했습니다.재작성 부탁드립니다.");
				return "views/seongun/recipe/writeRecipe";
			}
		}
	}
	
	@GetMapping("selectRecipe.su")
	public String selectRecipe(@RequestParam("recipeNo") int recipeNo,Model model) {
		Recipe r = rService.selectRecipe(recipeNo);
		
		SelectCondition b = sWork.selectBoard(recipeNo, 2);
		ArrayList<Photo> pList = pService.selectBoardPhoto(b);
		
		System.out.println(r.toString());
		
		sWork.recipeDetatil(model, r, pList);
		return "views/seongun/recipe/recipeDetail";
	}
}

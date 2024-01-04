package com.kh.zangzac.sohwa.product.controller;

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
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.sohwa.product.model.exception.ProductException;
import com.kh.zangzac.sohwa.product.model.service.ProductService;
import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class ProductController {
	
	private final ImageStorage imageStorage;

    @Autowired
    public ProductController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
	
	@Autowired
	private ProductService pService;
	
	
	
	//관리자 상품 등록페이지 view
	@GetMapping("adminProductEnroll.so")
	public String adminView() {
		return "views/sohwa/(admin)productEnroll";
	}
	
	
	
	
	//상품등록페이지 view
	@GetMapping("productListView.so")
	public String productListView(@RequestParam(value="standard", defaultValue="1") int standard, @RequestParam(value="categoryNo", defaultValue="0") int categoryNo, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		
		int listCount = pService.getListCount(categoryNo);
		
		int currentPage = page;
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 16);
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("categoryNo", categoryNo);
		map.put("standard", standard);
		System.out.println(map);
		
		
		ArrayList<Product> pList = pService.selectProductList(pi, map);
		
		System.out.println(pList);
		
		
		//카테고리 별 썸네일만 가져오기.
		ArrayList<Attachment> aList = pService.selectPhotoList(categoryNo);
		
		if(aList != null) {
			model.addAttribute("aList", aList);
			model.addAttribute("pList", pList);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "views/sohwa/productList";
		}else {
			throw new ProductException("상품 목록 조회 실패");
		}
		
	}
	
	
	//상품등록
	@PostMapping("productEnroll.so")
	public String insertProduct(@ModelAttribute Product p, @RequestParam("option") String[] options, @RequestParam("productEno") Integer[] productEnos, @RequestParam("detailFile") ArrayList<MultipartFile> detailFiles, @RequestParam("coreFile") ArrayList<MultipartFile> coreFiles, Model model) {
		
		
		
		ArrayList<Attachment> coreList = new ArrayList<>();
		ArrayList<Attachment> detailList = new ArrayList<>();
		String name="sohwa";
    	
		int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        
        
        
		for(int i=0; i<coreFiles.size(); i++) {
			MultipartFile upload = coreFiles.get(i); //파일 하나씩 뽑아오기.
			String[] returnArr = imageStorage.saveImage(upload, name);
			
			
			
			if(returnArr != null) {
				
				Attachment a = new Attachment();
				if(i==0) {
					a.setPhotoRename(returnArr[0]);
					a.setPhotoPath(returnArr[1]);
					a.setPhotoLevel(0);
				}else {
					a.setPhotoRename(returnArr[0]);
					a.setPhotoPath(returnArr[1]);
					a.setPhotoLevel(1);
				}
				coreList.add(a);
			}
			
			
		}
		
		
		//rename 이랑 경로 뱉어냄.
		for(int i=0; i<detailFiles.size(); i++) {
			MultipartFile upload = detailFiles.get(i); //파일 하나씩 뽑아오기.
			String[] returnArr = imageStorage.saveImage(upload, name);
			
			if(returnArr != null) {
				Attachment a = new Attachment();
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				a.setPhotoLevel(2);
				
				detailList.add(a);
			}
			
		}
		
		int eno = 0;
		
		for(int i=0; i<options.length; i++) {
			eno += productEnos[i];
			p.setEno(eno);
		}
		
		result1 = pService.insertProduct(p);
		
		//대표사진 저장
		for(Attachment a : coreList) {
			a.setBoardNo(p.getProductNo());
		}
		result2 = pService.insertProductPhoto(coreList);
		
		
		//상세사진 저장
		for(Attachment a : detailList) {
			a.setBoardNo(p.getProductNo());
		}
		result3 = pService.insertProductPhoto(detailList);
		
		
		
		
		
		
		
		ArrayList<Option> list = new ArrayList<>();
		
		for(int i=0; i<options.length; i++) {
			String option = options[i];
			Integer productEno = productEnos[i];
			
			
			
			Option o = new Option();
		    o.setProductOptionColor(option);
		    o.setProductOptionEno(productEno);
		    o.setProductNo(p.getProductNo());
		    list.add(o);
		}
		
		
		
		
		
		int result0 = pService.insertOption(list);
		
		
		System.out.println(list);
		//[Option(productOptionNo=0, productOptionColor=그린, productOptionEno=15, productNo=32), Option(productOptionNo=0, productOptionColor=주황, productOptionEno=20, productNo=32), Option(productOptionNo=0, productOptionColor=보라, productOptionEno=15, productNo=32)]
		
		
		
		
		if(result0 + result1 + result2 + result3 == options.length + coreList.size()+detailList.size()+1) {
			return "redirect:productListView.so";
		}else {
			throw new ProductException("상품 등록 실패");
		}
	}
	
	


	
	
	
	
}

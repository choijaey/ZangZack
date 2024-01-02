package com.kh.zangzac.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.product.model.service.ProductService;
import com.kh.zangzac.product.model.vo.Attachment;
import com.kh.zangzac.product.model.vo.Product;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProductController {
	
	
	@Autowired
	private ProductService pService;
	
	//상품등록페이지 view
	@GetMapping("productListView.so")
	public String productListView() {
		return "views/sohwa/productDetail";
	}
	
	
	//상품등록
	@PostMapping("productEnroll.so")
	public String insertProduct(@ModelAttribute Product p, @RequestParam("detailFile") ArrayList<MultipartFile> detailFiles, @RequestParam("coreFile") ArrayList<MultipartFile> coreFiles, HttpServletRequest request, Model model) {
		ArrayList<Attachment> coreList = new ArrayList<>();
		ArrayList<Attachment> detailList = new ArrayList<>();
		
		for(int i=0; i<coreFiles.size(); i++) {
			MultipartFile upload = coreFiles.get(i); //파일 하나씩 뽑아오기.
			if(!upload.getOriginalFilename().equals("")) {
				String[] returnArr = saveFile(upload);
				
				
			}
		}
		
		
		return null;
	}


	private String[] saveFile(MultipartFile upload) {
		return null;
	}
	
	
	
}

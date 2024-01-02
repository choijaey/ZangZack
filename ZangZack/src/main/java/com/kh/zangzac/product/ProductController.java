package com.kh.zangzac.product;

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
import com.kh.zangzac.product.model.exception.productException;
import com.kh.zangzac.product.model.service.ProductService;
import com.kh.zangzac.product.model.vo.Attachment;
import com.kh.zangzac.product.model.vo.Product;


@Controller
public class ProductController {
	
	private final ImageStorage imageStorage;

    @Autowired
    public ProductController(ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
    }
	
	@Autowired
	private ProductService pService;
	
	//상품등록페이지 view
	@GetMapping("productListView.so")
	public String productListView() {
		return "views/sohwa/(admin)productEnroll";
	}
	
	
	//상품등록
	@PostMapping("productEnroll.so")
	public String insertProduct(@ModelAttribute Product p, @RequestParam("option") String[] options, @RequestParam("productEno") Integer[] productEnos, @RequestParam("detailFile") ArrayList<MultipartFile> detailFiles, @RequestParam("coreFile") ArrayList<MultipartFile> coreFiles, Model model) {
		
		System.out.println(options); //그린, 주황, 보라
		System.out.println(productEnos); //15, 20, 30
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for(int i=0; i<options.length; i++) {
			map.put(options[i], productEnos[i]);
		}
		
		System.out.println(map);
		
		
		
		
		
		
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
				a.setPhotoRename(returnArr[0]);
				a.setPhotoPath(returnArr[1]);
				a.setPhotoLevel(0);
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
				a.setPhotoLevel(1);
				
				detailList.add(a);
			}
			
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
		
		
		if(result1 + result2 + result3 == coreList.size()+detailList.size()+1) {
			return "views/sohwa/productList";
		}else {
			throw new productException("상품 등록 실패");
		}
	}


	
	
	
	
}

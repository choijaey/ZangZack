package com.kh.zangzac.sohwa.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.ImageStorage;
import com.kh.zangzac.common.Pagination;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.ming.member.model.vo.Member;
import com.kh.zangzac.sohwa.product.model.exception.ProductException;
import com.kh.zangzac.sohwa.product.model.service.ProductService;
import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Cart;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;
import com.kh.zangzac.sohwa.product.model.vo.Qna;
import com.kh.zangzac.sohwa.product.model.vo.Review;

import jakarta.servlet.http.HttpServletRequest;

@SessionAttributes("loginUser")
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
	public String adminProductEnrollView() {
		return "views/sohwa/(admin)productEnroll";
	}
	
	//관리자 상품 수정페이지 view
	@GetMapping("adminProductUpdate.so")
	public String adminProductUpdateView(@RequestParam("productNo") int productNo, Model model) {
		Product p = pService.selectProductDetail(productNo);
		ArrayList<Attachment> list = pService.selectPhotoDetail(productNo);
		ArrayList<Option> oList = pService.optionDetail(productNo);
		
		
		model.addAttribute("oList", oList);
		model.addAttribute("list", list);
		model.addAttribute("p", p);
		return "views/sohwa/(admin)productUpdate";
	}
	
	
	
	
	
	//관리자 상품 목록페이지 view
	@GetMapping("adminProductList.so")
	public String adminProductListView(Model model) {
		ArrayList<Product> pList = pService.selectAllProduct();
		ArrayList<Attachment> aList = pService.selectAllPhoto();
		
		model.addAttribute("pageStatus", "Y");
		model.addAttribute("aList", aList);
		model.addAttribute("pList", pList);
		return "views/sohwa/(admin)productList";
	}
	
	
	@GetMapping("productListN.so")
	public String adminProductListN(Model model) {
		ArrayList<Product> pList = pService.selectDeleteProduct();
		ArrayList<Attachment> aList = pService.selectDeletePhoto();
		
		model.addAttribute("pageStatus", "N");
		model.addAttribute("aList", aList);
		model.addAttribute("pList", pList);
		return "views/sohwa/(admin)productList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("productListView.so")
	public String productListView(@RequestParam(value="keyword", defaultValue="") String keyword, @RequestParam(value="standard", defaultValue="1") String standard, @RequestParam(value="categoryNo", defaultValue="0") String categoryNo, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
		
		
		
		int listCount = 0;
		HashMap<String, String> categoryMap = new HashMap<>();
		HashMap<String, String> searchMap = new HashMap<>();
		ArrayList<Product> pList = new ArrayList<>();
		ArrayList<Attachment> aList = new ArrayList<>();
		ArrayList<Attachment> thList = new ArrayList<>();
		PageInfo pi = new PageInfo();
		
		//categoryNo가 0일때는 keyword가져가기
		//keyword가 ""일때는 categoryNo가져가기
		if(!categoryNo.equals("0")) {
			listCount = pService.getListCount(categoryNo);
			int currentPage = page;
			pi = Pagination.getPageInfo(currentPage, listCount, 16);
			categoryMap.put("categoryNo", categoryNo);
			categoryMap.put("standard", standard);
			pList = pService.selectProductList(pi, categoryMap);
			aList = pService.selectPhotoList(categoryNo);
			thList = pService.selectPhotothList(categoryNo);
		}else {
			listCount = pService.getListCountKeyword(keyword);
			int currentPage = page;
			pi = Pagination.getPageInfo(currentPage, listCount, 16);
			
			searchMap.put("keyword", keyword);
			searchMap.put("standard", standard);
			
			pList = pService.searchProduct(pi, searchMap);
			aList = pService.searchPhoto(searchMap);
			thList = pService.searchPhototh(searchMap);
		}
		
		
		ArrayList<Review> rList = pService.selectproductAllReview();
		
		
		if(aList != null) {
			model.addAttribute("thList", thList);
			model.addAttribute("rList", rList);
			model.addAttribute("categoryNo", categoryNo);
			model.addAttribute("keyword", keyword);
			model.addAttribute("aList", aList);
			model.addAttribute("pList", pList);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "views/sohwa/productList";
		}else {
			throw new ProductException("상품 목록 조회 실패");
		}
		
	}
	
	
	
//	@GetMapping("searchProduct.so")
//	public String searchProduct(@RequestParam("keyword") String keyword, @RequestParam(value="standard", defaultValue="1") String standard, @RequestParam(value="page", defaultValue="1") int page, Model model, HttpServletRequest request) {
//		
//		int listCount = pService.getListCountKeyword(keyword);
//		
//		
//		
//		System.out.println(map);
//		
//		ArrayList<Product> pList = pService.searchProduct(pi, map);
//		ArrayList<Attachment> aList = pService.searchPhoto(map);
//		
//		
//		if(aList != null) {
//			model.addAttribute("keyword", keyword);
//			model.addAttribute("aList", aList);
//			model.addAttribute("pList", pList);
//			model.addAttribute("pi", pi);
//			model.addAttribute("loc", request.getRequestURI());
//			return "views/sohwa/productList";
//		}else {
//			throw new ProductException("상품 목록 조회 실패");
//		}
//	}
//	
	
	@GetMapping("productDetail.so")
	public String productDetailView(@RequestParam(value="reviewStatus", defaultValue="1") int reviewStatus, @RequestParam("productNo") int productNo, Model model) {
		Product p = pService.selectProductDetail(productNo);
		//상품사진
		ArrayList<Attachment> list = pService.selectPhotoDetail(productNo);
		ArrayList<Option> oList = pService.optionDetail(productNo);
		ArrayList<Qna> qList = pService.selectProductQna(productNo);
		
		ArrayList<Member> mList = pService.selectReviewMember(productNo);
		
		ArrayList<Review> rList = new ArrayList<>();
		
		
		if(reviewStatus==1) {
			rList = pService.selectProductReview(productNo);
		}else {
			rList = pService.selectProductPhotoReview(productNo);
		}
		
		//리뷰사진
		ArrayList<Attachment> aList = pService.selectPhotoReview(productNo);
		
		imageStorage.deleteImage("cb1d55f4-e2c4-42b1-96b9-948d8883b3c5.png","sohwa");
		
		
		int roundScore = (int) Math.round(p.getProductScore());
		
		model.addAttribute("roundScore", roundScore);
		model.addAttribute("reviewStatus", reviewStatus);
		model.addAttribute("mList", mList);
		model.addAttribute("aList", aList);
		model.addAttribute("rList", rList);
		model.addAttribute("qList", qList);
		model.addAttribute("productNo", productNo);
		model.addAttribute("oList", oList);
		model.addAttribute("list", list);
		model.addAttribute("p", p);
		return "views/sohwa/productDetail";
	}
	
	
	//상품등록
	@PostMapping("productEnroll.so")
	public String insertProduct(@ModelAttribute Product p, @RequestParam(value="option") String[] options, @RequestParam("productEno") Integer[] productEnos, @RequestParam("detailFile") ArrayList<MultipartFile> detailFiles, @RequestParam("coreFile") ArrayList<MultipartFile> coreFiles, Model model) {
		
		
		
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
	
	
	
	
	@GetMapping("insertCart.so")
	   public String insertCart(@RequestParam("price") int price, @RequestParam("option") String option, @RequestParam("productNo") int productNo, @RequestParam("eno") int eno, Model model) {
	      
	      String id = ((Member)model.getAttribute("loginUser")).getMemberId();
	      Cart c = new Cart();
	      c.setProductNo(productNo);
	      c.setProductEno(eno);
	      c.setBuyOption(option);
	      c.setMemberId(id);
	      c.setBuyPrice(price);
	      int result = pService.insertCart(c);
	      
	      if(result > 0) {
	          return "redirect:productDetail.so?productNo=" + productNo;
	      }else {
	         throw new ProductException("장바구니 등록에 실패");
	      }
	      
	 }
	
	
	
	
	@GetMapping("cartView.so")
	public String cartView(Model model) {
		String id = ((Member)model.getAttribute("loginUser")).getMemberId();
		ArrayList<Cart> list = pService.memberCart(id);
	    ArrayList<Product> pList = pService.selectAllProduct();
	    ArrayList<Attachment> aList = pService.selectAllPhoto();
	    ArrayList<Option> oList = pService.selectAllOption();
	    
	    model.addAttribute("oList", oList);
        model.addAttribute("aList", aList);
        model.addAttribute("pList", pList);
        model.addAttribute("list", list);
		return "views/sohwa/cartPage";
	}
	
	
	
	@GetMapping("deleteCart.so")
	public String deleteCart(@RequestParam("cartKeyNo") int cartKeyNo, Model model) {
		int result = pService.deleteCart(cartKeyNo);
		if(result > 0) {
			return "redirect:cartView.so";
		}else {
			throw new ProductException("삭제 실패");
		}
	}
	
	@PostMapping("deleteCarts.so")
	@ResponseBody
	public String deleteCarts(@RequestBody List<String> cartKeyNos) {
		System.out.println(cartKeyNos); //[309, 307]
		int result = pService.deleteCarts(cartKeyNos);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	
	
	@PostMapping("updateCartEno.so")
	@ResponseBody
	public String updateCartEno(@RequestParam("cartKeyNo") int cartKeyNo, @RequestParam("price") int price, @RequestParam("quantity") int quantity, Model model) {
		Cart c = new Cart();
		c.setCartKeyNo(cartKeyNo);
		c.setProductEno(quantity);
		c.setBuyPrice(price);
		System.out.println(price);
		int result = pService.updateCartEno(c);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@GetMapping("centerView.so")
	public String qnaView(Model model) {
		Member loginUser = (Member)model.getAttribute("loginUser");
		
		ArrayList<Product> pList = pService.selectAllProduct();
		ArrayList<Attachment> aList = pService.selectAllPhoto();
		
		
		
		if(loginUser != null) {
			String id = ((Member)model.getAttribute("loginUser")).getMemberId();
			ArrayList<Qna> qList = pService.selectMyQna(id);
			model.addAttribute("pList", pList);
			model.addAttribute("aList", aList);
			model.addAttribute("qList", qList);
			return "views/sohwa/centerPage";
		}else {
			return "views/sohwa/centerPage2";
		}
		
	}

	
	@PostMapping("insertQna.so")
	@ResponseBody
	public String insertQna(@RequestParam("productNo") int productNo, @RequestParam("questionContent") String questionContent, Model model) {
		String id = ((Member)model.getAttribute("loginUser")).getMemberId();
		Qna q = new Qna();
		q.setProductNo(productNo);
		q.setQuestionContent(questionContent);
		q.setMemberId(id);
		int result = pService.insertQna(q);
		
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@GetMapping("adminQnaListView.so")
	public String adminQnaListView(@RequestParam(value="searchType", defaultValue="1") String searchType, @RequestParam(value="keyword", defaultValue="") String keyword, @RequestParam(value="status", defaultValue="1") int status, @RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request, Model model) {
		
		if(status ==1) {
			int listCount = pService.getListQnaCount();
			int currentPage = page;
			PageInfo pi = new PageInfo();
			pi = Pagination.getPageInfo(currentPage, listCount, 10);
			
			ArrayList<Qna> qList = new ArrayList<>();
			ArrayList<Product> pList = pService.selectAllProduct();
			ArrayList<Attachment> aList = pService.selectAllPhoto();
			
			if(keyword != null) {
				HashMap<String, String> map = new HashMap<>();
				map.put("keyword", keyword);
				map.put("searchType", searchType);
				qList = pService.searchKeyword(map);
			}else {
				qList = pService.selectQna();
			}
			
			model.addAttribute("status", status);
			model.addAttribute("pList", pList);
			model.addAttribute("aList", aList);
			model.addAttribute("qList", qList);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "views/sohwa/(admin)qnaList";
			
		}else {
			int listCount = pService.getListQnaYCount();
			int currentPage = page;
			PageInfo pi = new PageInfo();
			pi = Pagination.getPageInfo(currentPage, listCount, 10);
			
			ArrayList<Qna> qList = new ArrayList<>();
			ArrayList<Product> pList = pService.selectAllProduct();
			ArrayList<Attachment> aList = pService.selectAllPhoto();
			
			if(keyword != null) {
				HashMap<String, String> map = new HashMap<>();
				map.put("keyword", keyword);
				map.put("searchType", searchType);
				qList = pService.searchYKeyword(map);
			}else {
				qList = pService.selectQnaY();
			}
			model.addAttribute("status", status);
			model.addAttribute("pList", pList);
			model.addAttribute("aList", aList);
			model.addAttribute("qList", qList);
			model.addAttribute("pi", pi);
			model.addAttribute("loc", request.getRequestURI());
			return "views/sohwa/(admin)qnaList";
		}
		
	}
	
//	@GetMapping("answerY.so")
//	public String selectAnswerY(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request, Model model){
//		
//		
//	}
	
	@PostMapping("insertAnswer.so")
	public String insertAnswer(@RequestParam("questionNo") int questionNo, @RequestParam("answerContent") String answerContent) {
		Qna q = new Qna();
		q.setAnswerContent(answerContent);
		q.setQuestionNo(questionNo);
		
		int result = pService.updateAnswer(q);
		
		if(result > 0) {
			return "redirect:adminQnaListView.so";
		}else {
			throw new ProductException("문의답글 등록 실패");
		}
	}
	
	
	@GetMapping("deleteQna.so")
	public String deleteQna(@RequestParam("questionNo") int questionNo, Model model) {
		int result = pService.deleteQna(questionNo);
		Member loginUser = (Member)model.getAttribute("loginUser");
		if(loginUser.getMemberIsAdmin() == "Y") {
			if(result > 0) {
				return "redirect:adminQnaListView.so";
			}else {
				throw new ProductException("문의 삭제 실패");
			}
		}else {
			if(result > 0) {
				return "redirect:centerView.so";
			}else {
				throw new ProductException("문의 삭제 실패");
			}
		}
		
		
		
	}
	
	
	
	@PostMapping("enrollReview.so")
	public String insertReview(@ModelAttribute Review r, @RequestParam("file") MultipartFile upload, Model model) {
		
		String id = ((Member)model.getAttribute("loginUser")).getMemberId();
		r.setMemberId(id);
		String name="sohwa";
		String[] returnArr = imageStorage.saveImage(upload, name);
		Attachment a = new Attachment();
		int result1 = pService.insertReview(r);
		int result2 = 0;
		
		
		if(returnArr != null) {
			a.setPhotoRename(returnArr[0]);
			a.setPhotoPath(returnArr[1]);
			a.setPhotoLevel(0);
			a.setBoardNo(r.getReviewNo());
			result2 = pService.insertReviewPhoto(a);
		}
		
		System.out.println(r);  //Review(reviewNo=59, reviewContent=wagfwe, reviewUploadDate=null, reviewScore=4, reviewModifyDate=null, reviewStatus=null, productNo=111, memberId=ming11)
		
		int[] scoreArr = pService.selectAllProductScore(r.getProductNo());
		double avgScore = 0;
		double total = 0;
		for (int i = 0; i < scoreArr.length; i++) {
			 total += scoreArr[i];
		}

		// 배열의 길이로 나누어 평균 계산
		avgScore = total / scoreArr.length;
		
		System.out.println(total);
		System.out.println(avgScore);
		
		Product p = new Product();
		p.setProductNo(r.getProductNo());
		p.setProductScore(avgScore);
		
		int result3 = pService.updateScore(p);
		
		if(result1 > 0) {
			return "redirect:productDetail.so?productNo=" + r.getProductNo();
		}else {
			throw new ProductException("리뷰 등록 실패");
		}
		
	}
	
	//리뷰삭제
	@GetMapping("deleteReview.so")
	public String deleteReview(@RequestParam("reviewNo") int reviewNo, @RequestParam("productNo") int productNo) {
		
		String name="sohwa";
		int result = pService.deleteReview(reviewNo);
		String deleteRename = pService.deleteSelectReview(reviewNo);
		System.out.println(deleteRename);
		
		Boolean sf = imageStorage.deleteImage(deleteRename, name);
		System.out.println(sf);
		
		
		if(result > 0) {
			return "redirect:productDetail.so?productNo=" + productNo;
		}else {
			throw new ProductException("리뷰 삭제 실패");
		}
	}
	
	
	@PostMapping("updateReview.so")
	public String updateReview(@RequestParam(value="deleteRename", defaultValue="") String deleteRename, @RequestParam("reviewContent") String reviewContent, @RequestParam("updateFile") MultipartFile updateFile, @RequestParam("productNo") int productNo, @RequestParam("updateReviewScore") int reviewScore, @RequestParam("reviewNo") int reviewNo) {
		
		Review r = new Review();
		Attachment a = new Attachment();
		String name="sohwa";
		r.setProductNo(productNo);
		r.setReviewContent(reviewContent);
		r.setReviewNo(reviewNo);
		r.setReviewScore(reviewScore);
		
		System.out.println(reviewScore);
		System.out.println(reviewContent);
		System.out.println(reviewNo);
		System.out.println(updateFile.getOriginalFilename());
		
		int result1 = 0;
		int result2 = 0;
		//deleteRename도 비어있고, updateFile이 비어있을 때 (사진 X)
		//deleteRename은 비어있고, updateFile이 있을 때 (새로 추가)
		//deleteRename 차있고, updateFile이 비어있을 때 (사진 그대로 유지)
		//deleteRename 차있고, updateFile도 차있을 때 (사진 변경)
		
		//deleteRename도 비어있고, updateFile이 비어있을 때 (사진 X)
		if(deleteRename.equals("")) {
			if(updateFile.getOriginalFilename().equals("")) {
				result1 = pService.updateReviewInfo(r);
				
			}else if(!updateFile.getOriginalFilename().equals("")) {
				//deleteRename은 비어있고, updateFile이 있을 때 (새로 추가)
				a.setBoardNo(reviewNo);
				a.setBoardType(6);
				a.setPhotoLevel(0);
				String[] returnArr = imageStorage.saveImage(updateFile, name);
				
				if(returnArr != null) {
					a.setPhotoPath(returnArr[1]);
					a.setPhotoRename(returnArr[0]);
					
					System.out.println(a);
					result2 = pService.updateReviewPhoto(a);
				}
				result1 = pService.updateReviewInfo(r);
			}
		}else {
			//deleteRename 차있고, updateFile이 비어있을 때 (사진 그대로 유지)
			if(updateFile.getOriginalFilename().equals("")) {
				result1 = pService.updateReviewInfo(r);
			}else {
				//deleteRename 차있고, updateFile도 차있을 때 (사진 변경)
				Boolean sf = imageStorage.deleteImage(deleteRename, name);
				System.out.println("sf:" + sf);
				pService.deleteReviewPhoto(reviewNo);
				a.setBoardNo(reviewNo);
				a.setBoardType(6);
				a.setPhotoLevel(0);
				String[] returnArr = imageStorage.saveImage(updateFile, name);
				
				if(returnArr != null) {
					a.setPhotoPath(returnArr[1]);
					a.setPhotoRename(returnArr[0]);
					
					System.out.println(a);
					result2 = pService.updateReviewPhoto(a);
				}
				result1 = pService.updateReviewInfo(r);
			}
			
			
			
		}
		
		
		int[] scoreArr = pService.selectAllProductScore(r.getProductNo());
		double avgScore = 0;
		double total = 0;
		for (int i = 0; i < scoreArr.length; i++) {
			 total += scoreArr[i];
		}

		// 배열의 길이로 나누어 평균 계산
		avgScore = total / scoreArr.length;
		
		Product p = new Product();
		p.setProductNo(r.getProductNo());
		p.setProductScore(avgScore);
		
		int result3 = pService.updateScore(p);
		
		
		
		
		
		return "redirect:productDetail.so?productNo=" + productNo;
		
	}
	
	
}

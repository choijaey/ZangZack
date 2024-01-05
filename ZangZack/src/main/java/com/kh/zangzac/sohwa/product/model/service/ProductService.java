package com.kh.zangzac.sohwa.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Cart;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;

public interface ProductService {

	int insertProduct(Product p);

	int insertProductPhoto(ArrayList<Attachment> coreList);

	int insertOption(ArrayList<Option> list);

	int getListCount(String categoryNo);

	ArrayList<Product> selectProductList(PageInfo pi, HashMap<String, String> categoryMap);

	ArrayList<Attachment> selectPhotoList(String categoryNo);

	Product selectProductDetail(int productNo);

	ArrayList<Attachment> selectPhotoDetail(int productNo);

	ArrayList<Option> optionDetail(int productNo);

	int getListCountKeyword(String keyword);

	ArrayList<Product> searchProduct(PageInfo pi, HashMap<String, String> map);

	ArrayList<Attachment> searchPhoto(HashMap<String, String> map);

	ArrayList<Product> selectAllProduct();

	ArrayList<Attachment> selectAllPhoto();

	ArrayList<Option> selectProductOption(int productNo);

	ArrayList<Product> selectDeleteProduct();

	ArrayList<Attachment> selectDeletePhoto();

	int insertCart(Cart c);


	
}

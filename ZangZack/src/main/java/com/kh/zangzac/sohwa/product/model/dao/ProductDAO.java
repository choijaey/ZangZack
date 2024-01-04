package com.kh.zangzac.sohwa.product.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;

@Mapper
public interface ProductDAO {

	int insertProduct(Product p);

	int insertProductPhoto(ArrayList<Attachment> coreList);

	int insertOption(ArrayList<Option> list);

	int getListCount(int categoryNo);

	ArrayList<Product> selectProductList(HashMap<String, Integer> map, RowBounds rowBounds);

	ArrayList<Attachment> selectPhotoList(int categoryNo);

	Product selectProductDetail(int productNo);

	ArrayList<Attachment> selectPhotoDetail(int productNo);

	ArrayList<Option> optionDetail(int productNo);

	int getListCountKeyword(String keyword);

	ArrayList<Product> searchProduct(String keyword, RowBounds rowBounds);

	ArrayList<Attachment> searchPhoto(String keyword);

	

	
}

package com.kh.zangzac.sohwa.product.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Cart;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;
import com.kh.zangzac.sohwa.product.model.vo.Qna;

@Mapper
public interface ProductDAO {

	int insertProduct(Product p);

	int insertProductPhoto(ArrayList<Attachment> coreList);

	int insertOption(ArrayList<Option> list);

	int getListCount(String categoryNo);

	ArrayList<Product> selectProductList(HashMap<String, String> map, RowBounds rowBounds);

	ArrayList<Attachment> selectPhotoList(String categoryNo);

	Product selectProductDetail(int productNo);

	ArrayList<Attachment> selectPhotoDetail(int productNo);

	ArrayList<Option> optionDetail(int productNo);

	int getListCountKeyword(String keyword);

	ArrayList<Product> searchProduct(HashMap<String, String> map, RowBounds rowBounds);

	ArrayList<Attachment> searchPhoto(HashMap<String, String> map);

	ArrayList<Product> selectAllProduct();

	ArrayList<Attachment> selectAllPhoto();

	ArrayList<Option> selectProductOption(int productNo);

	ArrayList<Product> selectDeleteProduct();

	ArrayList<Attachment> selectDeletePhoto();

	int insertCart(Cart c);

	ArrayList<Cart> memberCart(String id);

	ArrayList<Option> selectAllOption();

	int deleteCart(int cartKeyNo);

	int deleteCarts(List<String> cartKeyNos);

	int updateCartEno(Cart c);

	int insertQna(Qna q);

	ArrayList<Qna> selectMyQna(String id);

	int getListQnaCount();

	ArrayList<Qna> selectQna();

	int updateAnswer(Qna q);

	ArrayList<Qna> selectProductQna(int productNo);

	

	
}

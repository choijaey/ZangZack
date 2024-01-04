package com.kh.zangzac.sohwa.product.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.sohwa.product.model.dao.ProductDAO;
import com.kh.zangzac.sohwa.product.model.vo.Attachment;
import com.kh.zangzac.sohwa.product.model.vo.Option;
import com.kh.zangzac.sohwa.product.model.vo.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO pDAO;

	@Override
	public int insertProduct(Product p) {
		return pDAO.insertProduct(p);
	}

	@Override
	public int insertProductPhoto(ArrayList<Attachment> coreList) {
		return pDAO.insertProductPhoto(coreList);
	}

	@Override
	public int insertOption(ArrayList<Option> list) {
		return pDAO.insertOption(list);
	}

	@Override
	public int getListCount(int categoryNo) {
		return pDAO.getListCount(categoryNo);
	}

	@Override
	public ArrayList<Product> selectProductList(PageInfo pi, HashMap<String, Integer> map) {
		int offset = (pi.getCurrentPage()-1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return pDAO.selectProductList(map, rowBounds);
	}

	@Override
	public ArrayList<Attachment> selectPhotoList(int categoryNo) {
		return pDAO.selectPhotoList(categoryNo);
	}

	

	
	
}

package com.kh.zangzac.product.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.product.model.dao.ProductDAO;
import com.kh.zangzac.product.model.vo.Attachment;
import com.kh.zangzac.product.model.vo.Option;
import com.kh.zangzac.product.model.vo.Product;

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

	

	
	
}

package com.kh.zangzac.product.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.product.model.vo.Attachment;
import com.kh.zangzac.product.model.vo.Option;
import com.kh.zangzac.product.model.vo.Product;

@Mapper
public interface ProductDAO {

	int insertProduct(Product p);

	int insertProductPhoto(ArrayList<Attachment> coreList);

	int insertOption(ArrayList<Option> list);

	int getListCount(int categoryNo);

	

	
}

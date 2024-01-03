package com.kh.zangzac.product.model.service;

import java.util.ArrayList;

import com.kh.zangzac.product.model.vo.Attachment;
import com.kh.zangzac.product.model.vo.Option;
import com.kh.zangzac.product.model.vo.Product;

public interface ProductService {

	int insertProduct(Product p);

	int insertProductPhoto(ArrayList<Attachment> coreList);

	int insertOption(ArrayList<Option> list);

	int getListCount(int categoryNo);


	
}

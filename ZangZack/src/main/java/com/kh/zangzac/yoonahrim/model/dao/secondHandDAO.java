package com.kh.zangzac.yoonahrim.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

@Mapper
public interface secondHandDAO {

	int insertSeconHand(secondHandProduct sp);

	int updateSeconHand(secondHandProduct sp);

	ArrayList<HashMap<String, Object>> updateSeconHand(String id);

	int insertAttmSecondHand(ArrayList<Attachment> detailList);

	int insertSecondHand(secondHandProduct sp);
	
	

}

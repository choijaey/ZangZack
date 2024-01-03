package com.kh.zangzac.yoonahrim.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

public interface secondHandService {

	int insertSecondHand(secondHandProduct sp);

	int updateSeconHand(secondHandProduct sp);

	ArrayList<HashMap<String, Object>> selectSeconHand(String id);

	int insertAttmSecondHand(ArrayList<Attachment> detailList);



	
	

}

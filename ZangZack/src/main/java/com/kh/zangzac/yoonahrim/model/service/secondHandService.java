package com.kh.zangzac.yoonahrim.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

public interface secondHandService {

	int insertSecondHand(secondHandProduct sp);

	int updateSecondHand(secondHandProduct sp);

	int insertAttmSecondHand(ArrayList<Attachment> detailList);

	ArrayList<secondHandProduct> selectMyList(String memberId);

	int updateAttmSecondHand(ArrayList<Attachment> detailList);

	ArrayList<Attachment> selectAttachmentList(secondHandProduct sp);




	
	

}

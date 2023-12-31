package com.kh.zangzac.yoonahrim.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

@Mapper
public interface secondHandDAO {

	int insertSecondHand(secondHandProduct sp);

	int updateSecondHand(secondHandProduct sp);

	int insertAttmSecondHand(ArrayList<Attachment> detailList);

	ArrayList<secondHandProduct> selectMyList(String memberId);

	int updateAttmSecondHand(ArrayList<Attachment> detailList);

	ArrayList<Attachment> selectAttachmentList(secondHandProduct sp);



	
	

}

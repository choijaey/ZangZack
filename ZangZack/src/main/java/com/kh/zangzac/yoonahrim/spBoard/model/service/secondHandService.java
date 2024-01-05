package com.kh.zangzac.yoonahrim.spBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.Reply;
import com.kh.zangzac.yoonahrim.spBoard.model.vo.secondHandProduct;

public interface secondHandService {

	int insertSecondHand(secondHandProduct sp);

	int updateSecondHand(secondHandProduct sp);

	int insertAttmSecondHand(ArrayList<Attachment> detailList);

	ArrayList<secondHandProduct> selectMyList(String memberId);

	ArrayList<Attachment> selectAttachmentList(Integer spNo);

	int deleteAttmSecondHand(int spNo);

	int insertReply(Reply r);

	ArrayList<Reply> selectReply(int spNo);




	
	

}

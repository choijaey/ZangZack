package com.kh.zangzac.yoonahrim.spBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.common.reply.model.vo.Reply;
import com.kh.zangzac.yoonahrim.spBoard.model.vo.secondHandProduct;

public interface secondHandService {

	int insertSecondHand(secondHandProduct sp);

	int updateSecondHand(secondHandProduct sp);

	int insertAttmSecondHand(ArrayList<Photo> detailList);

	ArrayList<secondHandProduct> selectMyList(secondHandProduct sp);

	ArrayList<Photo> selectAttachmentList(Integer spNo);

	int deleteAttmSecondHand(int spNo);

	int updateBooking(int spNo);

	int updateBookingundo(int spNo);

	int soldout(int spNo);

	int markDelete(int spNo);

	ArrayList<secondHandProduct> selectSeconHand(secondHandProduct sp);

	ArrayList<Reply> selectReply(int spNo);

	ArrayList<Photo> selectPhotoSeconHand(secondHandProduct sp);

	int updateAttmSecondHand(ArrayList<Photo> detailList);

	int deleteAttm(ArrayList<String> delRename);

	int deleteAttmForN(int spNo);

	void updatePhotoLevel(int spNo);




	
	

}

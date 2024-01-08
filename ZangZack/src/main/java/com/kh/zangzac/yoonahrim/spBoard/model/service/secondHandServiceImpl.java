package com.kh.zangzac.yoonahrim.spBoard.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.spBoard.model.dao.secondHandDAO;
import com.kh.zangzac.yoonahrim.spBoard.model.vo.secondHandProduct;

@Service
public class secondHandServiceImpl implements secondHandService{
	
	@Autowired
	private secondHandDAO spDAO;
	
	@Override
	public int insertSecondHand(secondHandProduct sp) {
		return spDAO.insertSecondHand(sp);
	}

	@Override
	public int updateSecondHand(secondHandProduct sp) {
		return spDAO.updateSecondHand(sp);
	}

	@Override
	public int insertAttmSecondHand(ArrayList<Attachment> detailList) {
		return spDAO.insertAttmSecondHand(detailList);
	}

	@Override
	public ArrayList<secondHandProduct> selectMyList(String memberId) {
		return (ArrayList)spDAO.selectMyList(memberId);
	}

	@Override
	public ArrayList<Attachment> selectAttachmentList(Integer spNo) {
		return (ArrayList)spDAO.selectAttachmentList(spNo);
	}

	@Override
	public int deleteAttmSecondHand(int spNo) {
		return spDAO.deleteAttmSecondHand(spNo);
	}

	@Override
	public int updateBooking(int spNo) {
		return spDAO.updateBooking(spNo);
	}

	@Override
	public int updateBookingundo(int spNo) {
		return spDAO.updateBookingundo(spNo);
	}

	@Override
	public int soldout(int spNo) {
		return spDAO.soldout(spNo);
	}

	@Override
	public int markDelete(int spNo) {
		return spDAO.markDelete(spNo);
	}





	


}

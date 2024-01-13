package com.kh.zangzac.yoonahrim.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonahrim.model.dao.secondHandDAO;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

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
	public int updateAttmSecondHand(ArrayList<Attachment> detailList) {
		return spDAO.updateAttmSecondHand(detailList);
	}

	@Override
	public ArrayList<Attachment> selectAttachmentList(secondHandProduct sp) {
		return (ArrayList)spDAO.selectAttachmentList(sp);
	}




	


}

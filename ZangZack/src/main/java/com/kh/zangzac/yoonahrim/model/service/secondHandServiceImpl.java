package com.kh.zangzac.yoonahrim.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.yoonahrim.model.dao.secondHandDAO;
import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

@Service
public class secondHandServiceImpl implements secondHandService{
	
	@Autowired
	private secondHandDAO spDAO;
	
	@Override
	public int insertSeconHand(secondHandProduct sp) {
		return spDAO.insertSeconHand(sp);
	}

	@Override
	public int updateSeconHand(secondHandProduct sp) {
		return spDAO.updateSeconHand(sp);
	}


}

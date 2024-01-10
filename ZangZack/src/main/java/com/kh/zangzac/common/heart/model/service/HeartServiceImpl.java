package com.kh.zangzac.common.heart.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.heart.model.dao.HeartDAO;
import com.kh.zangzac.common.heart.model.vo.Heart;

@Service
public class HeartServiceImpl implements HeartService{
	
	@Autowired
	private HeartDAO hDAO;
	
	@Override
	public void selectHeart(Heart h) {
		hDAO.selectHeart(h);
	}

}

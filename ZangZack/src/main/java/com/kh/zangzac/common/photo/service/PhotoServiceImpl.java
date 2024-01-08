package com.kh.zangzac.common.photo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.dao.PhotoDAO;
import com.kh.zangzac.common.photo.model.vo.Photo;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	private PhotoDAO pDAO;
	
	@Override
	public ArrayList<Photo> selectBoardPhoto(SelectCondition b) {
		return pDAO.selectBoardPhoto(b);
	}

}

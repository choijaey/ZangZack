package com.kh.zangzac.yoonseo.model.servcie;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonseo.model.dao.CampDAO;
import com.kh.zangzac.yoonseo.model.vo.CampingGround;

@Service
public class CampServiceImpl implements CampService {
	
	@Autowired
	private CampDAO cDAO;

	@Override
	public int insertCamp(CampingGround camp) {
		return cDAO.insertCamp(camp);
	}

	@Override
	public int insertCampImg(ArrayList<Attachment> campList) {
		return cDAO.insertCampImg(campList);
	}

	@Override
	public int insertInfoImg(ArrayList<Attachment> infoList) {
		return cDAO.insertInfoImg(infoList);
	}

	@Override
	public int getListCount(int i) {
		return cDAO.getListCount(i);
	}

	
	
}

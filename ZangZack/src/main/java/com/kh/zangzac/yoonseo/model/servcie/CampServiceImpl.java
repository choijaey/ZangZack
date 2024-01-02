package com.kh.zangzac.yoonseo.model.servcie;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.zangzac.yoonseo.model.vo.CampingGround;
import com.kh.zangzac.yoonseo.model.dao.CampDAO;

public class CampServiceImpl implements CampService {
	
	@Autowired
	private CampDAO cDAO;

	@Override
	public int insertCamp(CampingGround camp) {
		return cDAO.insertCamp(camp);
	}

}

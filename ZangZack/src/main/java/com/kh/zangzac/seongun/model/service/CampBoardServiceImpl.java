package com.kh.zangzac.seongun.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.model.dao.CampBoardDAO;
import com.kh.zangzac.seongun.model.vo.CampBoard;

@Service
public class CampBoardServiceImpl implements CampBoardService{
	@Autowired
	private CampBoardDAO cDAO;
	
	@Override
	public int getListCount(int i) {
		return cDAO.getListCount(i);
	}

	@Override
	public ArrayList<CampBoard> selectBoardList(PageInfo pi, int i) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage()-1 )* pi.getBoardLimit(), 
		pi.getBoardLimit());
		return cDAO.selectBoardList(i, rowBounds);
	}

}

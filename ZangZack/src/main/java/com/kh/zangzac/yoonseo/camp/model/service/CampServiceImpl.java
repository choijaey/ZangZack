package com.kh.zangzac.yoonseo.camp.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.yoonseo.camp.model.dao.CampDAO;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

@Service
public class CampServiceImpl implements CampService {
	
	@Autowired
	private CampDAO cDAO;

	@Override
	public int insertCamp(CampingGround camp) {
		return cDAO.insertCamp(camp);
	}

	@Override
	public int insertInfoImg(ArrayList<Attachment> infoList) {
		return cDAO.insertInfoImg(infoList);
	}

	@Override
	public int getListCount(int i) {
		return cDAO.getListCount(i);
	}

	@Override
	public ArrayList<CampingGround> selectCampList(PageInfo pi, int i) {
		
		int offSet = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds row = new RowBounds(offSet, pi.getBoardLimit());
		
		return cDAO.selectCampList(i,row);
		
		
	}

	@Override
	public ArrayList<CampingGround> selectMapList(int i) {
		return cDAO.selectMapList(i);
	}

	@Override
	public CampingGround selectCampingDetail(int no) {
		return cDAO.selectCampingDetail(no);
	}

	@Override
	public ArrayList<Photo> selectPhoto(Integer no) {
		return cDAO.selectPhoto(no);
	}

	@Override
	public ArrayList<Attachment> selectInfoPhoto(int no) {
		return cDAO.selectInfoPhoto(no);
	}

	@Override
	public int getRecomendationCount(String recomendation) {
		return cDAO.getRecomendationCount(recomendation);
	}

	@Override
	public ArrayList<CampingGround> selectRecomendationList(PageInfo pi, String recomendation) {
		return cDAO.selectRecomendationList(recomendation);
	}

	@Override
	public ArrayList<Photo> selectOnePhoto(int i) {
		return cDAO.selectOnePhoto(i);
	}

	@Override
	public int insertCampImg(ArrayList<Photo> campList) {
		return cDAO.insertCampImg(campList);
	}



	
	

}
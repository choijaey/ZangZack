package com.kh.zangzac.jaeyoung.campingReview.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.jaeyoung.campingReview.model.dao.CampingReviewDAO;
import com.kh.zangzac.jaeyoung.campingReview.model.vo.CampingReview;

@Service
public class CampingReviewServiceImpl implements CampingReviewService{

	@Autowired
	CampingReviewDAO crDAO;
	
	@Override
	public int insertCampingReview(CampingReview cr) {
		return crDAO.insertCampingReview(cr);
	}

}

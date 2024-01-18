package com.kh.zangzac.jaeyoung.campingReview.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.jaeyoung.campingReview.model.vo.CampingReview;

@Mapper
public interface CampingReviewDAO {

	int insertCampingReview(CampingReview cr);

}

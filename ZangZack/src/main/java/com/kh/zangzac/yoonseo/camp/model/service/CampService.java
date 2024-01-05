package com.kh.zangzac.yoonseo.camp.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

public interface CampService {
	int insertCamp(CampingGround camp);

	int insertCampImg(ArrayList<Attachment> campList);

	int insertInfoImg(ArrayList<Attachment> infoList);

	int getListCount(int i);

	ArrayList<CampingGround> selectCampList(PageInfo pi, int i);

	ArrayList<CampingGround> selectMapList(int i);

	CampingGround selectCampingDetail(int no);

	ArrayList<Attachment> selectPhoto(Integer no);

	ArrayList<Attachment> selectInfoPhoto(int no);

	int getRecomendationCount(String recomendation);

	ArrayList<CampingGround> selectRecomendationList(PageInfo pi, String recomendation);

	ArrayList<Attachment> selectOnePhoto(int i);

}

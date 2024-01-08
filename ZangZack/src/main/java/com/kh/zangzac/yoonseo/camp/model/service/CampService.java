package com.kh.zangzac.yoonseo.camp.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

public interface CampService {
	int insertCamp(CampingGround camp);

	int insertInfoImg(ArrayList<Attachment> infoList);

	int getListCount(int i);

	ArrayList<CampingGround> selectCampList(PageInfo pi, int i);

	ArrayList<CampingGround> selectMapList(int i);

	CampingGround selectCampingDetail(int no);

	ArrayList<Photo> selectPhoto(Integer no);

	ArrayList<Attachment> selectInfoPhoto(int no);

	int getRecomendationCount(String recomendation);

	ArrayList<CampingGround> selectRecomendationList(PageInfo pi, String recomendation);

	ArrayList<Photo> selectOnePhoto(int i);

	int insertCampImg(ArrayList<Photo> campList);

}

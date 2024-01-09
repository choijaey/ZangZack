package com.kh.zangzac.yoonseo.camp.model.dao;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;


import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

@Mapper
public interface CampDAO {

	int insertCamp(CampingGround camp);

	int insertCampImg(ArrayList<Photo> campList);

	int insertInfoImg(ArrayList<Photo> infoList);

	int getListCount(int i);

	ArrayList<CampingGround> selectCampList(int i, RowBounds row);

	ArrayList<CampingGround> selectMapList(int i);

	CampingGround selectCampingDetail(int no);

	ArrayList<Photo> selectPhoto(Integer no);

	ArrayList<Photo> selectInfoPhoto(int no);

	int getRecomendationCount(String recomendation);

	ArrayList<CampingGround> selectRecomendationList(String recomendation);

	ArrayList<Photo> selectOnePhoto(int i);

	int getAllCount();

	ArrayList<CampingGround> selectAllList(RowBounds row);

	int stateUpdate(Properties prop);
}

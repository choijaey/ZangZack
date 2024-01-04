package com.kh.zangzac.yoonseo.camp.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonseo.camp.model.vo.CampingGround;

@Mapper
public interface CampDAO {

	int insertCamp(CampingGround camp);

	int insertCampImg(ArrayList<Attachment> campList);

	int insertInfoImg(ArrayList<Attachment> infoList);

	int getListCount(int i);

	ArrayList<CampingGround> selectCampList(int i, RowBounds row);

	ArrayList<CampingGround> selectMapList(int i);

	CampingGround selectCampingDetail(int no);

	ArrayList<Attachment> selectPhoto(Integer no);
}

package com.kh.zangzac.yoonseo.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonseo.model.vo.CampingGround;

@Mapper
public interface CampDAO {

	int insertCamp(CampingGround camp);

	int insertCampImg(ArrayList<Attachment> campList);

	int insertInfoImg(ArrayList<Attachment> infoList);

}

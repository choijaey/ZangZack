package com.kh.zangzac.common.photo.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.vo.Photo;

@Mapper
public interface PhotoDAO {
	ArrayList<Photo> selectBoardPhoto(SelectCondition b);
	
	int insertPhotoCampBoard(ArrayList<Photo> fileList);

}

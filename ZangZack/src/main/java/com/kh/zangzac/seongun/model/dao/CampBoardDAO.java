package com.kh.zangzac.seongun.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.seongun.model.vo.CampBoard;

@Mapper
public interface CampBoardDAO {

	int getListCount(int i);

	ArrayList<CampBoard> selectBoardList(int i, RowBounds rowBounds);

}

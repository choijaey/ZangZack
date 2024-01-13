package com.kh.zangzac.seongun.campboard.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

@Mapper
public interface CampBoardDAO {

	int getListCount(int i);

	ArrayList<CampBoard> selectBoardList(int i, RowBounds rowBounds);

	int insertCampBoard(CampBoard board);

	int insertAttmCampBoard(ArrayList<Attachment> fileList);

	CampBoard selectBoard(int cbNo);

	int updateCount(int cbNo);

}

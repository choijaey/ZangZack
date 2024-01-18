package com.kh.zangzac.seongun.campboard.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.zangzac.common.photo.model.vo.Photo;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;
import com.kh.zangzac.seongun.common.model.vo.SearchBoard;

@Mapper
public interface CampBoardDAO {

	int getListCount(int i);

	ArrayList<CampBoard> selectBoardList(int i, RowBounds rowBounds);

	int insertCampBoard(CampBoard board);

	int insertAttmCampBoard(ArrayList<Photo> fileList);

	CampBoard selectBoard(int cbNo);

	int updateCount(int cbNo);

	int searchListCount(SearchBoard search);
	
	ArrayList<CampBoard> searchBoardList(SearchBoard search, RowBounds rowBounds);

	int deleteCampBoard(int cbNo);

	int updateCampBoard(CampBoard b);
}

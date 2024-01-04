package com.kh.zangzac.seongun.campboard.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.campboard.model.vo.CampBoard;

public interface CampBoardService {

	int getListCount(int i);

	ArrayList<CampBoard> selectBoardList(PageInfo pi, int i);

	int insertCampBoard(CampBoard board);

	int insertAttmCampBoard(ArrayList<Attachment> fileList);

}

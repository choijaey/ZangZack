package com.kh.zangzac.seongun.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.seongun.model.vo.CampBoard;

public interface CampBoardService {

	int getListCount(int i);

	ArrayList<CampBoard> selectBoardList(PageInfo pi, int i);

}

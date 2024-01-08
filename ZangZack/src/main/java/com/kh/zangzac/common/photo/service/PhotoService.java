package com.kh.zangzac.common.photo.service;

import java.util.ArrayList;

import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.photo.model.vo.Photo;

public interface PhotoService {

	ArrayList<Photo> selectBoardPhoto(SelectCondition b);

}

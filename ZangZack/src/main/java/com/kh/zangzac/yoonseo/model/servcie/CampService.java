package com.kh.zangzac.yoonseo.model.servcie;

import java.util.ArrayList;


import com.kh.zangzac.common.model.vo.Attachment;
import com.kh.zangzac.yoonseo.model.vo.CampingGround;


public interface CampService {
      
	int insertCamp(CampingGround camp);

	int insertCampImg(ArrayList<Attachment> campList);

	int insertInfoImg(ArrayList<Attachment> infoList);

}

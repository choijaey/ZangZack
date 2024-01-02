package com.kh.zangzac.yoonseo.model.servcie;

import org.springframework.stereotype.Service;

import com.kh.zangzac.yoonseo.model.vo.CampingGround;

@Service
public interface CampService {
      
	int insertCamp(CampingGround camp);

}

package com.kh.zangzac.common.heart.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.heart.model.vo.Heart;

@Mapper
public interface HeartDAO {

	void selectHeart(Heart h);

}

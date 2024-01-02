package com.kh.zangzac.yoonahrim.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.yoonahrim.model.vo.secondHandProduct;

@Mapper
public interface secondHandDAO {

	int insertSeconHand(secondHandProduct sp);

	int updateSeconHand(secondHandProduct sp);

	

}

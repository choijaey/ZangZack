package com.kh.zangzac.jaeyoung.chat.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.jaeyoung.chat.model.vo.Chatter;

@Mapper
public interface ChatDAO {

	int checkCount(Chatter checkChatter);

	int insertChatRoom(Chatter c);

	ArrayList<Chatter> selectChatterList(int i);

}

package com.kh.zangzac.common.reply.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.reply.model.vo.Reply;

@Mapper
public interface ReplyDAO {

	ArrayList<Reply> selectReply(Reply sendReply);

	int insertReply(Reply reply);
	
}

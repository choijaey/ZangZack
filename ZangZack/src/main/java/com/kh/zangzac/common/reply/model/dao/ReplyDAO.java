package com.kh.zangzac.common.reply.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.reply.model.vo.Reply;

@Mapper
public interface ReplyDAO {
	ArrayList<Reply> selectReply(Reply sendReply);
	
	ArrayList<Reply> selectReply(SelectCondition b);

	int insertReply(Reply reply);

	int countReply(Reply reply);
	
	Reply selectReplyOne(Reply reply);
	
}

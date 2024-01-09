package com.kh.zangzac.common.reply.model.service;

import java.util.ArrayList;

import com.kh.zangzac.common.reply.model.vo.Reply;

public interface ReplyService {
	int insertReply(Reply reply);
	
	ArrayList<Reply> selectReply(Reply reply);

	int countReply(Reply reply);
	
	Reply selectReplyOne(Reply reply);

}

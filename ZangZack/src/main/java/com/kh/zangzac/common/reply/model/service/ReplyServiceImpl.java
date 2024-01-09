package com.kh.zangzac.common.reply.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.common.model.vo.PageInfo;
import com.kh.zangzac.common.model.vo.SelectCondition;
import com.kh.zangzac.common.reply.model.dao.ReplyDAO;
import com.kh.zangzac.common.reply.model.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO rDAO;
	
	@Override
	public ArrayList<Reply> selectReply(Reply reply) {
		return rDAO.selectReply(reply);
	}
	
	@Override
	public int insertReply(Reply reply) {
		return rDAO.insertReply(reply);
	}

	@Override
	public int countReply(SelectCondition b) {
		return rDAO.countReply(b);
	}

	@Override
	public ArrayList<Reply> selectReply(SelectCondition b) {
		return rDAO.selectReply(b);
	}

	@Override
	public ArrayList<Reply> replyLimitList(SelectCondition b, PageInfo pi) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage()-1 )* pi.getBoardLimit(), pi.getBoardLimit());
		return rDAO.replyLimitList(b, rowBounds) ;
	}

	@Override
	public Reply selectReplyOne(Reply reply) {
		return rDAO.selectReplyOne(reply);
	}
}

package com.kh.zangzac.common.reply.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int countReply(Reply reply) {
		return rDAO.countReply(reply);
	}
}

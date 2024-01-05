package com.kh.zangzac.jaeyoung.chat.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.jaeyoung.chat.model.dao.ChatDAO;
import com.kh.zangzac.jaeyoung.chat.model.vo.Chatter;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	ChatDAO cDAO;
	
	@Override
	public int checkCount(Chatter checkChatter) {
		
		return cDAO.checkCount(checkChatter);
	}

	@Override
	public int insertChatRoom(Chatter c) {
		return cDAO.insertChatRoom(c);
	}

	@Override
	public ArrayList<Chatter> selectChatterList(int i) {
		return cDAO.selectChatterList(i);
	}

}

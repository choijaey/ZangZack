package com.kh.zangzac.jaeyoung.chat.model.service;

import java.util.ArrayList;

import com.kh.zangzac.jaeyoung.chat.model.vo.Chatter;

public interface ChatService {

	int checkCount(Chatter checkChatter);

	int insertChatRoom(Chatter c);

	ArrayList<Chatter> selectChatterList(int i);

}

package com.kh.zangzac.jaeyoung.eventBoard.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.jaeyoung.eventBoard.model.dao.EventBoardDAO;

@Service
public class EventBoardServiceImpl implements EventBoardService {
	
	@Autowired
	EventBoardDAO ebDAO;

}

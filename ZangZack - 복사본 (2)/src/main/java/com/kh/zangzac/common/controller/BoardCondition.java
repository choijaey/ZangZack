package com.kh.zangzac.common.controller;

import org.springframework.stereotype.Controller;

import com.kh.zangzac.common.model.vo.SelectCondition;

@Controller
public class BoardCondition {
	
	public SelectCondition selectBoard(int x, int y) {
		SelectCondition b = new SelectCondition();
		b.setBoardNo(x);
		b.setBoardType(y);
		return b;
	}
}

package com.kh.zangzac.ming.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.zangzac.ming.member.model.dao.MemberDAO;
import com.kh.zangzac.ming.member.model.vo.Member;


@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO mDAO;
	
	@Override
	public int insertMember(Member m) {
		return mDAO.insertMember(m);
	}

	@Override
	public Member login(Member m) {
		return mDAO.login(m);
	}

	@Override
	public ArrayList<Member> selectId(Member m) {
		return mDAO.selectId(m);
	}


	@Override
	public int updateNewPwd(HashMap<String, String> map) {
		return mDAO.updateNewPwd(map);
	}

	
	}

	


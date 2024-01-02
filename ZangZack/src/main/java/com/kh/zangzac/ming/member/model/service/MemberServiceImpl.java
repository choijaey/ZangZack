package com.kh.zangzac.ming.member.model.service;

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

	
	}

	


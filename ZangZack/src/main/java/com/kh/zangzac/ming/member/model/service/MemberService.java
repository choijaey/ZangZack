package com.kh.zangzac.ming.member.model.service;

import java.util.ArrayList;

import com.kh.zangzac.ming.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member m);

	Member login(Member m);

	ArrayList<Member> selectId(Member m);


	
	

}

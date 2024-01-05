package com.kh.zangzac.ming.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.ming.member.model.vo.Member;


@Mapper
public interface MemberDAO {

	// 회원가입
	int insertMember(Member m);
	
	// 로그인
	Member login(Member m);
	
	//아이디 찾기
	ArrayList<Member> selectId(Member m);

	//비밀번호 찾기
	int updateNewPwd(Member m);
	
	//아이디 중복체크
	int checkId(String memberId);
	
	//이메일 중복체크
	int checkEmail(String memberEmail);

	int deleteMember(String memberId);

}

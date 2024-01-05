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
	
	//회원가입
	@Override
	public int insertMember(Member m) {
		return mDAO.insertMember(m);
	}
	
	//로그인
	@Override
	public Member login(Member m) {
		return mDAO.login(m);
	}
	
	//아이디 찾기
	@Override
	public ArrayList<Member> selectId(Member m) {
		return mDAO.selectId(m);
	}

	
	//비밀번호 랜덤코드 재설정
	@Override
	public int updateNewPwd(Member m) {
		return mDAO.updateNewPwd(m);
	}
	
	//아이디 중복체크
	@Override
	public int checkId(String memberId) {
		return mDAO.checkId(memberId);
	}
	
	//이메일 중복체크
	@Override
	public int checkEmail(String memberEmail) {
		return mDAO.checkEmail(memberEmail);
	}

	@Override
	public int deleteMember(String memberId) {
		return mDAO.deleteMember(memberId);
	}

	
	}

	


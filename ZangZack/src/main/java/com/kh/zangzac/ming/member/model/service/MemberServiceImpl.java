package com.kh.zangzac.ming.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public int changePwd(HashMap<String, String> map) {
		return mDAO.changePwd(map);
	}
	
	//마이페이지 수정
	@Override
	public int updateMemberName(HashMap<String, String> map) {
		return mDAO.updateMemberName(map);
	}

	@Override
	public int updateMemberNickname(HashMap<String, String> map) {
		return mDAO.updateMemberNickname(map);
	}

	@Override
	public int updatememberPhone(HashMap<String, String> map) {
		return mDAO.updatememberPhone(map);
	}

	@Override
	public int updatememberEmail(HashMap<String, String> map) {
		return mDAO.updatememberEmail(map);
	}

	@Override
	public int updatememberAddress(HashMap<String, String> map) {
		return mDAO.updatememberAddress(map);
	}

	@Override
	public int updateMemberProfile(Member m) {
		return mDAO.updateMemberProfile(m);
	}
	
	//관리자페이지
	@Override
	public ArrayList<Member> selectMembers() {
		return mDAO.selectMembers();
	}

	@Override
	public int updateInfo(Properties prop) {
		return mDAO.updateInfo(prop);
	}



	
}

	


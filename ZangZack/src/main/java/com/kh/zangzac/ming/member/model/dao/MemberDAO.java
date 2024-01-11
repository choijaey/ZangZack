package com.kh.zangzac.ming.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

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

	//회원탈퇴
	int deleteMember(String memberId);

	int changePwd(HashMap<String, String> map);

	//마이페이지
	int updateMemberName(HashMap<String, String> map);

	int updateMemberNickname(HashMap<String, String> map);

	int updatememberPhone(HashMap<String, String> map);

	int updatememberEmail(HashMap<String, String> map);

	int updatememberAddress(HashMap<String, String> map);

	int updateMemberProfile(Member m);

	ArrayList<Member> selectMembers();

	int updateInfo(Properties prop);

	int adminUpdateNickName(Member m);

	int adminUpdateName(Member m);

}
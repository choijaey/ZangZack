package com.kh.zangzac.ming.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.ming.member.model.vo.Member;

public interface MemberService {

	int insertMember(Member m);

	Member login(Member m);

	ArrayList<Member> selectId(Member m);

	int updateNewPwd(Member m);

	int checkId(String memberId);

	int checkEmail(String memberEmail);

	int deleteMember(String memberId);

	int changePwd(HashMap<String, String> map);
	
	//마이페이지
	int updateMemberName(HashMap<String, String> map);

	int updateMemberNickname(HashMap<String, String> map);

	int updatememberPhone(HashMap<String, String> map);

	int updatememberEmail(HashMap<String, String> map);

	int updatememberAddress(HashMap<String, String> map);

	int updateMemberProfile(Member m);
	
	//관리자페이지
	ArrayList<Member> selectMembers();


	
	

}

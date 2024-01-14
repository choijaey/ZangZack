package com.kh.zangzac.ming.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

import com.kh.zangzac.common.model.vo.PageInfo;
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
	ArrayList<Member> selectMembers(int i, PageInfo pi);

	int updateInfo(Properties prop);

	int adminUpdateNickName(Member m);

	int adminUpdateName(Member m);

	int getListCount();

	int searchList(HashMap<String, String> map);

	ArrayList<Member> searchtNoticeList(PageInfo pi, HashMap<String, String> map);

	String getAccessToken(String code);

	HashMap<String, Object> getUserInfo(String access_Token);
	

	
	

}

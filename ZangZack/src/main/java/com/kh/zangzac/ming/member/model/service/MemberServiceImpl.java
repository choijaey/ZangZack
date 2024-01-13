package com.kh.zangzac.ming.member.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kh.zangzac.common.model.vo.PageInfo;
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
	public ArrayList<Member> selectMembers(int i, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return mDAO.selectMembers(i, rowBounds);
	}

	@Override
	public int updateInfo(Properties prop) {
		return mDAO.updateInfo(prop);
	}

	@Override
	public int adminUpdateNickName(Member m) {
		return mDAO.adminUpdateNickName(m);
	}

	@Override
	public int adminUpdateName(Member m) {
		return mDAO.adminUpdateName(m);
	}

	@Override
	public int getListCount() {
		return mDAO.getListCount();
	}

	@Override
	public int searchList(HashMap<String, String> map) {
		return mDAO.searchList(map);
	}

	@Override
	public ArrayList<Member> searchtNoticeList(PageInfo pi, HashMap<String, String> map) {
		int offset = (pi.getCurrentPage() - 1)*pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		return mDAO.searchtNoticeList(map,rowBounds);
	}

	@Override
	   public String getAccessToken(String code) {
	      String access_Token = "";
	      String refresh_Token = "";
	      String reqURL = "https://kauth.kakao.com/oauth/token";

	      try {
	         URL url = new URL(reqURL);
	            
	         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	         // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            
	         conn.setRequestMethod("POST");
	         conn.setDoOutput(true);
	         // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            
	         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	         StringBuilder sb = new StringBuilder();
	         sb.append("grant_type=authorization_code");
	            
	         sb.append("&client_id=18ae7b1bab696aadfa39200fae9ad11b"); //본인이 발급받은 key
	         sb.append("&redirect_uri=http://localhost:8080/kakaoLogin"); // 본인이 설정한 주소
	            
	         sb.append("&code=" + code);
	         System.out.println("code = "+code);
	         bw.write(sb.toString());
	         System.out.println(sb.toString());
	         bw.flush();
	            
	         // 결과 코드가 200이라면 성공
	         int responseCode = conn.getResponseCode();
	         
	         // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	         BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	         String line = "";
	         String result = "";
	            
	         while ((line = br.readLine()) != null) {
	            result += line;
	         }
	            
	         // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	         JsonElement element = JsonParser.parseString(result);
	            
	         access_Token = element.getAsJsonObject().get("access_token").getAsString();
	         refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
	         br.close();
	         bw.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      return access_Token;
	   }



	
}

	
package com.kh.zangzac.ming.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.ming.member.model.vo.Member;


@Mapper
public interface MemberDAO {

	int insertMember(Member m);

	Member login(Member m);

	ArrayList<Member> selectId(Member m);

	int updateNewPwd(Member m);

}

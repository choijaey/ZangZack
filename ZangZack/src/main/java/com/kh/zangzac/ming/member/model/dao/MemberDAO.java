package com.kh.zangzac.ming.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.zangzac.ming.member.model.vo.Member;


@Mapper
public interface MemberDAO {

	int insertMember(Member m);

}

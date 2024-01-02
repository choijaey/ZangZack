package com.kh.zangzac.ming.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String nickName;
	private Date birth;
	private String address;
	private String phone;
	private Date enrollDate;
	private String isAdmin;
	private String status;
	private String profileRename;
	private String profilePath;
}

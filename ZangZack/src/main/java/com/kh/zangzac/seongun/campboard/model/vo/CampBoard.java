package com.kh.zangzac.seongun.campboard.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CampBoard {
	private int cbNo;
	private String cbTitle;
	private String cbContent;
	private int cbCount;
	private Date cbCreateDate;
	private Date cbModifyDate;
	private String cbStatus;
	private String memberId;
	private int categoryNo;
	private String memberName;
	private String categoryName;
	private int heartCount;
	private String photoPath;
	private String photoRename;
}

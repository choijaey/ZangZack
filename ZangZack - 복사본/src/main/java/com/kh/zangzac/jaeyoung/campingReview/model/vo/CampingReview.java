package com.kh.zangzac.jaeyoung.campingReview.model.vo;

import com.google.cloud.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CampingReview {
	private int crNo;
	private String crTitle;
	private String crContent;
	private Date crCreateDate;
	private Date crModifyDate;
	private int crCount;
	private String memberId;
	private int categoryNo;
	private double crStar;

}

package com.kh.zangzac.yoonseo.model.vo;

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
public class CampingGround {
	private int cgNo;
	private String cgName;
	private String cgRegion;
	private String cgAddress;
	private String cgPhone;
	private String cgType;
	private int cgCount;
	private Date cgDate;
	private String cgArea;
	private String cgSeason;
	private String cgManageDate;
	private String cgBooking;
	private String cgPage;
	private double cgStarPoint;
	private String cgAmenity;
	private String cgInfo;
	private char cgRecomendation;
	private char cgStatus;
	private int categoryNo;
	
	
    
}

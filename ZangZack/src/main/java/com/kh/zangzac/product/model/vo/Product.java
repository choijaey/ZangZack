package com.kh.zangzac.product.model.vo;

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
public class Product {
	
	private int productNo;
	private String productName;
	private int productPrice;
	private Date productUploadDate;
	private String productStatus;
	private int productScore;
	private int categoryNo;
	private String productCompany;

}

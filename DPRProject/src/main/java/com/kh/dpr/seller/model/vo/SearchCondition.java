package com.kh.dpr.seller.model.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SearchCondition {
	
	String productName;
	String productBrand;
	String sellerId;
	int productNo;
	int categoryNo;
	int categoryNo2;
	int claimCode;
	
	

}

package com.kh.dpr.board.model.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component

public class Product implements Serializable {

	private static final long serialVersionUID = 79337L;

	private int productNo;
	private int productId;
	private int categoryNo; 
	private String sellerId;
	private String productName;
	private int productPrice;
	private int productAmount;
	private String productStatus;
	private String productInfo;
	private int productCount;

	private int fileCount;

}

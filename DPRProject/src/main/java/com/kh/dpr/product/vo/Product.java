package com.kh.dpr.product.model.vo;

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
public class Product {
	private int productNo;
	private int categoryNo2;
	private int sizeId;
	private String sellerId;
	private int productId;
	private String productName;
	private int productPrice;
	private int productAmount;
	private String productStatus;
	private String productInfo;
	private int productCount;
}

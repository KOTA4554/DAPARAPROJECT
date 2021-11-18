package com.kh.dpr.product.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

	private int productno;
	private int categoryno2;
	private int sizeid;
	private String sellerid;
	private int productid;
	private String productname;
	private int productprice;
	private int productamount;
	private String productstatus;
	private String productinfo;
	private int productcount;
	
}

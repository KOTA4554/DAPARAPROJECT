package com.kh.dpr.claim.model.service;

import java.util.List;

import com.kh.dpr.claim.model.vo.Claim;
import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;



public interface ClaimService  {

	
	 Order selectOneOrder(String userId); 


	 OrderDetail selectOneDetail(String userId); 


	 Seller selectSeller(int detailNo); 


	 String selectProduct(int productNo);


	int insertClaim(Claim claim);


	int insertClaim1(Claim claim);


	int insertClaim2(Claim claim);


	String selectClaim3(Claim claim);


	String selectProduct(Product product);


	String selectOrder(Order order);




}

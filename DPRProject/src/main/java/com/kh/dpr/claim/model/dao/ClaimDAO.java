package com.kh.dpr.claim.model.dao;

import com.kh.dpr.claim.model.vo.Claim;
import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;

public interface ClaimDAO {

	 Order selectOrder(String userId);

	 OrderDetail selectOrderDetail(String userId);

	 Seller selectSeller(int detailNo); 

	 String selectProduct(int productNo);

	int insertClaim(Claim claim);

	int insertClaim1(Claim claim);

	int insertClaim2(Claim claim);
}
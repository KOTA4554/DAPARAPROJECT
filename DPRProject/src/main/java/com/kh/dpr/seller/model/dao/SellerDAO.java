package com.kh.dpr.seller.model.dao;

import com.kh.dpr.seller.model.vo.Seller;

public interface SellerDAO {
	
	public int insertSeller(Seller seller);

	public int checkSellerDuplicate(String sellerId);

	public Seller selectOneSeller(String sellerId);
	
}

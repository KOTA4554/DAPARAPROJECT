package com.kh.dpr.seller.model.service;

import com.kh.dpr.seller.model.vo.Seller;

public interface SellerService {

	public int insertSeller(Seller seller);

	public int checkSellerDuplicate(String sellerId);

	public Seller selectOneSeller(String sellerId);
	
	public int updateSeller(Seller seller);
	
	public int deleteSeller(String sellerId);
	
}

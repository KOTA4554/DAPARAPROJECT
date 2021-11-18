package com.kh.dpr.seller.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dpr.seller.model.dao.SellerDAO;
import com.kh.dpr.seller.model.vo.Seller;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerDAO sellerDAO;
	
	@Override
	public int insertSeller(Seller seller) {
		return sellerDAO.insertSeller(seller);
	}

	@Override
	public int checkSellerDuplicate(String sellerId) {
		return sellerDAO.checkSellerDuplicate(sellerId);
	}

	@Override
	public Seller selectOneSeller(String sellerId) {
		return sellerDAO.selectOneSeller(sellerId);
	}

}

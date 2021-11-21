package com.kh.dpr.prod.model.dao;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;

public interface ProdDAO {

	Product selectProd(int prodNo);
	
	Seller selectSeller(int prodNo);
	
	int viewcount(int prodNo);
	
	
}

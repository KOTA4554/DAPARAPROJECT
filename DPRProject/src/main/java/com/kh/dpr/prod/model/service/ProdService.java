package com.kh.dpr.prod.model.service;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;

public interface ProdService {

	Product selectProd(int prodNo);
	
	Seller selectSeller(int prodNo);
	
	

}

package com.kh.dpr.prod.model.service;

import java.util.List;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;

public interface ProdService {

	Product selectProd(int prodNo);
	
	Seller selectSeller(int prodNo);
	
	List<String> loadImage(int prodNo);

}

package com.kh.dpr.prod.model.dao;

import com.kh.dpr.product.model.vo.Product;

public interface ProdDAO {

	Product selectProd(int prodNo);
	
	int viewcount(int prodNo);
	
	
}

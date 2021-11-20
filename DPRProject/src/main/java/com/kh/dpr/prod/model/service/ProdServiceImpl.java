package com.kh.dpr.prod.model.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.kh.dpr.prod.model.dao.ProdDAO;
import com.kh.dpr.product.model.vo.Product;

@Service
public class ProdServiceImpl implements ProdService  {
	
	@Inject
	ProdDAO ProdDAO;

	@Override
	public Product selectProd(int prodNo) {
		
		int result = ProdDAO.viewcount(prodNo);
		
		return ProdDAO.selectProd(prodNo);
		
	}

}

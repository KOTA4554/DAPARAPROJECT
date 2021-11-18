package com.kh.dpr.board.model.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dpr.board.model.dao.ProductDAO;
import com.kh.dpr.product.model.vo.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	ProductDAO productDAO;

	@Override
	public List<Map<String, String>> selectProductList(int cPage, int numPerPage) {
		
		return productDAO.selectProductList(cPage, numPerPage);
	}

	@Override
	public int selectProductTotalContents() {
		
		return productDAO.selectProductTotalContents();
	}

	@Override
	public List<Product> selectList(int maxpage) {
		
		return productDAO.seletList(maxpage);
	}
	
	
}

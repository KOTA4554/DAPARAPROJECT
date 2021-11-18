package com.kh.dpr.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.dpr.board.model.vo.Product;

public interface ProductService {


	List<Map<String, String>> selectProductList(int cPage, int numPerPage);

	int selectProductTotalContents();

	List<Product> selectList(int maxpage);

	
	
	
}

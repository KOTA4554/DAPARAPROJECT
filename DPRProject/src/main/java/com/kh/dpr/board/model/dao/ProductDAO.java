package com.kh.dpr.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.dpr.product.model.vo.Product;

public interface ProductDAO {


	List<Map<String, String>> selectProductList(int cPage, int numPerPage);

	int selectProductTotalContents();

	List<Product> seletList(int maxpage);

	List<Product> seletListTop(int maxpage);
}

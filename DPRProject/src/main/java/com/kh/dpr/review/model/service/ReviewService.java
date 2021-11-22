package com.kh.dpr.review.model.service;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.review.model.vo.Review;

public interface ReviewService {
	
	Product selectProduct(int detailNo);
	
	String selectCompany(int productNo);
	
	String selectSizeName(int detailNo);
	
	int insertReview(Review review);
}

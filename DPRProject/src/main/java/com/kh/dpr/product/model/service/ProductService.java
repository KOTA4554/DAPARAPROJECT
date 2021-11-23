package com.kh.dpr.product.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dpr.product.model.dao.ProductDAO;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.product.model.vo.ProductImage;
import com.kh.dpr.review.model.vo.Review;
import com.kh.dpr.seller.model.vo.Seller;

@Service
public class ProductService {

	@Autowired
	ProductDAO productDAO;
	
	public int insertProduct(Product product, List<ProductImage> imgList) {
		
		int productResult = productDAO.insertProduct(product);
		
		if(productResult < 0) {
			System.out.println("게시글 등록 실패");
		} else {			
			for(ProductImage img : imgList) {
				int imageResult = productDAO.insertImage(img);
				if(imageResult == 0) {
					System.out.println("첨부파일 전송 실패");
				}
			}
		}
		
		return productResult;
	}

	public int selectPno() {
		return productDAO.selectPno();
	}

	public int insertTempProduct() {
		return productDAO.insertTempProduct();
	}

	public int selectTempProduct() {
		return productDAO.selectTempProduct();
	}

	public int insertOption(Product opt) {
		return productDAO.insertOption(opt);
	}

	public List<Map<String, String>> selectProductList(String sellerId, int prodPage, int numPerPage) {
		return productDAO.selectProductList(sellerId, prodPage, numPerPage);
	}

	public int selectTotalProduct(String sellerId) {
		return productDAO.selectTotalProduct(sellerId);
	}

	public List<Map<String, String>> searchProductList(Map map, int prodPage, int numPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Review> selectReivewList(String sellerId) {
		
		return productDAO.selectReviewList(sellerId);
	}

	public Product selectRproduct(int reviewNo) {

		return productDAO.selectRproduct(reviewNo);
	}


}

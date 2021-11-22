package com.kh.dpr.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dpr.product.model.dao.ProductDAO;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.product.model.vo.ProductImage;

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

}

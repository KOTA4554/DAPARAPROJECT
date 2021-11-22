package com.kh.dpr.product.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.product.model.vo.ProductImage;

@Repository
public class ProductDAO {

	@Autowired
	SqlSessionTemplate sqlSession;

	public int insertProduct(Product product) {
		// TODO Auto-generated method stub
		return sqlSession.update("manageSQL.insertProduct", product);
	}

	public int insertImage(ProductImage img) {
		return sqlSession.insert("manageSQL.insertImage", img);
	}

	public int selectPno() {
		return sqlSession.selectOne("manageSQL.selectPno");
	}

	public int insertTempProduct() {
		return sqlSession.insert("manageSQL.insertTempProduct");
	}

	public int selectTempProduct() {
		return sqlSession.selectOne("manageSQL.selectTempProduct");
	}

	public int insertOption(Product opt) {
		return sqlSession.insert("manageSQL.insertOption", opt);
	}
	
	
}

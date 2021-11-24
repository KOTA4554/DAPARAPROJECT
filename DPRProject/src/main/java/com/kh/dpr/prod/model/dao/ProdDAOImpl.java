package com.kh.dpr.prod.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;


@Repository
public class ProdDAOImpl implements ProdDAO {
	
	@Inject
	SqlSessionTemplate sqlSession;

	@Override
	public Product selectProd(int prodNo) {

		return sqlSession.selectOne("prod.selectProd", prodNo);
	}

	@Override
	public Seller selectSeller(int prodNo) {
		
		return sqlSession.selectOne("prod.selectSeller", prodNo);
		
	}
	
	@Override
	public int viewcount(int prodNo) {
		
		return sqlSession.update("prod.viewcount", prodNo);
	}
	
	@Override
	public List<String> loadImage(int prodNo) {


		return sqlSession.selectList("prod.loadImage", prodNo);
	
	}

	
}

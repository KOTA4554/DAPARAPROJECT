package com.kh.dpr.board.model.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.dpr.product.model.vo.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSessionTemplate sqlSession;
	

	@Override							
	public List<Map<String, String>> selectProductList(int cPage, int numPerPage) {
		// 데이터베이스에서 특정 갯수의 행만 가져오는 객체
		RowBounds rows = new RowBounds((cPage - 1)* numPerPage, numPerPage);
		
		return sqlSession.selectList("product.selectProductList", null, rows);
	}

	@Override
	public int selectProductTotalContents() {
		
		return sqlSession.selectOne("product.selectProductTotalContents");
	}

	@Override
	public List<Product> seletList(int maxpage) {
		
		return sqlSession.selectList("product.selectList", maxpage);
	}

	

}

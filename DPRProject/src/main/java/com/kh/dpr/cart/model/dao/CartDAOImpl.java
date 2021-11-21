package com.kh.dpr.cart.model.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.dpr.cart.model.vo.Cart;


@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSessionTemplate sqlSession;
	
	@Override
	public int addCart(Cart c) {

		return sqlSession.insert("cart.addCart", c);
	}


	@Override
	public List<Cart> cartList(String userId){
		
		
		
		return sqlSession.selectList("cart.cartList",userId);
		
	};
	
}

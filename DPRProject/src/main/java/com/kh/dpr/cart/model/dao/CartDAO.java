package com.kh.dpr.cart.model.dao;

import java.util.List;

import com.kh.dpr.cart.model.vo.Cart;

public interface CartDAO {
	
	int addCart(Cart c);	

	List<Cart> cartList(String userId);
	
}

package com.kh.dpr.cart.model.service;

import java.util.List;

import com.kh.dpr.cart.model.vo.Cart;

public interface CartService {

	int addCart(Cart c);
	
	List<Cart> cartList(String userId);
	
	
}

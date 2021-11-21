package com.kh.dpr.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.dpr.cart.model.service.CartService;
import com.kh.dpr.cart.model.vo.Cart;
import com.kh.dpr.member.model.vo.Member;
import com.kh.dpr.product.model.vo.Product;

@Controller
public class CartController {
	
	@Inject
	CartService CartService;

	
	@RequestMapping("/prod/cart.do")
	public String cart(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);

        Member member = (Member)session.getAttribute("member");

        String userId = member.getUserId();
		System.out.println("/cart.do가 실행되었음");
		List<Cart> c = CartService.cartList(userId);
		int count =  c.size();
		
		
		model.addAttribute("cart", c);
        model.addAttribute("count", count);
		
	return "prod/cart";
		
	}
	
	
	@RequestMapping("/addcart.do")
	public void addCart(@RequestParam String userId, int productNo, int cartAmount, String sizeName, String sellerCompany, HttpServletResponse response) throws IOException {
	
		System.out.println("장바구니 추가 접근 확인" + productNo);
		
		Cart c = new Cart(userId,productNo,cartAmount,sizeName,sellerCompany);
		
		System.out.println("장바구니 추가 접근 확인" + c);
		
		int result = CartService.addCart(c);
				
		System.out.println(result);
		
		response.getWriter().print(result);
		
	
		
		
	}
	
	
	
		
		
	}
	
	
	
	


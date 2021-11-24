package com.kh.dpr.prod.controller;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.dpr.cart.model.vo.Cart;
import com.kh.dpr.prod.model.service.ProdService;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;

@Controller
public class ProdController {
	
	@Inject
	ProdService ProdService;

	
	
	
	@RequestMapping("/prod_detail.do")
	public String detail(@RequestParam int prodNo, Model model) {
		
		System.out.println("상세보기 접근 확인" + prodNo);
		
		Product prod = ProdService.selectProd(prodNo);
		Seller seller2 = ProdService.selectSeller(prodNo);
		
		// List<String> image = ProdService.loadImage(prodNo);
		
		
		model.addAttribute("prod", prod);
		model.addAttribute("seller2", seller2);
		
		System.out.println(prod);
		System.out.println("셀러정보 :" + seller2);
		
		
		return "prod/prod_detail";
	}
	

}

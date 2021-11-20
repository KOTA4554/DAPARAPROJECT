package com.kh.dpr.prod.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.dpr.prod.model.service.ProdService;
import com.kh.dpr.product.model.vo.Product;

@Controller
public class ProdController {
	
	@Inject
	ProdService ProdService;

	
	
	@RequestMapping("/prod/cart.do")
	public String cart1() {
		
		System.out.println("/cart.do가 실행되었음");
		
		
		return "prod/cart";
		
		
		
		
	}


	

	
	@RequestMapping("/prod_detail.do")
	public String detail(@RequestParam int prodNo, Model model) {
		
		System.out.println("상세보기 접근 확인" + prodNo);
		
		Product prod = ProdService.selectProd(prodNo);
		
		model.addAttribute("prod", prod);
		
		System.out.println(prod);
		
		
		return "prod/prod_detail";
	}
	

}

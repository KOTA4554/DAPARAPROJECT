package com.kh.dpr.prod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdController {

	@RequestMapping("/prod/cart.do")
	public String cart() {
		
		System.out.println("/cart.do가 실행되었음");
		
		
		return "prod/cart";
		
		
		
		
	}
	
	

	@RequestMapping("/prod/detail.do")
	public String cart1() {
		
		System.out.println("/detail.do가 실행되었음");
		
		
		return "prod/prod_detail";
		
		
		
		
	}
	
	
	
}

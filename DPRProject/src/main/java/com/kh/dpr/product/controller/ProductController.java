package com.kh.dpr.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("seller/addProduct.do")
	public String addProduct() {
		
		return "productManage/addProduct";
	}
}

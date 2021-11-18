package com.kh.dpr.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.kh.dpr.board.model.service.ProductService;
import com.kh.dpr.board.model.vo.Product;
import com.kh.dpr.common.Utils;

@Controller

public class MainController {

	@Inject
	ProductService productService;

	@RequestMapping("/product/clothes.do")
	public String selectBoardList(
			@RequestParam(value="cPage", required=false, defaultValue="1") int cPage,
			Model model
			) {
		
		// 한 페이지당 게시글 수
		int numPerPage = 10;
		
		// 현재 페이지의 게시글 수
		List<Map<String, String>> list = productService.selectProductList(cPage, numPerPage);
		
		// 전체 게시글 수
		int totalContents = productService.selectProductTotalContents();
		
		// 페이지 처리 Utils 사용하기
		String pageBar = Utils.getPageBar(totalContents, cPage, numPerPage, "boardList.do");
		
		System.out.println("list : " + list);
		System.out.println("pageBar : " + pageBar);
		
		
		model.addAttribute("list", list);
		model.addAttribute("totalContents", totalContents);
		model.addAttribute("numPerPage", numPerPage);
		model.addAttribute("pageBar", pageBar);
		
		return "product/clothes";
	}
	
	

	@RequestMapping(value="/main.do", method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	public void ajaxMain(HttpServletResponse response) throws IOException {
		
		
		
		
		
		int maxpage = 4;
		List<Product> product = productService.selectList(maxpage);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		new Gson().toJson(product, response.getWriter());

		System.out.println(product);
		
		if(product != null) {
			
		}else {
			
		}
		
	
		
		
		
	}
	
}

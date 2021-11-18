package com.kh.dpr.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	//ReviewService reviewSerivce;
	
	@RequestMapping("/reviewForm.do")
	public String reviewForm() {
		
		
		//model.addAttribute("detailNo", detailNo);
		
		return "review/reviewForm";
		//return null;
	}
	
	
}

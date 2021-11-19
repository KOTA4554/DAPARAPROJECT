package com.kh.dpr.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MypageController {

	@RequestMapping("/myPage/myPage.do")
	public String myPage(
			@RequestParam String userId,
			Model model
			) {
		
		// userId 일치하는 order
		System.out.println("userId : " + userId);
		// userId 일치하는 orderDetail
		
		return "myPage/myPage";
	}
	
	
}

package com.kh.dpr.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {

	@RequestMapping("myPage/myPage.do")
	public String myPage(
			//@RequestParam String userId
			//Model model
			) {
		
		// userId 일치하는 order
		
		// userId 일치하는 orderDetail
		
		return "myPage/myPage";
	}
	
	
}

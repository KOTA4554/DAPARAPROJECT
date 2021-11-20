package com.kh.dpr.claim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.dpr.claim.model.service.ClaimService;

@Controller
public class ClaimController {
	@Autowired
	//ClaimService claimServie;
	@RequestMapping("/claim/claim.do")
	public String selectClaim() {
		
		
		
		return "claim/claim";
	}
}
	
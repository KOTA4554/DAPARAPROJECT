package com.kh.dpr.claim.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.dpr.claim.model.service.ClaimService;
import com.kh.dpr.claim.model.vo.Claim;
import com.kh.dpr.member.model.vo.Member;
import com.kh.dpr.order.model.vo.Order;
import com.kh.dpr.order.model.vo.OrderDetail;
import com.kh.dpr.product.model.vo.Product;
import com.kh.dpr.seller.model.vo.Seller;



@Controller
public class ClaimController {
	@Autowired
	ClaimService ClaimService;
	@RequestMapping("/claim/claim.do")
	public String selectClaim(HttpServletRequest request, Model model) {
		
	HttpSession session = request.getSession(false);
		
		Member member = (Member)session.getAttribute("member");
		
		String userId = member.getUserId();
		
		// userId 일치하는 order
		Order order = ClaimService.selectOneOrder(userId);
				
		// orderNo 일치하는 orderDetail
		OrderDetail orderDetail = ClaimService.selectOneDetail(userId); 
				
		
		//seller 조회
		int productNo = orderDetail.getProductNo();
				
		// detailNo
		int detailNo = orderDetail.getDetailNo();
					
		Seller seller3 = ClaimService.selectSeller(detailNo);
					
		String prodName = ClaimService.selectProduct(productNo);
		
			
		
		model.addAttribute("order", order);
		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("seller3", seller3);
		model.addAttribute("prodName", prodName);
		
		
		return "claim/claim";
	}
	
	@RequestMapping("/claim/change.do")
	public String insertClaimInfo(Claim claim) {
		
		int result = ClaimService.insertClaim(claim);
		
		
		return "claim/claim";
		
		
	}
	
	@RequestMapping("/claim/refund.do")
	public String insertClaimInfo1(Claim claim) {
		
		int result = ClaimService.insertClaim1(claim);
		
		
		return "claim/claim";
		
		
	}
	
	@RequestMapping("/claim/cancel.do")
	public String insertClaimInfo2(Claim claim) {
		
		int result = ClaimService.insertClaim2(claim);
		
		
		return "claim/claim";
		
		
	}
}
	
package com.kh.dpr.claim.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String insertClaimInfo(Claim claim, Model model) {
		
		int result = ClaimService.insertClaim(claim);
		
		String loc = "/myPage/myPage.do";
		String msg = "";
		
		// orderDetail db에 넣기
		if(result > 0) {
			msg = " 교환 등록 성공!";
		} else {
			msg = "클레임 등록 실패!";
		
		}
		
		model.addAttribute("loc",loc);
		model.addAttribute("msg",msg);
		
		return "common/msg";
		
		
	}
	@RequestMapping("/claim/refund.do")
	public String insertClaimInfo1(Claim claim,Model model) {
		
		int result = ClaimService.insertClaim1(claim);
		
		String loc = "/myPage/myPage.do";
		String msg = "";
		
		// orderDetail db에 넣기
		if(result > 0) {
			msg = " 환불 등록 성공!";
		} else {
			msg = "클레임 등록 실패!";
		
		}
		
		model.addAttribute("loc",loc);
		model.addAttribute("msg",msg);
		
		return "common/msg";
		
		
	}
	
	@RequestMapping("/claim/cancel.do")
	public String insertClaimInfo2(Claim claim, Model model) {
		
		int result = ClaimService.insertClaim2(claim);
		
		String loc = "/myPage/myPage.do";
		String msg = "";
		
		// orderDetail db에 넣기
		if(result > 0) {
			msg = " 취소 등록 성공!";
		} else {
			msg = "클레임 등록 실패!";
		
		}
		
		model.addAttribute("loc",loc);
		model.addAttribute("msg",msg);
		
		return "common/msg";
		
		
	}
	

	@RequestMapping("/claim/claimlist.do")
	public String selectClaimList(@RequestParam(value="prodPage", required=false, defaultValue="1") int prodPage,
			@RequestParam(value="searchNm", required=false) String searchNm,
			@RequestParam(value="searchCate1", required=false, defaultValue="999") int searchCate1,
			@RequestParam(value="searchCate2", required=false, defaultValue="999") int searchCate2,
			@RequestParam(value="startDate", required=false) Date startDate,
			@RequestParam(value="endDate", required=false) Date endDate,
			@RequestParam(value="claimState", required=false, defaultValue="0") int saleState,
			@RequestParam(value="searchBrand", required=false, defaultValue="") String searchBrand,
			@RequestParam(value="searchPno", required=false, defaultValue="0") int searchPno,
			Seller seller, Model model) {
		
		int numPerPage = 15;
		String sellerId = seller.getSellerId();
		
		Map map= new HashMap();
		
		map.put("sellerId", sellerId);
		map.put("searchNm", searchNm);
		map.put("searchCate1", searchCate1);
		map.put("searchCate2", searchCate2);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("saleState", saleState);
		map.put("searchBrand", searchBrand);
		map.put("searchPno", searchPno);
		
		System.out.println(map.get("sellerId"));
		System.out.println(map.get("searchNm"));
		System.out.println(map.get("searchCate1"));
		System.out.println("페이지 : " + prodPage);
		System.out.println("searchNm : " + searchNm);
		
		return "claim/claimlist";
	}
	
}
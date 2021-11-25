
package com.kh.dpr.seller.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.dpr.seller.model.service.SellerService;
import com.kh.dpr.seller.model.vo.Seller;

@Controller
@SessionAttributes({"seller"})
public class SellerController {
	
	String loc = "/";
	String msg = "";
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@RequestMapping("member/memberSignUp.do")
	public String memberSignUp() {
		
		return "member/memberSignUp";
	}
	
	@RequestMapping("member/memberLoginView.do")
	public String memberLogin() {
		
		return "member/memberLogin";
	}
	
	@RequestMapping("seller/sellerMain.do")
	public String sellerMain() {
		
		return "seller/sellerMain";
	}
	
	@RequestMapping("member/sellerSignUpEnd.do")
	public String sellerSingUpEnd(Seller seller, Model model) {
		
		System.out.println("컨트롤러 도착 확인");
		System.out.println("Seller 정보 확인" + seller);
		
		String beforePw = seller.getSellerPw();
		String encryptPw = bcryptPasswordEncoder.encode(beforePw);
		seller.setSellerPw(encryptPw);
		
		int result = sellerService.insertSeller(seller);
		
		if(result > 0) {
			return "seller/sellerMain";
		} else {
			msg = "회원가입에 실패했습니다.";

			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			
			return "common/msg";
		}
		
	}
	
	@RequestMapping("member/checkSellerDuplicate.do")
	public void sellerDuplicate(@RequestParam String sellerId,
								HttpServletResponse response) throws IOException {
		
		int check = sellerService.checkSellerDuplicate(sellerId);
		
		boolean result = (check == 0 ? true : false);
		response.getWriter().print(result);
	}
	
	@RequestMapping("member/sellerLogin.do")
	public String sellerLogin(@RequestParam String sellerId, @RequestParam String sellerPw, 
							Model model) {
		
		Seller seller = sellerService.selectOneSeller(sellerId);
		
		if(seller != null) {
			if(bcryptPasswordEncoder.matches(sellerPw, seller.getSellerPw())) {
				
				model.addAttribute("seller", seller);
				System.out.println("seller : " + seller);
				return "seller/sellerMain";

			} else {
				// 비밀번호 불일치 시 로직
				msg = "비밀번호가 일치하지 않습니다.";
				model.addAttribute("loc", loc);
				model.addAttribute("msg", msg);
				return "common/msg";
			}
		} else {
			msg = "존재하지 않는 아이디입니다.";
			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			return "common/msg";
		}		
	}
	
	@RequestMapping("seller/logout.do")
	public String sellerLogout(SessionStatus status) {
		
		if(!status.isComplete()) {
			status.setComplete();
			System.out.println("로그아웃 완료");
		}
				
		return "redirect:/";
	}
	
	@RequestMapping("/myPage/sellerInfo.do")
	public String sellerInfo(String sellerId, Model model) {
		
		Seller result = sellerService.selectOneSeller(sellerId);
		
		model.addAttribute("seller", result);
		
		return "myPage/sellerInfo";
	}
	
	@RequestMapping("/myPage/updateSeller.do")
	public String updateSeller(Seller seller, Model model) {
		
		int result = sellerService.updateSeller(seller);
		
		if(result > 0) {
			msg = "회원 정보 수정 완료!";
			model.addAttribute("seller", seller);
			System.out.println(seller);
		} else {
			msg = "회원 정보 수정 실패!";
		}
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "redirect:/";
	}
	
	@RequestMapping("/myPage/sellerDelete.do")
	public String sellerDelete(Seller seller, SessionStatus status, Model model) {
		
		int result = sellerService.deleteSeller(seller.getSellerId());
		
		if(result > 0) {
			msg = "회원 탈퇴 완료!";
			status.setComplete();
		} else {
			msg = "회원 탈퇴 실패!";
		}
		
		model.addAttribute("loc", loc);
		model.addAttribute("msg", msg);
		
		return "common/msg";
		
	}
	
}










=======
package com.kh.dpr.seller.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.dpr.seller.model.service.SellerService;
import com.kh.dpr.seller.model.vo.Seller;

@Controller
@SessionAttributes({"seller"})
public class SellerController {
	
	String loc = "/";
	String msg = "";
	
	@Autowired
	SellerService sellerService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@RequestMapping("member/memberSignUp.do")
	public String memberSignUp() {
		
		return "member/memberSignUp";
	}
	
	@RequestMapping("member/memberLoginView.do")
	public String memberLogin() {
		
		return "member/memberLogin";
	}
	
	@RequestMapping("seller/sellerMain.do")
	public String sellerMain() {
		
		return "seller/sellerMain";
	}
	
	@RequestMapping("member/sellerSignUpEnd.do")
	public String sellerSingUpEnd(Seller seller, Model model) {
		
		System.out.println("컨트롤러 도착 확인");
		System.out.println("Seller 정보 확인" + seller);
		
		String beforePw = seller.getSellerPw();
		String encryptPw = bcryptPasswordEncoder.encode(beforePw);
		seller.setSellerPw(encryptPw);
		
		int result = sellerService.insertSeller(seller);
		
		if(result > 0) {
			return "seller/sellerMain";
		} else {
			msg = "회원가입에 실패했습니다.";

			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			
			return "common/msg";
		}
		
	}
	
	@RequestMapping("member/checkSellerDuplicate.do")
	public void sellerDuplicate(@RequestParam String sellerId,
								HttpServletResponse response) throws IOException {
		
		int check = sellerService.checkSellerDuplicate(sellerId);
		
		boolean result = (check == 0 ? true : false);
		response.getWriter().print(result);
	}
	
	@RequestMapping("member/sellerLogin.do")
	public String sellerLogin(@RequestParam String sellerId, @RequestParam String sellerPw, 
							Model model) {
		
		Seller seller = sellerService.selectOneSeller(sellerId);
		
		if(seller != null) {
			if(bcryptPasswordEncoder.matches(sellerPw, seller.getSellerPw())) {
				
				model.addAttribute("seller", seller);
				System.out.println("seller : " + seller);
				return "seller/sellerMain";

			} else {
				// 비밀번호 불일치 시 로직
				msg = "비밀번호가 일치하지 않습니다.";
				model.addAttribute("loc", loc);
				model.addAttribute("msg", msg);
				return "common/msg";
			}
		} else {
			msg = "존재하지 않는 아이디입니다.";
			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			return "common/msg";
		}		
	}
	
	@RequestMapping("seller/logout.do")
	public String sellerLogout(SessionStatus status) {
		
		if(!status.isComplete()) {
			status.setComplete();
			System.out.println("로그아웃 완료");
		}
				
		return "redirect:/";
	}
}


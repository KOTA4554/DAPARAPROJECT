package com.kh.dpr.member.controller;

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

import com.kh.dpr.exception.MemberException;
import com.kh.dpr.member.model.service.MemberService;
import com.kh.dpr.member.model.vo.Member;
import com.kh.dpr.seller.model.vo.Seller;

@Controller
@SessionAttributes({"member"})
public class MemberController {
	
	String loc = "/";
	String msg = "";
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	@RequestMapping("/member/memberJoin.do")
	public String memberJoin() {
		
		System.out.println("회원가입 접근 확인");
		
		return "member/memberJoin";
	}
	
	@RequestMapping("/member/memberEnrollEnd.do")
	public String memberJoinEnd(Member m, Model model) {
		
		System.out.println("받아온 정보 확인 : " + m);
		
		String pass1 = m.getUserPw();
		String pass2 = bcryptPasswordEncoder.encode(pass1);
		
		System.out.println(pass1 + " / " + pass2);
		
		m.setUserPw(pass2);
		
		try {
			int result = memberService.insertMember(m);
						
			if(result > 0) {
				msg = "회원가입이 완료되었습니다.";
			} else {
				msg = "회원가입에 실패했습니다.";
			}
			
			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			
		}catch(Exception e) {
			
			throw new MemberException(e.getMessage());
		}
		
		return "common/msg";
	}
	
	@RequestMapping("member/checkIdDuplicate.do")
	public void IdDuplicate(@RequestParam String userId, HttpServletResponse response)
		throws IOException {
		
		int check = memberService.checkIdDuplicate(userId);
		
		boolean data = (check == 0 ? true : false);
		response.getWriter().print(data);
	}
	
	
	@RequestMapping("member/memberLogin.do")
	public String memberLogin(@RequestParam String userId, @RequestParam String userPw,
							  Model model) {
		
		Member member = memberService.selectOneMember(userId);
		
		if(member != null) {
			if(bcryptPasswordEncoder.matches(userPw, member.getUserPw())) {
				
				model.addAttribute("member", member);
				System.out.println("member : " + member);
				return "redirect:/";

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
	
	@RequestMapping("member/logout.do")
	public String sellerLogout(SessionStatus status) {
		
		if(!status.isComplete()) {
			status.setComplete();
			System.out.println("로그아웃 완료");
		}
				
		return "redirect:/";
	}
	
}


















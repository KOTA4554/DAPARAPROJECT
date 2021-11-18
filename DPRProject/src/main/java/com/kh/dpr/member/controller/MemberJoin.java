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

import com.kh.dpr.exception.MemberException;
import com.kh.dpr.member.model.service.MemberService;
import com.kh.dpr.member.model.vo.Member;

@Controller
@SessionAttributes({"member"})
public class MemberJoin {
	
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
			
			String loc = "/";
			String msg = "";
			
			if(result > 0) {
				msg = "회원 가입 성공!";
			} else {
				msg = "회원 가입 실패!";
			}
			
			model.addAttribute("loc", loc);
			model.addAttribute("msg", msg);
			
		}catch(Exception e) {
			throw new MemberException(e.getMessage());
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("member/checkIdDuplicate.do")
	public void IdDuplicate(@RequestParam String userId, HttpServletResponse response)
		throws IOException {
		
		int check = memberService.checkIdDuplicate(userId);
		
		boolean data = (check == 0 ? true : false);
		
		response.getWriter().print(data);
	}
}

















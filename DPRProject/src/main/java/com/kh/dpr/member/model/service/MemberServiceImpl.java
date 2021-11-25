
package com.kh.dpr.member.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kh.dpr.member.model.dao.MemberDAO;
import com.kh.dpr.member.model.vo.Member;

import sun.net.www.protocol.http.HttpURLConnection;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public int insertMember(Member member) {
		
		return memberDAO.insertMember(member);
	}

	@Override
	public Member selectOneMember(String userId) {
		return memberDAO.selectOneMember(userId);
	}

	@Override
	public int updateMember(Member member) {
		return memberDAO.updateMember(member);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDAO.deleteMember(userId);
	}

	@Override
	public int checkIdDuplicate(String userId) {
		return memberDAO.checkIdDuplicate(userId);
	}

	@Override
	public String getReturnAccessToken(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


















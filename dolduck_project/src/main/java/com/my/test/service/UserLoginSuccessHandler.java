package com.my.test.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.my.test.dto.MemberDto;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		System.out.println("UserLoginSuccessHandler: 2");
		System.out.println(authentication);
		
		MemberDto dto = (MemberDto)authentication.getPrincipal();
		System.out.println(dto);
		String msg = authentication.getName()+"님 환영합니다";
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("/");
		rd.forward(request, response);	
		
	}

}

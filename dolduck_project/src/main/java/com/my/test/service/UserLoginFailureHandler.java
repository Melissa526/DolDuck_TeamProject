package com.my.test.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("UserLoginFailureHandler : 3");
		
		request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지않습니다");
		request.getRequestDispatcher("/member/login.do").forward(request, response);	
		
	
	}

}
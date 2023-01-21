package com.example.demo.config.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("로그인 실패");

		int errorMsg = 0;
		
		if (exception instanceof AuthenticationServiceException) {
			System.out.println("존재하지 않는 사용자");
			request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
			errorMsg = 1;
		} else if (exception instanceof BadCredentialsException) {
			System.out.println("비번오류!"); // 동작함
			request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			errorMsg = 2;
		} else if (exception instanceof LockedException) {
			request.setAttribute("loginFailMsg", "잠긴 계정입니다..");

		} else if (exception instanceof DisabledException) {
			System.out.println("비활성화된 계정");
			request.setAttribute("loginFailMsg", "비활성화된 계정입니다..");
			errorMsg = 3;

		} else if (exception instanceof AccountExpiredException) {
			request.setAttribute("loginFailMsg", "만료된 계정입니다..");

		} else if (exception instanceof CredentialsExpiredException) {
			request.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}

		/*// 로그인 이슈 발생 시 에러메시지를 가지고 로그인 페이지로 포워딩됨
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
		dispatcher.forward(request, response);*/

		response.sendRedirect("/login?errorMsg=" + errorMsg);
		
	}

}
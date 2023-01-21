 package com.example.demo.config. security;

 import com.example.demo.service.UserService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
 import org.springframework.stereotype.Component;

 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import java.io.IOException;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private final UserService userService;

	// 로그인 성공시 이동 페이지(default Page)
	private String defaultUrl = "/notice-list";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		HttpSession session = request.getSession();
		session.setAttribute("memId", request.getParameter("memId"));

		response.setStatus(200);
		response.sendRedirect(defaultUrl);
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

}

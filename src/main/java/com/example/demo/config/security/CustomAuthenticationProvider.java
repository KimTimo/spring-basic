 package com.example.demo.config.security;

 import com.example.demo.dto.UserDto;
 import com.example.demo.entity.User;
 import com.example.demo.service.UserService;
 import lombok.RequiredArgsConstructor;
 import org.springframework.context.annotation.Bean;
 import org.springframework.security.authentication.AuthenticationProvider;
 import org.springframework.security.authentication.BadCredentialsException;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.AuthenticationException;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	private final PasswordEncoder passwordEncoder;

	private final UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String memId = (String) authentication.getPrincipal();
		String memPassword = (String) authentication.getCredentials();

		User user = (User) userService.loadUserByUsername(authentication.getName());

		// 로그인 시 입력한 패스워드 체크해주는 로직 필요
		System.out.println(memPassword);
		System.out.println(user.getPassword());
		if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getMemPassword())) {
			throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
		}

		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}


}

 package com.example.demo.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


 @Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final AuthenticationProvider provider;
	private final AuthenticationSuccessHandler sHandler;

	private final CustomAuthenticationFailHandler fHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()

				// 모든 권한 다풀음
			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//			.antMatchers("/**").permitAll()

				// 권한에 맞게 분류
//				.antMatchers("/admin-list", "/admin-management", "/modify-admin", "/register-admin").hasAuthority("ADMIN") // 관리자만 접근 가능한 페이지
				.antMatchers("/login/**").anonymous() // 누구나 접근 가능한 페이지

//				.anyRequest().authenticated()/*permitAll() */// 추후 path에 따라 접근 권한 나눠주세요.
//				.anyRequest().permitAll()
				.and()
	        .formLogin() 
		        .loginPage("/login")
                .usernameParameter("memId")
                .passwordParameter("memPassword")
		        .successHandler(sHandler).failureHandler(fHandler)
		.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll()
			.deleteCookies("JSESSIONID")
			.invalidateHttpSession(true)
		.and()
		.cors();

		http.sessionManagement()
				.maximumSessions(1)     //동시 생성가능 세션 수 -1은 무제한
//                동시 로그인 차단하게되면 로그아웃할때 세션 무효화지 삭제가 아니라
//                세션이 무효화 되었어도 사라지지 않아서 세션1개 있다고 로그인 안됨
//                false 로하면 로그인돼서 걍 false 로
				.maxSessionsPreventsLogin(false)     //동시 로그인 차단(true), false 가 디폴트+이전 세션 만료
				.expiredUrl("/login")             //세션이 만료된 경우 이동 할 페이지
				.and()                              //이걸 해야 invalid뜸
				.invalidSessionUrl("/login")      //세션이 유효하지 않을 때 이동 할 페이지 로그인했는데 또 로그인 시도하면 가게 되는 경로라고봄
//                changeSessionId는 3.1이상 버전 migrateSession은 3.1이하버전 디폴트
				.sessionFixation().changeSessionId()    //기본값 인증을 성공하게 되면 사용자 세션은 그대로 두고 세션 아이디만 변경
//                .sessionFixation().migrateSession()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);   //디폴트 스크링 시큐리티가 필요시 사용, Always 스프링 시큐리티가 항상


	}		
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.addAllowedOriginPattern("*");
	    configuration.addAllowedMethod("*");
	    configuration.addAllowedHeader("*");
	    configuration.setAllowCredentials(true);
	    configuration.setMaxAge(3600L);
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}

}
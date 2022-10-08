package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;
import com.cos.blog.config.oauth.Principaloauth2UserService;

// 빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration // 빈등록 (IoC)
@EnableWebSecurity // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Principaloauth2UserService principaloauth2UserService;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean //IoC가 됨. new BCryptPasswordEncoder()를 스프링이 관리함.
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인 해 줄 때 가로챈 password와 같은 해쉬로 암호화하여 비교하기 위해서 사용.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/test/**")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")  // 스프링 시큐리티가 로그인을 가로채서 대신 로그인.
				.defaultSuccessUrl("/")
				.and()
				.oauth2Login()
				.loginPage("/auth/loginForm") // 구글 로그인 화면 표시해줌. 구글 로그인이 완료된 뒤의 후처리가 필요함.
				// tip. 라이브러리를 사용하면 코드 안받고 엑세스토큰 + 사용자프로필정보를 한번에 받음
				.userInfoEndpoint()
				.userService(principaloauth2UserService);
	}

}

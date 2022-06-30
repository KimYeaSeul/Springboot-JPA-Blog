package com.cos.blog.Controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManger;
	
	@Autowired // DI
	private BCryptPasswordEncoder encoder;
	
//	이방식으로도 가능
//	@Autowired
//	private HttpSession session;
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, email, password
		System.out.println("UserApiController : save");
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	// JSON DATA 받으려면 @RequestBody, key=value (x-www-form-urlencoded) type 받고싶으면 어노테이션 생략
	@PutMapping("/user")
	public ResponseDto<Integer> userUpdate(@RequestBody User user, HttpSession session){ 
		 userService.userUpdate(user);	 
		// 세션 등록
		Authentication authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	//  스프링 시큐리티를 이용한 로그인
	
	
	// 전통적인 로그인 방식
	/*	
    @PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) { // username, email, password
		System.out.println("UserApiController : login");
		User principal = userService.login(user); // principal : 접근주체
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	*/
	
}

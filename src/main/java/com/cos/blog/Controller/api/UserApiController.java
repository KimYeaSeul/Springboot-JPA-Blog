package com.cos.blog.Controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
//	이방식으로도 가능
//	@Autowired
//	private HttpSession session;
	
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, email, password
		System.out.println("UserApiController : save");
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	// 스프링 시큐리티를 이용한 로그인
	
	
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

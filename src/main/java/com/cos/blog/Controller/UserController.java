package com.cos.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	//실제 DB에 insert
	@GetMapping("/user/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}

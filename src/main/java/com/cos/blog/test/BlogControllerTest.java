package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
// 스프링이 com.cos.blog  패키지 이하를 스캔해서 모든 파일을 메모리에 new하는 것은 아니구
// 특정 어노테이션이 붙어있는 클래스 파일들을  new 해서 (IoC) 스프링 컨테이너에 관리해줍니다.
@RestController
public class BlogControllerTest {

	// http://localhost:8000/test/hello
	@GetMapping("/test/hello")
	public String  hello() {
		return "<h1>Hello Spring Boot </h1>";
	}
=======
@RestController
public class BlogControllerTest {
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>Hello SpringBoot</h1>";
	}

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
}

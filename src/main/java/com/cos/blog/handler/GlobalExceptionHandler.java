package com.cos.blog.handler;

<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import com.cos.blog.dto.ResponseDto;

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
@ControllerAdvice // 모든 exception이 들어오면 여기로 옴
@RestController
public class GlobalExceptionHandler {

<<<<<<< HEAD
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
	}
}
=======
//	@ExceptionHandler(value=IllegalArgumentException.class)
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
//		return "<h1>" + e.getMessage() + "</h1>";
	}
}
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

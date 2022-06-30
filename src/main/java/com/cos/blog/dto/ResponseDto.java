package com.cos.blog.dto;

<<<<<<< HEAD
=======
import org.springframework.http.HttpStatus;

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
<<<<<<< HEAD
=======

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
	int status;
	T data;
}

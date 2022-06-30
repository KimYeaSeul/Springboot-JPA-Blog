package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//User Table을 관리하며 User Table의 Primary Key는 Integer이다.
//DAO (Data Access Object)
//Bean 등록 여부 = IoC에서 객체로 가지고 있나요 = DI 가능. ===> 자동으로 Bean 등록이 됨.
// 자동으로 Bean에 등록이 되기 때문에 @Repository annotation 생략 가능.
public interface UserRepository extends JpaRepository<User, Integer>{ 
	
}

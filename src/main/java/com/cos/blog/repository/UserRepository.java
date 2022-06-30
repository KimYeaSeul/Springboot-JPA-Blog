package com.cos.blog.repository;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//User Table을 관리하며 User Table의 Primary Key는 Integer이다.
//DAO (Data Access Object)
//Bean 등록 여부 = IoC에서 객체로 가지고 있나요 = DI 가능. ===> 자동으로 Bean 등록이 됨.
<<<<<<< HEAD
// 자동으로 Bean에 등록이 되기 때문에 @Repository annotation 생략 가능.
public interface UserRepository extends JpaRepository<User, Integer>{ 
	
}
=======
//자동으로 Bean에 등록이 되기 때문에 @Repository annotation 생략 가능.

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// SELECT * FROM user WHERE username = ?1;
	Optional<User> findByUsername(String username);
}
	// 로그인을 위한 함수
	// JPA Naming query 전략
	// Select * From user WHERE username = ?1 AND password = ?2;
//	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="Select * From user WHERE username = ?1 AND password = ?2", nativeQuery=true)
//	User login(String username, String password);

>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

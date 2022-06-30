package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어 포함) Object를 테이블로 매핑해주는 기술.

@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더패턴
@Data
@Entity //User 클래스가 MySQL에 테이블이 자동으 생성된다. 
public class User {
	
	@Id  //Primary key 설정  at
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. (오라클 - 시퀀스, MySQL - Autoincreament)
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
//	private String role; //Enum 을 쓰는게 좋다. Data의 도메인을 만들 수 있음.
//실수를 줄이기 위해 Type을 정해줌.
//DB는 RoleType이라는게 없어서 @Enumerated로 type이 String이라는 것을 알려주어야 한다.
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;


}

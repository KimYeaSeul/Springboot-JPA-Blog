package com.cos.blog.model;

import java.sql.Timestamp;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;

import com.cos.blog.dto.ReplySaveRequestdto;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더패턴
@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
<<<<<<< HEAD
	@JoinColumn(name = "boardId")
	private Board board; // 이 값을 mappedby에 적어준
	
	@ManyToOne
	@JoinColumn(name = "userId")
=======
	@JoinColumn(name="boardID")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userID")
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
<<<<<<< HEAD
=======
	
	public void update(User user, Board board, String content) {
		setUser(user);
		setBoard(board);
		setContent(content);		
	}
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
}

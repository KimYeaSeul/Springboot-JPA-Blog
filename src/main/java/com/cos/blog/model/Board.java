package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

<<<<<<< HEAD
=======
import javax.persistence.CascadeType;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
<<<<<<< HEAD
=======
import javax.persistence.OrderBy;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
@Entity
public class Board {
	
	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100 )
	private String title;
	
	@Lob // 대용량 데이터시 사용
	private String content; // 섬머노트 라이브러리 사용 => <html> 태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count; //  조회수
	
	@ManyToOne // many => board, one => user
	@JoinColumn(name = "userId")
	private User user; //DB는 object를 저장할 수 없다. FK사용, but Java는 object를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER) //mappedBy는 연관관계의 주인이 아니다.(FK가 아니다.) DB에 컬럼을 만들지 마세요. Board를 Select할 때 Join문을 통해서 Reply값을 얻기 위해서 필요한거에용.
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;

=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob
	private String Content;
	
	@Column
	private int count;

	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne //many = board, one => user
	@JoinColumn(name="userId")
	private User user;  //DB는 object를 저장할 수 없다. FK사용, but Java는 object를 저장할 수 있다.
	
	//mappedBy는 연관관계의 주인이 아니다.(FK가 아니다.) DB에 컬럼을 만들지 마세요. Board를 Select할 때 Join문을 통해서 Reply값을 얻기 위해서 필요한거에용.
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8
}

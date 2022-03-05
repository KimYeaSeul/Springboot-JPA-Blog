package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
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
	@OneToMany(mappedBy="board", fetch=FetchType.EAGER)
	private List<Reply> reply;
}

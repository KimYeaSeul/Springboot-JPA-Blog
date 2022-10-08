package com.cos.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRespository;

//무한참조 문제 발생
@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private ReplyRespository replyRespository;

	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		//jackson library 발동( object를 json 으로 리턴해줌 => 모델의 getter를 호출.)
		// reply에서 board를 return 해주는데, board는 reply를 return 해주어서 둘이 미친듯이 나옴!
		// 따라서 reply에서 board를 호출하지 말자!
		// @JsonIgnoreProperties({"board"}) 로 해결!
		// reply를 가지고 올때 board는 가져오지맛!
		return boardRepository.findById(id).get();
	}

	@GetMapping("/test/reply")
	public List<Reply> getReply() {
		return replyRespository.findAll();
	}
}

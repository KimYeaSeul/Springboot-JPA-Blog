package com.cos.blog.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.dto.ReplySaveRequestdto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRespository;
import com.cos.blog.repository.UserRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReplyRespository replyrRepository;
	
	@Transactional
	public void replyWrite( ReplySaveRequestdto replySaveRequestDto) { // title, content
		
		User requestUser = userRepository.findById(replySaveRequestDto.getBoardId())
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글쓰기 실패:게시글을 찾을수 없습니다.");
				}); // 영속화
		
		Board requestBoard = boardRepository.findById(replySaveRequestDto.getUserId())
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글쓰기 실패:유저 아이디를 찾을 수 없습니다.");
				}); // 영속화
		
		// 2번 방법
		Reply reply = Reply.builder()
				.user(requestUser)
				.board(requestBoard)
				.content(replySaveRequestDto.getContent())
				.build();
		
		// 3번 방법
//		Reply reply = new Reply();
//		reply.update(requestUser, requestBoard, replySaveRequestDto.getContent());
//		
		// 1번 방법
//		requestReply.setUser(user);
//		requestReply.setBoard(requestBoard);
//		
		replyrRepository.save(reply);
	}
	
	@Transactional
	public void write(Board board, User user) { // title, content
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Board boardDetail(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public void boardDelete(int id) {
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void boardUpdate(int id, Board requestBoard) {
		Board board = boardRepository.findById(id) // 영속화 시켜줌
		.orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 글을 찾을 수 없습니다.");
		});
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		// 해당 함수로 종료시(service가 종료될때) 트랜잭션이 종료됩니다. 이때 더티체킹이 일어나면서 자동으로 업데이트가 됨.(db flush)
	}
}
 
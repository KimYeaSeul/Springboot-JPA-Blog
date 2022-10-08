package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired // DI
	private BCryptPasswordEncoder encoder;

	@Transactional(readOnly = true)
	public User findUser(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

	@Transactional
	public void join(User user) {

		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		System.out.println(rawPassword +", "+ encPassword);
		userRepository.save(user);
//		try {
//			userRepository.save(user);
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("UserService : join() : " + e.getMessage());
//			// TODO: handle exception
//		}
//		return -1;
	}

	@Transactional
	public void userUpdate(User user) {
		// 수정시에는 영속성 컨텍스트에 User 객체를 영속화시키고, 영속화된 User 객체를 수정하는게 좋다.
		// 먼저 Select를 해서 User 객체 DB로 부터 가지고 오는 이유는 영속화를 하기 위해서.!
		// 영속화된 객체를 변경하면 자동으로 DB에 Update문을 날려줌.!
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});

		// validate check
		if(persistance.getProvider() == null || persistance.getProvider().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		// 회원수정 함수 종료시 = 서비스 종료시 = 트랜잭션이 종료 = commit이 자동으로 된다.
		// 영속화된 persistance 객체의 변화가 감지되어 더티체킹 하여 DB에 자동으로 update문을 날려줌.
//		userRepository.save(persistance);
	}

//	@Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}

package com.cos.blog.Controller;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//@DynamicInsert // Role은 초기값이 user인데 null 값이 들어가서 DB에 null로 들어감. 그래서 null값을 없애주는 어노테이션 추가.
@RestController // html이 아닌 데이터를 return 해주는 controller
public class DummyControllerTest {

	@Autowired  //의존성 주입. DummyControllerTest class가 뜰때 뜬다.
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당  id는 db에 없습니다.";
		}
		return "삭제가 완료되었습니다.";
	}
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//id를 전달하면 해당 id에 대한 data가 있으면 update를 해주고 
	//id를 전달하면 해당 id에 대한 data가 없으면 insert를 해준다. 
	// 주소가 같더라도 요청 메서드가 다르기 때문에 구분 가능.
	//RequestBody annotation(json 데이터 요청 => spring 이 java object(MessageConverter의 jackson library)로 변환해서 받아줌.)
	@Transactional // 해당 어노테이션은 save를 하지 않아도 update가 된다. 더티 체킹 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " + id); 
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		// null 값이 너무 많음.. 
//		requestUser.setId(id);
//		userRepository.save(requestUser);
		
		// 이렇게 합니다. user를 찾은 후..
		// 자바는 parameter로 함수를 넣을 수 없음. 람다식으로 가능.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			// AOP 예정.
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("수정에 실패하였습니다.");
			}
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
			
//		User user = userRepository.findById(id).orElseThrow(null);
		
		// 더티 체킹.
		return user;
	}
	
	//전체 불러오기, 여러건 => List type으로
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 페이징실습. 한페이지당 2건의 데이터를 return 받아 볼 예정.
	// http://localhost:8000/blog/dummy/user?page=0
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 parameter를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3 
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById는 Optional을 return 한다. 
		// user가 null일 경우, return null일 때 프로그램에 문제가 되기 때문에
		// Optional로 User객체를 감싸서 가져 온 후 null 인지 아닌지 판단해서 return
		// 0. get, null일 경우가 없을 경우에 사용.
		// 1. orElseGet, 값이 없으면 null을 return 해줌.
/*	
 * 	User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
*			@Override
*			public User get() {
*				return new User();
*			}
*		});
*/
		// 2. orElseThrow, 값이 없으면 에러 내용을 보여줌.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			// AOP 예정.
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다" + id);
			}
		});
		
		// 3. 람다식. supplier type 을 몰라도 사용 할 수 있다. 편하고 난이도 높은 방법.
/*
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegarArgumentException("해당 사용자는 없습니다.");
		});
*/
		// req : web browser (htm, javascript 만 이해 가능.)
		// user object = java object
		//web browser가 이해 할수 있는 데이터로 변환 필요 -> json (ex, Gson library)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약 java object를 return을 하게 되면 MessageConverter 가 Jackson library 호출해서
		// user object를 json으로 변환해서 브라우저에게 던져주게 된다.
		return user;
	}

	
	// 변수명을 제대로 적으면 RequestParam 어노테이션을 쓰지 않아도 된다.
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청) 
	// join 함수  parameter 에 들어옴!
	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) { // key=value (약속된 규칙)
	public String join(User user) {
		
		System.out.println("id : " + user.getId());
		System.out.println("username " + user.getUsername());
		System.out.println("email " + user.getEmail());
		System.out.println("password " + user.getPassword());
		System.out.println("role : "+user.getRole());
		System.out.println("createdate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다.";
	}
	
}
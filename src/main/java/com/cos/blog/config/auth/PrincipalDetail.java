package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고
// 완료가 되면 UserDetails type의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
//@SuppressWarnings("serial")
@Getter
public class PrincipalDetail implements UserDetails, OAuth2User{

	private User user; // composition 객체를 품고 있다. 상속과 다름
	private Map<String, Object> attributes;

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	@Override
	public String getName() {
		return null;
	}

	// 일반로그인
		public PrincipalDetail(User user) {
			this.user = user;
		}

	// 생성자 오버로딩, OAuth 로그인
	public PrincipalDetail(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

		// 계정이 만료되지 않았는지 리턴함.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않았는지 리턴함.(true:잠기지않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 리턴한다.(true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정이 활성화(사용가능)인지 리턴함.(true:활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 갖고 있는 권한 목록을 리턴함.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole();
//			}
//		});
		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		return collectors;
	}


}

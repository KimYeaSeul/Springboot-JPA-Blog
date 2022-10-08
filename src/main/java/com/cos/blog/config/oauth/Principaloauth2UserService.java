package com.cos.blog.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.provider.FacebookUserInfo;
import com.cos.blog.config.auth.provider.GoogleUserInfo;
import com.cos.blog.config.auth.provider.KakaoUserInfo;
import com.cos.blog.config.auth.provider.NaverUserInfo;
import com.cos.blog.config.auth.provider.OAuth2UserInfo;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class Principaloauth2UserService extends DefaultOAuth2UserService {

	// 순환참조에러 ㅠ
//	@Autowired // DI
//	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	// sns로 부터 받은 userReauest 데이터에 대한 후처리 되는 함수
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어 진다.

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("loadUser = " + userRequest.getClientRegistration());

OAuth2User oauth2User = super.loadUser(userRequest);

		// 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인완료 -> code return(OAuth-Client Library) -> AccessToken 요청
		// userRequest정보 -> loadUser함수호출 -> 구글로부터 회원 프로필을 받아줌.
		System.out.println(oauth2User.getAttributes());

		OAuth2UserInfo oAuth2UserInfo = null;
		Map<String, Object> getAttribute = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글로그인요청.");
			getAttribute = oauth2User.getAttributes();
			oAuth2UserInfo = new GoogleUserInfo(getAttribute);
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북요청.");
			getAttribute = oauth2User.getAttributes();
			oAuth2UserInfo = new FacebookUserInfo(getAttribute);
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버요청.");
			getAttribute = (Map)oauth2User.getAttributes().get("response");
			oAuth2UserInfo = new NaverUserInfo(getAttribute);
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오요청.");
			getAttribute = (Map)oauth2User.getAttributes();
			oAuth2UserInfo = new KakaoUserInfo(getAttribute);
		}else {
			System.out.println("구글, 페이스북, 네이버, 카카오만 지원함.");
		}
		// new PrincipalDetail(userEntity, oauth2User.getAttributes());
//		System.out.println("여기까지는 오시나요? = " + oAuth2UserInfo.getName());
		// getAttrubute 정보를 토대로 회원가입 진행.
//		String provider = userRequest.getClientRegistration().getRegistrationId(); //google, facebook
		String provider = oAuth2UserInfo.getProvider(); //google, facebook
		System.out.println("provider ? = " + provider);
//		String providerId = oauth2User.getAttribute("sub");
		String providerId = oAuth2UserInfo.getProviderId();
		System.out.println("providerId ? = " + providerId);
		String username = provider+"_"+providerId;
		System.out.println("username ? = " + username);
		String password =  new BCryptPasswordEncoder().encode("시크릿키");
		System.out.println("password ? = " + password);
//		String email = oauth2User.getAttribute("email");
		String email = oAuth2UserInfo.getEmail();
		System.out.println("email ? = " + email);


		User userEntity = userRepository.findByUsername(username).orElseGet(()-> null );

		if(userEntity == null) {
			System.out.println("최초 OAuth 로그인 회원가입 진행");
			userEntity = User.builder().username(username)
					.password(password)
					.email(email)
					.role(RoleType.USER)
					.provider(provider)
					.providerId(providerId)
					.build();
			userRepository.save(userEntity);
		} else {
			System.out.println("로그인을 이미 한 적이 있습니다.");
		}

		System.out.println("userEntity = " + userEntity);
		System.out.println("userEntity = " + oauth2User.getAttributes());
		// OAuthentication Object 안에 세션정보로 들어감.
		return new PrincipalDetail(userEntity, getAttribute);
	}
}

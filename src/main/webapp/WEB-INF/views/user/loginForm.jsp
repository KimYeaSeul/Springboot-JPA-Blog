<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

<<<<<<< HEAD
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">User name :</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
		
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
	
</div>

<%@include file="../layout/footer.jsp"%>
=======
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">User name :</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
		</div>
		
		<button id="btn-login" class="btn btn-secondary">Login</button>
		<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=7e25a8cecfdcd4aa062c09f547f99a1a&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
		<img height="40px" src="/image/kakao_login_button.png"></a> -->
		 <!-- <a href="/oauth2/authorization/google">구글로그인</a> 주소는 고정입니다요 -->
		 <hr />
		<ul class="sns-login">
			<li>소셜로그인</li>
			<li><a href="/oauth2/authorization/google">
			<img height="40px" src="/image/google_login_btn.png"></a></li>
			<li><a href="/oauth2/authorization/facebook">
			<img height="40px" src="/image/facebook_login_btn.png"></a> </li>
			<li><a href="/oauth2/authorization/kakao">
			<img height="40px" src="/image/kakao_login_button.png"></a></li>
			<li><a href="/oauth2/authorization/naver"> 
			<img height="40px" src="/image/naver_login_button.png"></a></li>
		</ul>
	</form>	
</div>

<%@include file="../layout/footer.jsp"%> 
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">
	<input type="hidden" id="id" value="${principal.user.id }">
	<form action="/user/join" method="POST">
		<div class="form-group">
			<label for="username">User name :</label> 
			<input type="text" value="${principal.user.username}" class="form-control" id="username" readonly>
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password" value="${principal.user.password }">
		</div>	
		
		<div class="form-group">
			<label for="email">Email :</label> 
			<input type="email" value="${principal.user.email}" class="form-control" id="email">
		</div>
		
			
	</form>
		<button id="btn-update" class="btn btn-primary">회원수정완료</button>
		
</div>

<script src="/js/user.js"></script>

<%@include file="../layout/footer.jsp"%>
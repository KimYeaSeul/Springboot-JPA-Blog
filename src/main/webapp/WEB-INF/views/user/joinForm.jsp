<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../layout/header.jsp"%>

<div class="container">

	<form action="/user/join" method="POST">
		<div class="form-group">
			<label for="username">User name :</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>	
		
		<div class="form-group">
			<label for="email">Email :</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		
			
	</form>
		<button id="btn-save" class="btn btn-secondary">Join</button>
		
</div>

<<<<<<< HEAD
<script src="/blog/js/user.js"></script>

<%@include file="../layout/footer.jsp"%>
=======
<script src="/js/user.js"></script>

<%@include file="../layout/footer.jsp"%>
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<sec:authorize access="isAuthenticated()"> 
	<sec:authentication property="principal" var="principal"/>
</sec:authorize> 
<!DOCTYPE html>
<html lang="en">
<head>
<title>일단_블로그_시작</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
</head>

<body>
<header>
<nav class="navbar navbar-expand-md  navbar-dark " id="navbar">
  <a class="navbar-brand"  href="/" id="blog-title">일단_블로그_시작</a>
  <div class="" id="collapsibleNavbar nav-right">
  
  <c:choose>
  <c:when test="${empty principal}">
    <ul class="" id="navbar-menu">
      <li class="nav-item"><a class="link" href="/auth/loginForm">로그인</a></li>
      <li class="nav-item"><a class="link" href="/auth/joinForm">회원가입</a></li>    
    </ul>
    </c:when>
    <c:otherwise>
	<ul class="" id="navbar-menu">
	  <li class="nav-item"><a class="link" href="/board/saveForm">글쓰기</a></li>
	  <li class="nav-item"><a class="link" href="/user/updateForm">회원정보</a></li>    
	  <li class="nav-item"><a class="link" href="/logout">로그아웃</a></li>    
	</ul>
    </c:otherwise>
    </c:choose>
  </div>  
</nav>
</header>
<hr />
<br />
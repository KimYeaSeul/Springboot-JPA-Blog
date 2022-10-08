<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
	<button class="btn btn-secondary" onclick="history.back()" style="margin-bottom : 10px;">돌아가기</button>
	
	<div class="form-group" id="title">
		<h3>${board.title}</h3>
	</div>
	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id}/updateForm">수정</a>
		<a onClick="index.deleteById(${board.id}); return false;"  id="btn-delete"> 삭제 </a>
		<%-- <a onClick="deleteEntry(${board.id}); return false;" id="btn-delete">삭제</a> --%>
		<!-- <button class="btn btn-danger"  id="btn-delete">삭제</button> -->
	</c:if>
	<br/>
	</div>
	<hr/>
	<div class="form-group entry-content" id="content">
		<div>
			${board.content}
		</div>
	</div>
	<hr />
	
	<div class="reply-card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}">
			<input type="hidden" id="boardId" value="${board.id}">
			<div class="card-body"><textarea id="reply-content" class="form-control" rows="1"></textarea></div>
			<div class="card-footer"><button type="button" id="btn-reply-save" class="btn btn-primary">등록</button></div>
		</form>
	</div>
	<br>
	<div class="reply-card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">
		<c:forEach var="reply" items="${board.replys }">
		  <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
		  	<div>${reply.content }</div>
		  	<div class="d-flex">
		  		<div class="font-italic">작성자 : ${reply.user.email } &nbsp;</div>
		  		<button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge">삭제</button>
		  	</div>
		  </li>
		  </c:forEach>
		</ul>
	</div>
</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>


<div class="container">

<<<<<<< HEAD
	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">Title</h4>
			<p class="card-text">Some example text some example text. Jane
				Doe is an architect and engineer</p>
			<a href="#" class="btn btn-primary">See Profile</a>
		</div>
	</div>

	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">Title</h4>
			<p class="card-text">Some example text some example text. Jane
				Doe is an architect and engineer</p>
			<a href="#" class="btn btn-primary">See Profile</a>
		</div>
	</div>

	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">Title</h4>
			<p class="card-text">Some example text some example text. Jane
				Doe is an architect and engineer</p>
			<a href="#" class="btn btn-primary">See Profile</a>
		</div>
	</div>

</div>

<%@ include file="layout/footer.jsp"%>

=======
<c:forEach var="board" items="${boards.content}">
	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">${board.title}</h4>
			<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
		</div>
	</div>
</c:forEach>
<ul class="pagination justify-content-center">
	<c:choose>
		<c:when test="${boards.first}">
			<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
		</c:otherwise>
	</c:choose>	
	<c:choose>
		<c:when test="${boards.last}">
			<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
		</c:when>
		<c:otherwise>
  			<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
		</c:otherwise>
	</c:choose>	  
</ul>
</div>

<%@ include file="layout/footer.jsp"%>
>>>>>>> f8a7dd090047f64c3a67cbbccb00149c5d166cc8

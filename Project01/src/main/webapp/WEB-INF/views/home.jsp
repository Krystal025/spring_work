<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery 로딩 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- 플러그인 CSS 로딩 -->
<link  href="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">
<!-- 플러그인 JS로딩 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script>
</head>
<body>
<style>
.main{
	width: 100%;
	margin: auto
}
.main h2, .main p{
	text-align: center;
	
}
</style>
	<!-- navbar -->
	<jsp:include page="/WEB-INF/views/include/navbar.jsp">
		<jsp:param value="home" name="current"/>
	</jsp:include>
	
	<!-- Carousel 영역 -->
	<div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${pageContext.request.contextPath }/resources/images/bg_01.jpg" 
					class="d-block w-100" alt="bgImg_1" />
				<div class="carousel-caption text-start">
        			<h1>First slide label</h1>
        			<p>Some representative placeholder content for the first slide.</p>
        			<a class="btn btn-primary" href="${pageContext.request.contextPath }/users/loginform">LogIn</a>
         			<!--
         				JS로 처리하는 방법 
         				<button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath }/users/signup_form'">Sign Up</button>
         			  -->
      			</div>
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath }/resources/images/bg_02.jpg" 
					class="d-block w-100" alt="bgImg_2" />
				<div class="carousel-caption">
        			<h3>First slide label</h3>
        			<p>Some representative placeholder content for the first slide.</p>
        			<p><a class="btn btn-lg btn-primary" href="#">Sign up today</a></p>
      			</div>
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath }/resources/images/bg_05.jpg" 
					class="d-block w-100" alt="bgImg_5" />
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="prev">
    		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    		<span class="visually-hidden">Previous</span>
  		</button>
  		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying" data-bs-slide="next">
    		<span class="carousel-control-next-icon" aria-hidden="true"></span>
    		<span class="visually-hidden">Next</span>
  		</button>
	</div>
	
	<!-- Carousel영역 아래 링크 3가지 -->
    <div class="container marketing mt-5">
	    <div class="row main">
		      <div class="col-lg-4 mb-5">
		        <h2 class="fw-normal">Board</h2>
		        <p>Another exciting bit of representative placeholder content. This time, we've moved on to the second column.</p>
		        <p><a class="btn btn-outline-dark" href="${pageContext.request.contextPath }/gallery/list">자유게시판 &raquo;</a></p>
		      </div>
		      <div class="col-lg-4 mb-5">
		        <h2 class="fw-normal">Guest</h2>
		        <p>And lastly this, the third column of representative placeholder content.</p>
		        <p><a class="btn btn-outline-dark" href="${pageContext.request.contextPath }/guest/list">XXX &raquo;</a></p>
			</div>
			<div class="col-lg-4 mb-5">
		        <h2 class="fw-normal">Q&A</h2>
		        <p>Some representative placeholder content for the three columns of text below the carousel. This is the first column.</p>
		        <p><a class="btn btn-outline-dark" href="${pageContext.request.contextPath }/cafe/list">질문게시판 &raquo;</a></p>
		      </div>
	    </div>
	</div>
	
	<!-- header영역 & 로그인 창 -->
	<div class="row container mt-3">
		<div class="col-3">
			<c:choose>
				<c:when test="${empty sessionScope.id }">
					<a href="${pageContext.request.contextPath }/users/loginform">로그인</a>
					<a href="${pageContext.request.contextPath }/users/signup_form">회원가입</a>				
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/users/info">${id }</a>님 로그인 중...
					<a href="${pageContext.request.contextPath }/users/logout">로그아웃</a>				
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
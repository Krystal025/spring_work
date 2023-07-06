<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 어두운 계열의 navbar 라면 data-bs-theme="dark"로 지정 
		navbar-expand-md : md영역에서 navbar-collapse가 펼쳐지도록 함 -->
<nav class="navbar navbar-expand-md bg-body-tertiary" style="background-color: #030000"
	data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
			 Main Page
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarText">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto">
				<li class="nav-item">
					<a class="nav-link ${param.current eq 'cafe' ? 'active' : '' }" href="${pageContext.request.contextPath}/cafe/list">게시판</a>
				</li>
				<li class="nav-item">
					<a class="nav-link ${param.current eq 'gallery' ? 'active' : '' }" href="${pageContext.request.contextPath}/gallery/list">갤러리</a>
				</li>	
				<li class="nav-item">
					<a class="nav-link ${param.current eq 'guest' ? 'active' : '' }" href="${pageContext.request.contextPath}/guest/list">Q&A</a>	
				</li>	

					
			</ul>
			<div class="navbar-nav">
			<c:choose>
				<c:when test="${not empty id }">
					<a class="nav-link" href="${pageContext.request.contextPath}/users/private/info"><strong>${id }</strong></a>
					<a class="nav-link" href="${pageContext.request.contextPath}/users/logout">로그아웃</a>
				</c:when>
				<c:otherwise>
					<a class="nav-link ${param.current eq 'login' ? 'active' : '' }" href="${pageContext.request.contextPath}/users/loginform">로그인</a>
					<a class="nav-link ${param.current eq 'signup' ? 'active' : '' }" href="${pageContext.request.contextPath}/users/signup_form">회원가입</a>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</nav>
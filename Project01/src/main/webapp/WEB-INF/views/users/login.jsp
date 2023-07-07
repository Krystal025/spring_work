<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/login.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
 	<div class="container">
		<h1>알림</h1>
		<c:choose>
			<c:when test="${not empty sessionScope.id }">
				<div class="alert alert-success d-flex align-items-center" role="alert">
  					<strong>${sessionScope.id }</strong>님이 로그인 되었습니다.
  					<a href="${requestScope.url }" class="alert-link">확인</a>
  				</div>
			</c:when>
			<c:when test="${empty param.id}">
				<div class="alert alert-warning d-flex align-items-center" role="alert">
  					아이디를 입력해주세요!
  					<a href="loginform?url=${requestScope.encodedUrl }" class="alert-link">다시 시도</a>
  				</div>
			</c:when> 
			<c:when test="${empty param.pwd}">
				<div class="alert alert-warning d-flex align-items-center" role="alert">
  					비밀번호를 입력해주세요! 
  					<a href="loginform?url=${requestScope.encodedUrl }" class="alert-link">다시 시도</a>
  				</div>
			</c:when>
			<c:otherwise>
				<div class="alert alert-danger" role="alert">
  					아이디 혹은 비밀번호가 틀렸습니다!
  					<a href="loginform?url=${requestScope.encodedUrl }" class="alert-link">다시 시도</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
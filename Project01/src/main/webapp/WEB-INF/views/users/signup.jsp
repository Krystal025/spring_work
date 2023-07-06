<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signup.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		
		<p> <!-- form 전송 되었던 파라미터를 view page 에서 당연히 사용할수 있음 -->
			<strong>${param.id }</strong>님이 가입되었습니다.
			<a href="${pageContext.request.contextPath}/users/loginform">로그인 하러가기</a>
		</p>
		
	</div>	
</body>
</html>
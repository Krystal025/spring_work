<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-3">
		<c:if test="${not empty sessionScope.id }">
			<p>
				<storng>${id }</storng>님 로그인중...
			</p>
		</c:if>
		<h1>인덱스 페이지</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath }/play">놀러가기</a></li>
			<li><a href="${pageContext.request.contextPath }/users/loginform">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/users/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/users/info">개인정보(로그인필요)</a></li>
			<li><a href="${pageContext.request.contextPath }/file/insertform">파일 업로드 테스트</a></li>
		</ul>
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items="${requestScope.noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
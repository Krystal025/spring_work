<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.id }">
				<a href="${pageContext.request.contextPath }/users/loginform">로그인</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath }/users/info">${id }</a>
				님 로그인중...
				<a href="${pageContext.request.contextPath }/users/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
		<h1>인덱스 페이지</h1>
		<%--Spring Boot에서 추가된 static 폴더에 있는 자원 사용하기 --%>
		<img src="${pageContext.request.contextPath }/images/bottle.png" />
		<img src="${pageContext.request.contextPath }/images/kim1.png" />
		<%--컨트롤러에 이미지 데이터 요청하기 --%>
		<img src="${pageContext.request.contextPath }/aaa/kim1.png" />
		<img src="${pageContext.request.contextPath }/aaa/kim2.png" />
		<ul>
			<li><a href="${pageContext.request.contextPath }/html/hello.html">Html페이지</a></li>
			<%-- static 폴더에는 jsp페이지를 위치시킬 수 없음 --%>
			<li><a href="${pageContext.request.contextPath }/jsp/hello.jsp">JSP페이지(불가)</a></li>
			<li><a href="${pageContext.request.contextPath }/test.jsp">JSP페이지(가능)</a></li>
			<li><a href="cafe/insertform">카페 글쓰기</a></li>
			<li><a href="file/uploadform">파일 업로드</a></li>
		</ul>
		<h3>Lombok 테스트</h3>
		<form action="member/insert" method="post">
			<input type="text" name="num" placeholder="Number" />
			<input type="text" name="name" placeholder="Name" />
			<input type="text" name="addr" placeholder="Address" />
			<button type="submit">Add</button>
		</form>
		<h3>공지사항</h3>
		<ul>
			<c:forEach var="tmp" items="${noticeList }">
			 	<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
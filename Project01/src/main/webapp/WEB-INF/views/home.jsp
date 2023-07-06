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
	<!-- navbar -->
	<jsp:include page="/WEB-INF/views/include/navbar.jsp">
		<jsp:param value="home" name="current"/>
	</jsp:include>
	<!-- header영역 & 로그인 창 -->
	<div class="row container mt-3">
		<div class="col-9 fotorama">
			<img src="${pageContext.request.contextPath }/resources/images/bg_01.jpg" alt="bgImg_1" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_02.jpg" alt="bgImg_2" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_03.jpg" alt="bgImg_3" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_04.jpg" alt="bgImg_4" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_05.jpg" alt="bgImg_5" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_06.jpg" alt="bgImg_6" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_07.jpg" alt="bgImg_7" />
			<img src="${pageContext.request.contextPath }/resources/images/bg_08.jpg" alt="bgImg_8" />
		</div>
		<div class="col-3">
			<c:choose>
				<c:when test="${empty sessionScope.id }">
					<form action="${pageContext.request.contextPath}/users/login" method="post">
						<c:choose>
							<c:when test="${ empty param.url }">
								<input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="url" value="${param.url }"/>
							</c:otherwise>
						</c:choose>
						<div>
							<label class="control-label" for="id">아이디</label>
							<input class="form-control" type="text" name="id" id="id"/>
						</div>
						<div>
							<label class="control-label" for="pwd">비밀번호</label>
							<input class="form-control" type="password" name="pwd" id="pwd"/>
						</div>
						<button class="btn btn-primary" type="submit">로그인</button>
						<button class="btn btn-primary" type="button" onclick="location.href='${pageContext.request.contextPath }/users/signup_form'">회원가입</button>
					</form>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/users/info">${id }</a>님 로그인 중...
					<a href="${pageContext.request.contextPath }/users/logout">로그아웃</a>				
				</c:otherwise>
			</c:choose>
		</div>
			
		<h1>인덱스 페이지</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath }/cafe/list">게시판 (회원전용)</a></li>
			<li><a href="${pageContext.request.contextPath }/gallery/list">갤러리</a></li>
			<li><a href="${pageContext.request.contextPath }/guest/list">Q&A</a></li>
		</ul>

	</div>
</body>
</html>
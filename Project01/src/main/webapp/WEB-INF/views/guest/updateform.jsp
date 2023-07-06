<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/guest/updateform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<h1>작성글 수정</h1>
		<form action="${pageContext.request.contextPath }/guest/update" method="post">
			<div class="mb-2">
				<label class="form-label" for="num">번호</label>
				<input class="form-control" type="text" name="num" value="${requestScope.dto.num}" readonly/>
			</div>	
			<div class="mb-2">
				<label class="form-label" for="regdate">등록일</label>
				<input class="form-control" type="text" name="regdate" id="regdate" value="${dto.regdate}" readonly />
			</div>	
			<div class="mb-2">
				<label class="form-label" for="writer">작성자</label>
				<input class="form-control" type="text" name="writer" id="writer" value="${dto.writer}" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="content">내용</label>
				<textarea class="form-control" name="content" id="content">${dto.content}</textarea>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd" placeholder="Password"/>
			</div>
			<button class="btn btn-primary" type="submit">수정</button>
			<button class="btn btn-warning" type="reset">취소</button>
		</form>
	</div>
</body>
</html>
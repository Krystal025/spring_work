<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/insertform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<h1>회원추가 폼</h1>
		<form action="${pageContext.request.contextPath }/member/insert" method="post">
			<div>
				<label for="name">이름</label>
				<input type="text" name="name" id="name" />
			</div>
			<div>
				<label for="addr">주소</label>
				<input type="text" name="addr" id="addr" />
			</div>
			<button type="submit">추가</button>
		</form>
	</div>
</body>
</html>
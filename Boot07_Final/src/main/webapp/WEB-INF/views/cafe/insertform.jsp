<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/insertform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<style>

</style>
</head>
<body>
	<div class="container mt-5">
		<h1>Q&A</h1>
		<form action="${pageContext.request.contextPath }/cafe/insert" method="post">
			<div class="mb-2">
				<label class="form-label" for="title">제목</label>
				<input class="form-control" type="text" name="title" id="title" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="writer">작성자</label>
				<input class="form-control" type="text" name="writer" id="writer" />
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd" />
			</div>
			<div class="mb-5">
				<label class="form-label" for="content">내용</label>
				<textarea class="form-control" name="content" id="content" cols="30" rows="10" placeholder="내용을 작성하세요"></textarea>
			</div>
			<button class="btn btn-success" type="submit">등록</button>
			<button class="btn btn-danger" type="reset">취소</button>
		</form>
	</div>	
</body>
</html>

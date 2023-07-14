<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/upload.jsp</title>
</head>
<body>
	<div class="container">
		<p>원본 파일명 : <strong>${orgFileName }</strong></p>
		<p>저장된 파일명 : <strong>${saveFileName }</strong></p>
		<p>파일 크기 : <strong>${fileSize }</strong>bytes</p>
		<a href="download?orgFileName=${orgFileName }&saveFileName=${saveFileName }&fileSize=${fileSize}">임시 다운로드</a>
	</div>
</body>
</html>
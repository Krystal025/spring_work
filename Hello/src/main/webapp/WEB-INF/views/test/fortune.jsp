<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/fortune.jsp</title>
</head>
<body>
	<div class="container">
		<h1>오늘의 운세 페이지</h1>
		<p>
			오늘의 운세 : <strong>${fortuneToday }</strong>
			<a href="/hello/">인덱스 페이지</a>
		</p>
	</div>
</body>
</html>
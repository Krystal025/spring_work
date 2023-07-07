<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/loginform.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	body {
  		font-family: "Open Sans", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Helvetica, Arial, sans-serif; 
	}
</style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
	<div class="container">
		<main class="form-signin w-100 m-auto">
			<form action="${pageContext.request.contextPath}/users/login" method="post">
			    <c:choose>
					<c:when test="${ empty param.url }">
						<input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="url" value="${param.url }"/>
					</c:otherwise>
				</c:choose>
			    <h1 class="mb-3 fw-normal">Acorn</h1>
			    <div class="form-floating">
			      <input type="text" class="form-control" name="id" id="id">
			      <label for="id">ID</label>
			    </div>
			    <div class="form-floating">
			      <input type="password" class="form-control" name="pwd" id="pwd" >
			      <label for="pwd">Password</label>
			    </div>
			    <div class="form-check text-start my-3">
			      <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
			      <label class="form-check-label" for="flexCheckDefault">
			        Remember me
			      </label>
			    </div>
			    <button class="btn btn-primary w-100 py-2" type="submit">Log In</button>
			    <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
			  </form>
		  </main>
	</div>
</body>
</html>
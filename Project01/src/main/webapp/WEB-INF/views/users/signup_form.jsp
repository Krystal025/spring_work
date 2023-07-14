<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/signup_form.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
	<div class="container" id="app">	
		<h1 class="mb-3 fw-normal">Sign Up</h1>
		<form action="${pageContext.request.contextPath}/users/signup" method="post" id="myForm">
			<c:choose>
					<c:when test="${ empty param.url }">
						<input type="hidden" name="url" value="${pageContext.request.contextPath}/"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="url" value="${param.url }"/>
					</c:otherwise>
			</c:choose>
			<div class="form-group">
				<label class="form-label mt-4" for="id">ID</label>
				<input class="form-control" type="text" name="id" id="id"/>		
			</div>
			<div class="form-group">
				<label class="form-label mt-4" for="pwd">Password</label>
				<input @input="onPwdInput" v-model="pwd" 
					v-bind:class="{'is-valid':isPwdValid, 'is-invalid':!isPwdValid && isPwdDirty}"
					class="form-control" type="password" name="pwd" id="pwd"/>	
				<div class="invalid-feedback">특수문자를 한 개이상 포함하세요</div>
			</div>
			<div class="form-group">
				<label class="form-label mt-4" for="pwd2">Confirm Password</label>
				<input @input="onPwdInput2" v-model="pwd2"
					v-bind:class="{'is-valid':isPwdValid2, 'is-invalid':!isPwdValid2 && isPwdDirty2}" 
					class="form-control" type="password" name="pwd2" id="pwd2"/>					
				<div class="valid-feedback"></div>
				<div class="invalid-feedback">비밀번호가 일치하지 않습니다</div>
			</div>
			<div class="form-label mt-4">
				<label for="email">E-mail</label>
				<input @input="onEmailInput"
					v-bind:class="{'is-valid':isEmailValid, 'is-invalid':!isEmailValid && isEmailDirty}"	 
				class="form-control" type="text" name="email" id="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요</div>
			</div>
			<button class="btn btn-primary  my-3" type="submit">Sign Up</button>
		</form>
	</div>	
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
      new Vue({
         el:"#app",
         data:{
            isEmailValid:false,
            isEmailDirty:false,
            isPwdValid:false,
            isPwdDirty:false,
            isPwdValid2:false,
            isPwdDirty2:false,
            pwd:"",
            pwd2:""
         },
         methods:{
            onEmailInput(e){
               this.isEmailDirty=true;
               const inputEmail=e.target.value;
               const reg=/@/;
               this.isEmailValid=reg.test(inputEmail);
            },
            onPwdInput(){
               this.isPwdDirty=true;
               //model이 있기 때문에 입력값을 읽어올 필요없음 (자동으로 들어감)
               //특수문자가 포함되었는지 여부를 검증할 정규 표현식
               const reg=/[\W]/;
               //정규표현식 통과 + pwd와 pwd2의 값이 같아야 통과 
               this.isPwdValid = reg.test(this.pwd);
            },
            onPwdInput2(){
            	this.isPwdDirty2=true;
            	if(this.pwd == this.pwd2){
            		this.isPwdValid2 = true;
            	}
            }
         }
      });
   </script>
</body>
</html>
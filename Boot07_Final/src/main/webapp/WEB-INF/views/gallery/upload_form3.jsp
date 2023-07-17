<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/upload_form3.jsp</title>
<style>
#dropZone {
	width: 400px;
	height: 400px;
	outline: 2px dashed #92b0b3;
	border-radius: 20px;
	outline-offset: -10px;
	text-align: center;
	background-color: #ffc0cb;
	/*자식 contents를 상하 좌우 가운데 정렬하기 위한 CSS*/
	display: flex;
	justify-content: center;
	align-items: center;
}
/*img요소에 적용할 CSS*/
#preview{
	display: none;
	object-fit: cover;
	width: 100%;
	height: 100%;
	border-radius: 20px;
}
#image{
display: none;
}
</style>
</head>
<body>
	<div class="container">
		<h1>이미지 업로드 폼3</h1>
			<div>
				<label for="caption">설명</label> 
				<input type="text" name="caption" id="caption" />
			</div>
			<input type="file" name="image" id="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG" />
		<br />
		<a href="javascript:" id="dropZoneLink" title="업로드할 이미지 선택">
			<div id="dropZone">
				<p>Drop or Click</p>
				<img src="" id="preview"/>
			</div>
		</a>
		<br />
		<button id="submitBtn">업로드</button>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.0/dist/jquery.min.js"></script>
	<script>
		//업로드 버튼을 눌렀을 떄 
		document.querySelector("#submitBtn").addEventListener("click",()=>{
			//입력한 caption을 읽어옴 
			const caption = document.querySelector("#caption").value;
			const files = document.querySelector("#image").files;
			//만일 caption을 3글자 미만으로 입력하거나 선택한 파일이 없다면 
			if(caption.length < 3 || files.length == 0){
				alert("caption을 3글자 이상 입력하고 업로드할 이미지를 선택해주세요")
				return;  //함수 종료 
			}
			
			//ajax 전송할 데이터를 FormData()객체에 담음
			const data = new FormData();
			//caption이라는 파라미터명으로 input요소에 입력한 문자열을 담음 
			data.append("caption", document.querySelector("#caption").value);
			//input요소에 선택된 파일 데이터를 얻어옴 
			const file = document.querySelector("#image").files[0];
			//image라는 파라미터명으로 현재 선택한 파일 객체를 담음 
			data.append("image", file);
			/*
				fetch()함수를 호출하면서  method:"post", body:FormData객체를 전달하면
				<form method="post" enctype="mulipart/form-data"> 폼 전송과 같다 
			*/
			fetch("${pageContext.request.contextPath }/gallery/ajax_upload",{
				method: "post",
				body: data
			})
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				if(data.isSuccess){
					alert(file.name+" 이미지를 성공적으로 업로드 했습니다");
					//UI를 초기상태로 되돌림 
					document.querySelector("#preview").style.display="none";
					document.querySelector("#dropZone p").style.display="block";
					//input type="file"을 초기화하는 방법  (빈 value를 부여)
					document.querySelector("#image").value="";
					document.querySelector("#caption").value="";
				}
			})
		})
	
		//dropZone을 클릭해도 파일 선택창을 띄우도록 함 
		document.querySelector("#dropZoneLink").addEventListener("click",()=>{
			//input type="file"을 강제 클릭시킴 
			document.querySelector("#image").click();
		})
		//dropZone div의 참조값 얻어오기
		const dropZone = document.querySelector("#dropZone");
		//이벤트 리스너 함수 등록
		dropZone.addEventListener("dragover", (e)=>{
			e.preventDefault();
		});
		dropZone.addEventListener("drop", (e)=>{
			e.preventDefault();
			//drop된 파일의 정보를 얻어오기
			const files = e.dataTransfer.files;
			console.log(files[0]);
			//drop된 파일의 정보를 조사해서 이미지파일이 아니라면 함수 종료 
			const type = files[0].type;
			console.log(type);
			if(type.indexOf("image")!=0){
				alert("이미지 파일이 아닙니다!");
				return;
			}
			
			if(files.length > 0){
				//파일로부터 데이터를 읽어들일 객체 생성
				const reader = new FileReader();
				//로딩이 완료(파일데이터를 모두 읽었을 떄)되었을 떄 실행할 함수 등록
				reader.onload = (event)=>{
					//읽은 파일데이터 얻어내기 
					const data = event.target.result;
					//console.log(data);
					//이미지 요소의 data를 src속성의 value로 넣으시오
					document.querySelector("#preview").setAttribute("src", data);
					document.querySelector("#preview").style.display="block";
					document.querySelector("#dropZone p").style.display="none";
				};
				//파일을 DataURL형식의 문자열로 읽어들이기 
				reader.readAsDataURL(files[0]);
				//선택된 파일의 정보를  input type="file"요소에 넣어줌
				document.querySelector("#image").files = files;
			}
		});

		document.querySelector("#image").addEventListener("change", (e)=>{
			//선택된 파일 배열 객체를 얻어냄
			const files = e.target.files;
			console.log(files);
			//만약 파일이 존재한다면
			if(files.length > 0){
				//파일로부터 데이터를 읽어들일 객체 생성
				const reader = new FileReader();
				//로딩이 완료(파일데이터를 모두 읽었을 떄)되었을 떄 실행할 함수 등록
				reader.onload = (event)=>{
					//읽은 파일데이터 얻어내기 
					const data = event.target.result;
					//console.log(data);
					//이미지 요소의 data를 src속성의 value로 넣으시오
					document.querySelector("#preview").setAttribute("src", data);
					document.querySelector("#preview").style.display="block";
					document.querySelector("#dropZone p").style.display="none";
				};
				//파일을 DataURL형식의 문자열로 읽어들이기 
				reader.readAsDataURL(files[0]);
			}
		})
	
	</script>
</body>
</html>
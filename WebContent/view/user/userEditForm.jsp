<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css?after"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>회원정보 수정</title>
<style>
.body {
	background-color: #333333;
	margin: none;
	padding: none;
}
.jumbotron {
	background-color: #141414;
}
</style>
</head>
<body class=body>
	<div align="center">
	
		<h1 class=subTitle>회원정보 수정</h1>

		<div class=profileBox>
				<img src="${picture}" alt="" class="profile"> 
		</div>
		<span><input type=button value="사진 삽입" class="agreeBtn" id="picInsert" onclick="window.open('/Moviebokka/user/insertUserPicture', '사진 등록', 'width=500, height=150, location=no, status=no, scrollbars=yes')"></span>
		<span><input type=button value="사진 삭제" class=agreeBtn id="picInsert" onclick="location.href='/Moviebokka/user/deletePicture'" ></span>
	
		<form action="/Moviebokka/user/editAction" method="post">
			<div class ="editTable">
				<div>
					<input type=text name=nickname value="${nickname }" class="junForm" id="nickname" placeholder="변경할 닉네임">
					<input type="button" value="중복확인" class="dupleCheckBtn" id="picInsert" onclick="checkNickname()">
				</div>
				<div>
					<input type=password name=password required="required" class="junForm" id="password" placeholder="변경할 비밀번호" oninput="checkPassword()">
				</div>
				<div>
					<input type=password name=password required="required" class="junForm" id="passwordCheck" placeholder="변경할 비밀번호 확인" oninput="checkPassword()">
				</div>
				<div class="submitForm">
					<input type="submit" value="수정"  class="agreeBtn">
					<input type=button id="cancel" value="취소" class=agreeBtn onclick="history.go(-1)">
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
	function checkPassword() {
    	let passowrd = $('#password').val();
        let passwordCheck = $('#passwordCheck').val();
  
        if(passwordCheck=="" && (passowrd != passwordCheck || passowrd == passwordCheck)){
            $("#password").css("background-color", "#FFFFFF");
        } else if (passowrd == passwordCheck) {
        	$("#password").css("background-color", "#B0F6AC");

        } else if (passowrd != passwordCheck) {
        	$("#password").css("background-color", "#FFCECE");
  
        }
    }
	
	function checkNickname(){
    	let nickname = $('#nickname').val();
    	location.href='/Moviebokka/user/nicknameCheck?nickname=' + nickname;
    }
	</script>

</body>
<jsp:include page="../partial/footer.jsp"/>
</html>
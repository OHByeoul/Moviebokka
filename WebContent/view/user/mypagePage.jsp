<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css?after"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>마이페이지</title>
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
	<div align="center" class=myPage>
		<h1 class=subTitle>내 정보</h1>
		<fieldset class="myPageProfile">
			<div>
				<img id="pic" src="${picture}" alt="" >
			</div>
			<div class="myInfo">
				<br>이메일: ${email }
				<br>닉네임: ${nickname }
				<br>가입일: ${regDate }
			</div>
		</fieldset>
	</div>
	<div clas="editBtnArea" align="center">
		<a href="/Moviebokka/board/myContentList"><input type="button" value="내가 쓴 글 보기" class="agreeBtn"></a>
		<a href="/Moviebokka/user/edit"><input type="button" value="회원정보 수정" class="agreeBtn"></a>
		<a href="/Moviebokka/user/deleteForm"><input type="button" value="회원 탈퇴" class="agreeBtn"></a>
	</div><p>
		
	
</body>
<jsp:include page="../partial/footer.jsp"/>

</html>
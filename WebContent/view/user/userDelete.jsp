<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>회원탈퇴</title>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

</head>
<body class=body>
	<div align="center">
		
		<h1 class="subTitle">정말 탈퇴하시겠습니까?</h1><p>
		<img alt="" src="../static/images/sad.png">
		<form action="/Moviebokka/user/deleteAction" class="delete">
			<input type="hidden" name=email value="${email }">
			<input type=password name=pass required="required" placeholder="비밀번호 입력" class="form">
			<span><input type="submit" value="확인" class="deleteBtn" onclick="ocation.href='/Moviebokka/user/mypage'"></span>
		</form>
		
	</div>
	
	<script type="text/javascript">
		$('#cancel').on('click', function(){
			location.href="/Moviebokka/user/mypage";
		});
	</script>
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>
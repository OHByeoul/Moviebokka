<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로필 사진 삽입</title>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
\
</head>
<body class=body>
	<div align="left">
		<form action="/Moviebokka/user/savePicture" method="post" enctype="multipart/form-data">
			<input type="file" name="picture" value="${email }"><p>
			<input type="submit" class=btn value="확인">
			<input type="reset" value="취소" class=btn onclick="history.go(-1)">
		</form>
	</div>
	
</body>
</html>
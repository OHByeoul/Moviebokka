<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>무비보까 로그인</title>
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
   <div align="center" class=login>
   
   <h1 class="subTitle">로그인</h1>
      <form action="/Moviebokka/user/loginCheck" method="post">
         <fieldset class="loginForm">
            <div>
               <input type=email name=email class="junForm" required="required" placeholder="아이디">
            </div>
            <div>
               <input type=password name=password class="junForm" required="required" placeholder="비밀번호"> 
            </div>
            <div>
               <input type=submit class="loginBtn" value=로그인>
            </div>
         </fieldset>
      </form>
      <a href="/Moviebokka/user/findPassowrd" class=findPassword>비밀번호 찾기</a>
   </div><p>
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 찾기</title>
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<style type="text/css">
.jumbotron {
   background-color: #141414;
}
.body {
   background-color: #333333;
   margin: none;
   padding: none;
}

/* div에 클래스 추가해서 마진줌, email class 이름 변경  */
.findPasswordDiv{
      margin: 100px;
   }
   
.findPassowrdForm{
width: 324px; 
height: 40px;
padding: 12px 20px;
margin: 8px 0; 
display: inline-block; 
border: 3px none; 
border-radius: 4px; 
box-sizing: border-box; 
}
   
</style>
</head>


<body class=body>
   <div align="center" class=findPasswordDiv>
      <h1 class=subTitle>비밀번호 찾기</h1>
      <form action="/Moviebokka/user/findPassowrdCheck" method="post">
         <input type="email" name=email class="findPassowrdForm" placeholder="비밀번호를 찾고자 하는 이메일을 입력 하세요.">
         <input type="submit" class="dupleCheckBtn" value="다음">
      </form>
   </div>
   
   
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>
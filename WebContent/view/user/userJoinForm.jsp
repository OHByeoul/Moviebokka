<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <link rel="stylesheet" href="/Moviebokka/static/css/myPage.css"> -->
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp"/>
<title>회원가입</title>

</head>
<body class=body>
   <div align="center">
      <h1  class=subTitle>회원가입</h1>
      <form action="/Moviebokka/user/joinAction" method="post" id=joinForm>
         <fieldset class=join>
            <div>
               <input type="email" name="email" class="form" id="email" required="required" placeholder="이메일">
               <input type="button" class=dupleCheckBtn value=중복확인  onclick="checkEmail()">
            </div>

            <div>
               <input type="text" name="nickname" class="form" id="nickname" required="required" placeholder="닉네임">
               <input type="button" class=dupleCheckBtn value=중복확인 onclick="checkNickname()">
            </div>
      
            <div>
               <input type="password" name="password" class="form" id="password" required="required" oninput="checkPassword()" placeholder="비밀번호">
            </div>
            <div>
               <input type="password" name="passwordCheck" class="form" id="passwordCheck" required="required" oninput="checkPassword()" placeholder="비밀번호 확인">
            </div>
            <div class=joinSubmit>
               <input type="submit" class=agreeBtn value=회원가입>
               <input type="button" class=agreeBtn value=취소 onclick="location.go(-1)">
            </div>
         </fieldset>

      </form>
   </div>
   <script>
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
    

    function checkEmail(){
       let email = $('#email').val()
       location.href='/Moviebokka/user/emailCheck?email=' + email;
    }
    
    function checkNickname(){
       let nickname = $('#nickname').val();
       location.href='/Moviebokka/user/nicknameCheck?nickname=' + nickname;
    }

   </script>
</body>
<jsp:include page="../partial/footer.jsp"/>
</html>
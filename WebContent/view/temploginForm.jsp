<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "./partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
        <form action="" id="login_form" method="post">
            <span>아디 </span>
            <input type="text" name="id"><br>
            <span>비번 </span>
            <input type="password" name="password"><br>
            <input type="button" id="submit" value="login">
        </form>
    
    
    <script>
    	$('#submit').on('click', function(){
    		let data = $('#login_form').serialize();
    		console.log(data);
    		location.href = "/Moviebokka/user/isAuthenticate?"+data;
    	});
    </script>
 </body>
</html>
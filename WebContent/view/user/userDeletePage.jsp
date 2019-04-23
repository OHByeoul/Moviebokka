<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원탈퇴</title>
</head>
<body>
	<c:if test="${result eq true }">
		<script type="text/javascript">
			alert("회원 탈퇴 완료");
			location.href="/Moviebokka/user/login2";
		</script>
	</c:if>
	<c:if test="${result eq false }">
		<script type="text/javascript">
			alert("비밀번호를 확인해주세요");
			location.href="/Moviebokka/user/deleteForm"
		</script>
	</c:if>

</body>
</html>
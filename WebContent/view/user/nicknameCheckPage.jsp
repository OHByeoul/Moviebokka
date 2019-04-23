<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>닉네임 중복 확인</title>
</head>
<body>
	<c:if test="${result eq true }">
		<script type="text/javascript">
			alert("이미 사용중인 닉네임입니다.");
			history.go(-1);
		</script>
	</c:if>
	<c:if test="${result eq false }">
		<script type="text/javascript">
			alert("사용 가능한 닉네임입니다.");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>
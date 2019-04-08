<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	background-color : #141414;
}
.panel-body {
	height : 400px;
	background-color : #f2f2f2;
}
.title {
	margin-left : 90px;
}
.ip{
	margin-left : 300px;
}
.time {
	margin-left : 120px;
}
.view {
	margin-left : 600px;
}
.recommand {
	margin-left : 50px;
}
h2 {
	color : #E6E6E6;
	text-align : center;
	margin-bottom : 30px;
}
</style>
<body>
	<div class="container">
		<h2>리뷰 내용</h2>
		<div class="panel-group">
			<div class="Panel with panel-primary class">
				<div class="panel-heading">
					<span class="num">번호</span> <span class="title">제목</span>
					<span class="ip">아이피</span>
				</div>
				<div class="Panel with panel-info class">
					<div class="panel-heading">
					
						<span class="nick">닉네임</span> <span class="time">추천</span> 
						<span class="view">조회수</span> <span class="recommand">시간</span>
					</div>
					<div class="panel-body">Panel Content</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
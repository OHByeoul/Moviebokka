<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagePath = request.getContextPath() + "/static/images/";
%>
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">

<title>영화보까</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
.jumbotron {
	display: block;
	background-size: cover;
	width: 100%;
	padding: 0px;
	margin-bottom: 0;
	margin-top: 0;
	background-color: #141414;
}

h1 {
	margin: 0;
}
</style>
</head>
<body class="body" style="margin: none; padding: none;">


	<!-- contentStart -->
	<div class="container">
		<div class="jumbotron">
			<h2 class="text-center companyLogo" style="padding: 30px;">REVISIO
				Introduction</h2>
			<div class="text-center">
				<img style="padding: 30px;" height="20px" width="auto" alt="무비보까 로고" align="top"
					src="<%=imagePath%>logo.png">
			</div>
			<p class="info text-left" style="font-size: 1em; padding: 30px;">
				REVISIO가 제공하는 <b>무비보까</b> 서비스는<br> 모든 사용자가 자신의 의견과 생각을 표출할수 있는
				공간을<br> 제공함으로서 단순하게 리뷰를 보는것뿐만이 아닌, 리뷰를<br> 작성하고 여러 사람들과
				공감와 소통을 할수 있게하는<br> 포털이 되도록 노력하겠습니다.
			</p>
		</div>
	</div>
	<br>
	<div class="container" style="padding: 15px; padding-bottom:80px;">
		<div class="jumbotron">
			<h3 class="text-center companyLogo" style="padding: 30px;">오시는 길</h3>
			<div class="text-center">
				<img class="img-responsive" alt="" src="<%=imagePath%>company.jpg"
					style="padding: 30px;">
			</div>
		</div>
	</div>
	<!-- contentEnd -->

	<!-- contentScriptStart -->

	<!-- contentScriptEnd -->

	<!-- footerStart -->
	<jsp:include page="../partial/footer.jsp"></jsp:include>
	<!-- footerEnd -->
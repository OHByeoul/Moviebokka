<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.revizio.moviebokka.dto.Member"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String imagePath = request.getContextPath() + "/static/images/";
	Member member = (Member)session.getAttribute("user");
%>

<div class="text-center">
<div class="jumbotron jumbotron_logo_style text-center">
         <a class="text-center" href="/Moviebokka/movie/main">
            <img class="jumbotron_logo_image" alt="" src="<%=imagePath%>movie-logo.png">
         </a>
   </div>
	
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#moviebokkaNavbar">
					<span class="icon-bar"> </span><span class="icon-bar"> </span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/Moviebokka/movie/main">무비보까</a>
			</div>
			<div class="collapse navbar-collapse" id="moviebokkaNavbar">
				<ul class="nav navbar-nav">
					<!-- dropdownNavStart -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">회사소개 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/Moviebokka/movie/companyIntro">회사소개 및
									찾아오시는 길</a></li>
							<li><a href="/Moviebokka/movie/companyOrg">조직구성</a></li>
						</ul></li>
					<!-- dropdownNavEnd -->
					<li><a href="/Moviebokka/movie/searchMovieName">영화검색</a></li>
        			<li><a href="/Moviebokka/movie/searchDetailInfo">상세검색</a></li>
					<!-- dropdownNavStart -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">장르별 영화 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li class="genre"><a id="action">액션</a></li>
							<li class="genre"><a id="war">전쟁</a></li>
							<li class="genre"><a id="fantasy">판타지</a></li>
							<li class="genre"><a id="horror">공포</a></li>
							<li class="genre"><a id="comedy">코미디</a></li>
							<li class="genre"><a id="melo" >멜로드라마</a></li>
							<li class="genre"><a id="romance">로맨스</a></li>
							<li class="genre"><a id="anime" >애니메이션</a></li>
						</ul></li>
					<!-- dropdownNavEnd -->
					<!-- dropdownNavStart -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">커뮤니티 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/Moviebokka/board/boardList?type=1">자유게시판</a></li>
							<li><a href="/Moviebokka/board/boardList?type=2">공지사항</a></li>
							<li><a href="/Moviebokka/board/boardList?type=3">FAQ</a></li>
						</ul></li>
					<!-- dropdownNavEnd -->
				</ul>
				<ul class="nav navbar-nav navbar-right">
				      	<%
				      		if(member == null){
				      	%>
					    <li><a href="/Moviebokka/user/login2"><span class="glyphicon glyphicon-log-in" style=margin-right:8px></span>로그인</a></li>
					    <li><a href="/Moviebokka/user/joinAccessTerm"><span class="icomoon icon-signup"></span>회원가입</a></li>
					    <% } %>
					    <%
				      		if(member != null){
				      	%>
							<li><a href="/Moviebokka/user/mypage"><span class="icomoon icon-signup"></span>마이페이지</a></li>
					      	<li><a href="/Moviebokka/user/logout"><span class="icomoon icon-signup"></span>로그아웃</a></li>
					    <% } %>
        
				</ul>
			</div>
		</div>
	</nav>
	<script>
	 let genre;
     
     $('.genre').on('click', function(e){
         let $target = $(e.target);
         genre = $target.text();    
         
         let link = '/Moviebokka/movie/getMovieGenre?genreName='+genre;
         $target.attr("href",link);
     });
	</script>
</div>
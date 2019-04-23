<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.4.0.min.js" integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg=" crossorigin="anonymous"></script>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<!-- <script src="//code.jquery.com/jquery-3.2.1.min.js"></script> -->
<!-- <script src="//code.jquery.com/jquery.min.js"></script> -->
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<div class="text-center">
   <div class="jumbotron text-center">
         <a class="text-center" href="/Moviebokka/movie/main">
            
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
                     <li><a href="#">이용약관</a></li>
                     <li><a href="#">정보보호정책</a></li>
                  </ul></li>
               <!-- dropdownNavEnd -->
               <li><a href="/Moviebokka/movie/searchMovieName">영화검색</a></li>
                 <li><a href="/Moviebokka/movie/searchDetailInfo">상세검색</a></li>
               <!-- dropdownNavStart -->
               <li class="dropdown"><a class="dropdown-toggle"
                  data-toggle="dropdown" href="#">장르별 영화 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li class="genre"><a id="action"href="#">액션</a></li>
                     <li class="genre"><a id="war" href="#">전쟁</a></li>
                     <li class="genre"><a id="fantasy" href="#">판타지</a></li>
                     <li class="genre"><a id="horror"href="#">공포</a></li>
                     <li class="genre"><a id="comedy" href="#">코미디</a></li>
                     <li class="genre"><a id="melo" href="#">멜로드라마</a></li>
                     <li class="genre"><a id="romance" href="#">로맨스</a></li>
                     <li class="genre"><a id="anime" href="#">애니메이션</a></li>
                  </ul></li>
               <!-- dropdownNavEnd -->
               <!-- dropdownNavStart -->
               <li class="dropdown"><a class="dropdown-toggle"
                  data-toggle="dropdown" href="#">커뮤니티 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                     <li><a href="#">자유게시판</a></li>
                     <li><a href="#">공지사항</a></li>
                     <li><a href="#">FAQ</a></li>
                  </ul></li>
               <!-- dropdownNavEnd -->
            </ul>
            <ul class="nav navbar-nav navbar-right">
               <li><a href="/Moviebokka/user/login"><span
                     class="glyphicon glyphicon-log-in"></span>로그인</a></li>
               <li><a href="/Moviebokka/user/join"><span
                     class="icomoon icon-signup"></span>회원가입</a></li>
            </ul>
         </div>
      </div>
   </nav>
</div>
<style>
@charset "UTF-8";


/* Remove the navbar's default margin-bottom and rounded borders */ 
.navbar {
  margin-bottom: 0;
  border-radius: 0;
}


/********************* text-size **********************/
a {
   font-size: 0.9em;
}

ul {
   font-size: 1.3em;
}

.footer_quick_link {
   font-size: 2em;
   color: cyan;
}

.nav_name {
   font-size: 0.3em;
   color: white;
}


/********************* boot-strap **********************/
.jumbotron {
   background-color: #141414;
}

h2, .info {
   color: #ffffff;
}

.review_table {
   color: #141414;
   background-color: #E6E6E6;
}

.well {
   color: white;
   background: #141414;
}

.review_btn_color {
   color: #141414;
}

.search_btn_color {
   color: #141414;
}

.padding_top {
   padding-top: 60px;
}
.padding_bottom {
   padding-bottom: 60px;
}

.search_movie_img_center {
   margin: auto;
   width: 50%;
   padding: 10px;
}

.search_movie_detail {
   text-align: left;
}

.spacing {
   padding: 30px;
}

.review_btn_text_color {
   color: white;
}

body {
   background-color: #333333;
   margin: none;
   padding: none;
}

content {
    background-color : #3c3c3c;
}

/* Add a gray background color and some padding to the footer */
footer {
  background-color: #f2f2f2;
  padding: 25px;
}

h3.h3{color:#E6E6E6;text-align:center;margin:1em;text-transform:capitalize;font-size:1.7em;}
.jumbotron{background-color : #141414;}
/********************* movie-grid **********************/
.movie-grid,.movie-grid .movie-image{position:relative}
.movie-grid{font-family:Poppins,sans-serif;z-index:1}
.movie-grid .movie-image a{display:block}
.movie-grid .movie-image img{width:100%;height:auto}
.movie-grid .pic-1{opacity:1;transition:all .5s ease-out 0s}
.movie-grid:hover .pic-1{opacity:0}
.movie-grid .pic-2{position:absolute;top:0;left:0;opacity:0;transition:all .5s ease-out 0s}
.movie-grid:hover .pic-2{opacity:1}
.movie-grid .movie-full-view{color:#141414;background-color:#141414;font-size:16px;height:24px;width:16.px;padding:18px;border-radius:20px 0 0;display:block;opacity:0;position:absolute;right:0;bottom:0;transition:all .3s ease 0s}
.movie-grid .movie-full-view:hover{color:#c0392b}
.movie-grid:hover .movie-full-view{opacity:1}
.movie-grid .movie-content{padding:6px 6px 0;overflow:hidden;position:relative}
.movie-content .rating{padding:0;margin:0 0 7px;list-style:none}
.movie-grid .rating li{font-size:15px;color:#ffd200;transition:all .3s ease 0s}
.movie-grid .rating li.disable{color:rgba(0,0,0,.2)}
.movie-grid .title{font-size:16px;font-weight:400;text-transform:capitalize;margin:0 0 3px;transition:all .3s ease 0s}
.movie-grid .title a{color:#E6E6E6}
.movie-grid .title a:hover{color:#c0392b}
.movie-grid .gernre{color:#FE9A2E;font-size:17px;margin:0;display:block;transition:all .5s ease 0s}
.movie-grid:hover .gernre{opacity:0}
.movie-grid .add-to-cart{display:block;color:#c0392b;font-weight:600;font-size:14px;opacity:0;position:absolute;left:10px;bottom:-20px;transition:all .5s ease 0s}
.movie-grid:hover .add-to-cart{opacity:1;bottom:0}
@media only screen and (max-width:990px){.movie-grid{margin-bottom:30px}}


.movie-grid{
	border: 1px;
	border-color: #4C4C4C;
	border-style: solid;
}
.movie-image{
	border: 1px;
	border-color: #4C4C4C;
	border-bottom-style: solid;
}
.sibal{
	font-size: 18px;
}
.gernre{
	font-size: 5px;
	
	
}

/********************* footer **********************/
.companyLogo {
   color: cyan;
}

.location {
   color: white;
}

.footerBackground {
   background-color: #141414;
}

/********************* searchMoviePage **********************/
.list-group-item-heading, .list-group-item-text {
 float : right;
}
.list-group-item-heading {
   text-align : center;
}
 .list-group-item-text{
    text-align : left;
}
</style>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    </head>
    <body>
            <div>
                    <h3 class="h3"></h3>
                </div>
                <div class="recommand"></div>
        
                <div class="temp_movie" style="display: block">
                    <div class="col-md-2 col-sm-6" >
                        <div class="movie-grid">
                            <div class="movie-image">
                                <a href="#"> <img class="pic-1" src="" onerror="this.src='/Moviebokka/static/images/2.jpg'"alt="임시 이미지"> <img
                                    class="pic-2" src="" alt="이미지 없음">
                                </a> <a href="#" class="fa fa-search movie-full-view"></a>
                            </div>
                            <div class="movie-content">
                            	<h3 class="title">
                                    <a href="#" class="sibal">영화제목</a>
                                </h3>
                                <div class="gernre"></div>
                                <div class="user_rating">평점 : 6.78</div>
                               
                               	<div class="ratingBox" >
	                               	<ul class="rating" > 
	                                    <li class="fa fa-star"></li>
	                                    <li class="fa fa-star"></li>
	                                    <li class="fa fa-star"></li>
	                                    <li class="fa fa-star"></li>
	                                    <li class="fa fa-star"></li>
	                                </ul>
                               	</div>
                                
                                 <a class="add-to-cart" href="">줄거리 보기</a> <input type="hidden"
                                    id="movieCode" name="movieCode" value="">
                            </div>
                        </div>
                    </div>
                </div>
            
        <script>
            let title;
            
            $('.genre').on('click', function(e){
                let $target = $(e.target);
                title = $target.text();
                $('.h3').text(title);
              //  let link = '/Moviebokka/movie/getGenre?genreName='+title;
                $target.attr("href",link);
            });
            
            
        </script>
    </body>
    </html>
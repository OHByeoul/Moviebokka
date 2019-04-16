<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />

<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
	<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
    </head>
    <body>
            <div>
                <h3 class="h3"></h3>
                </div>
                <div class="recommand"></div>
        
                <div class="temp_movie" style="display: none">
                    <div class="col-md-2 col-sm-6">
                        <div class="movie-grid">
                            <div class="movie-image">
                                <a href="#"> <img class="pic-1" src="" onerror="this.src='/Moviebokka/static/images/no-photo.jpg'"alt="임시 이미지"> <img
                                    class="pic-2" src="" alt="이미지 없음">
                                </a> <a href="#" class="fa fa-search movie-full-view"></a>
                            </div>
                            <div class="movie-content">
                                <ul class="rating">
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                    <li class="fa fa-star"></li>
                                </ul>
                                <h3 class="title">
                                    <a href="#"></a>
                                </h3>
                                <div class="gernre"></div>
                                <div class="user_rating"></div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<style>
    .container {
        background-color:#353535;
    }
    h2 {
        color : #E6E6E6;
        text-align:center;
    }
    .form-group{
        color : #E6E6E6;
    }
</style>
</head>
<body>
    <div class="container">
            <h2>리뷰 작성</h2>
            <form class="form-horizontal" action="/Moviebokka/review/updateReview" id = "review_form" method="post">
           		 <input type="hidden" name="movieCode" value="${review.m_code}" >
           		 <input type="hidden" name="memId" value="${session.mem_id}" >
           		 <input type="hidden" name="revId" value="${review.rev_id}" >
              <div class="form-group">
                    <label class="control-label col-sm-2" for="email">리뷰제목 </label>
                    <div class="col-sm-8 offset-md-1">
                      <input type="text" class="form-control" id="title" placeholder="리뷰 제목" name="title" 
                      		value="${review.rev_title}" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-2" for="email">닉네임 </label>
                    <div class="col-sm-8 offset-md-1">
                      <input type="text" class="form-control" id="nick" name="nick" value="${session.mem_nick}" readonly>
                    </div>
                  </div>
                  
                  <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">리뷰내용</label>
                        <div class="col-sm-8 offset-md-1">          
                            <textarea class="form-control" rows="8" cols="50" placeholder="리뷰 내용" name="content" id="content" required>${review.rev_content}</textarea>
                        </div>
                      </div>
            </form>
                  <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-1">
                      <button type="button" id="update_review"class="btn btn-primary">리뷰수정</button>
                    </div>
                    <div class=" col-sm-4">
                        <button type="button" id = "cancel" class="btn btn-danger">취소</button>
                      </div>
                </div>
    </div><!--container-->
    <script>
    	$(function(){
    		$('#update_review').on('click', function(){  
    			if($('#title').val().length <2){
    				alert('제목에 두 글자 이상 입력해주세요');
    			} else if($('#content').val().length < 5){
    				alert('내용에 다섯 글자 이상 입력해주세요');
    			} else {
    				$('#review_form').submit();
    			}
    		});
    		
    		$('#cancel').on('click', function(){
    			let movieCode = $("input[name='movieCode']").val();
    			location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
    		});
    	});
    </script>
</body>
</html>
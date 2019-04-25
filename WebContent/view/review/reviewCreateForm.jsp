<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp"/>
<jsp:include page="../partial/navbar.jsp" />
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css?after">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
 body {
	background-color: #353535;
}
    h2 {
        color : #E6E6E6;
        text-align:center;
    }
    .form-group{
        color : #E6E6E6;
    }
</style>
<body>
    <div class="container">
            <h2>리뷰 작성</h2>
            <form class="form-horizontal" action="/Moviebokka/review/createReview" id = "review_form" method="post" accept-charset="UTF-8">
           		 <input type="hidden" name="movieCode" value="${movieCode}" >
           		 <input type="hidden" name="memId" value="${member.mem_id}" >
              <div class="form-group">
                    <label class="control-label col-sm-2" for="email">리뷰제목 </label>
                    <div class="col-sm-8 offset-md-1">
                      <input type="text" class="form-control" id="title" placeholder="리뷰 제목" name="title" required>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="control-label col-sm-2" for="email">닉네임 </label>
                    <div class="col-sm-8 offset-md-1">
                      <input type="text" class="form-control" id="nick" name="nick" value="${member.mem_nick}" readonly>
                    </div>
                  </div>
                  
                  <div class="form-group">
                        <label class="control-label col-sm-2" for="pwd">리뷰내용</label>
                        <div class="col-sm-8 offset-md-1">          
                            <textarea class="form-control" rows="8" cols="50" placeholder="리뷰 내용" name="content" required></textarea>
                        </div>
                      </div>
            </form>
                  <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-1">
                      <button type="button" id="create_review"class="btn btn-primary">리뷰작성</button>
                    </div>
                    <div class=" col-sm-4">
                        <button type="button" id = "cancel" class="btn btn-danger">취소</button>
                      </div>
                </div>
    </div><!--container-->
    <script>
    	$(function(){
    		$('#create_review').on('click', function(){  
    			$('#review_form').submit();
    		});
    		
    		$('#cancel').on('click', function(){
    			let movieCode = $("input[name='movieCode']").val();
    			location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
    		});
    	});
    </script>
</body>
</html>
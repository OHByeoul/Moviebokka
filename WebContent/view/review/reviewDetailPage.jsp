<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	background-color: #141414;
}

.panel-body-rev {
	height: 400px;
	background-color: #f2f2f2;
}

.title {
	margin-left: 90px;
}

.ip {
	margin-left: 500px;
}

.time {
	margin-left: 120px;
}

.view {
	margin-left: 600px;
}

.recommand {
	margin-left: 50px;
}

h2 {
	color: #E6E6E6;
	text-align: center;
	margin-bottom: 30px;
}

.panel-heading {
	padding: 7px;
}

.btn-group {
	margin-bottom: 5px !important;
	padding: 0px !important;
}

.btn-group button {
	margin: 0 7px;
}

.panel-body-reply {
	padding-top: 0;
}

.panel-body-reply hr {
	margin: 0 0 15px 0;
}

.node-child {
	padding-right: 0 !important;
}
</style>
<body>
	<div class="container">
		<h2>리뷰 내용</h2>
		<div class="list">
			<button type="button" id="list" class="btn btn-default">목록으로</button>
			<button type="button" id="update" class="btn btn-default" style="display:none">글수정</button>
			<button type="button" id="delete" class="btn btn-default" style="display:none">글삭제</button>
		</div>
		<div class="panel-group">
			<div class="Panel with panel-primary class">
				<div class="panel-heading">
					<span class="num">번호 ${reviewDetail.rev_id}</span> <span class="title">제목 ${reviewDetail.rev_title}</span>
					<span class="ip">아이피 ${reviewDetail.rev_ip}</span>
				</div>
				<div class="Panel with panel-info class">
					<div class="panel-heading">
					
						<span class="nick">닉네임 ${reviewDetail.mem_nick}</span> <span class="recommand">추천 ${reviewDetail.rev_recommand}</span> 
						<span class="view">조회수 ${reviewDetail.rev_view}</span> <span class="time">시간 ${reviewDetail.rev_regdate}</span>
					</div>
					<div class="panel-body-rev">${reviewDetail.rev_content}</div>
				</div>
			</div>
		</div>
		<button type="button" id="recommand" class="btn btn-success">추천<span id="score"> ${reviewDetail.rev_recommand}</span></button>
		<button type="button" id="unrecommand" class="btn btn-danger">비추천 <span id="unscore">${reviewDetail.rev_unrecommand}</span></button>
		<div class="row" id="cmt-wrapper">
			<!--상위의 댓글이 붙는곳-->
			<div class="col-sm-12">
				<div class="panel panel-white post panel-shadow">
					<div>
						<div class="input-group">
							<input class="form-control" placeholder="댓글작성" type="text"
								id="cmt-txt">
							<div class="input-group-btn">
								<button class="btn btn-primary" id="cmt-add">
									<i class="fa fa-edit"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<form action="/Moviebokka/review/deleteReview" method="POST" id="deleteReviewForm" style="display: hidden">
	  <input type="hidden" id="revId" name="revId" value=""/>
	  <input type="hidden" id="movieCode" name="movieCode" value=""/>
	</form>
	<script>
		$(function(){
			let session = "${session.mem_nick}";
			let nick = "${reviewDetail.mem_nick}";
			let movieCode = "${reviewDetail.m_code}";
			let revId = "${reviewDetail.rev_id}";
			
			if(session !== 'undefined' && nick !== 'undefined'){
				if(session == nick){
					$('#delete').show();
					$('#update').show();
				}
			}
			
			$('#delete').on('click', function(){
				let result = confirm("정말로 삭제할꼬얌??");
				
				if(result){
					$('#revId').val(revId);
					$('#movieCode').val(movieCode);
					$("#deleteReviewForm").submit();	
				}
			});
			
			$('#update').on('click', function(){
				location.href = "/Moviebokka/review/updateReviewForm?revId="+revId;
			});
			
			$('#list').on('click', function(){
				let code = ${reviewDetail.m_code};
				location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+code;
			});			
			
			let recomStatus = false;
			if("${recommand.recom_status}"==1){
				recomStatus = true;
			} 
			let unrecomStatus = false;
			if("${recommand.unrecom_status}"==1){
				unrecomStatus = true;
			}
			
			$('#recommand').on('click', function(){
				if(unrecomStatus){
					alert("비추를 해제해야 ㅊㅊ가능");
					return false;
				}
				if(recomStatus){
					recomStatus = false;
				} else {
					recomStatus = true;
				}
				
				checkUserRecommand();
				
					$.ajax({
						url : "/Moviebokka/review/recommandReview",
						type : "GET",
						data : {revId : revId, status : recomStatus}
					}).done(function(result){
						console.log(result);
					}).fail(function(fail){
						console.log(fail);
					});
					let $score = $('#score').text();
					let score;
				if(recomStatus){
					score = String(Number($score)+1);
				} else {
					score = String(Number($score)-1);
				}
					$('#score').text(score);
				
				
			});
			
			$('#unrecommand').on('click', function(){
				if(recomStatus){
					alert("추천을 해제해야 비추 가능함 ㅇㅋ?");
					return false;
				}
				if(unrecomStatus){
					unrecomStatus = false;
				} else {
					unrecomStatus = true;
				}
				checkUserRecommand();
				
					$.ajax({
						url : "/Moviebokka/review/unrecommandReview",
						type : "GET",
						data : {revId : revId, status : unrecomStatus}
					}).done(function(result){
						console.log(result);
					}).fail(function(fail){
						console.log(fail);
					});
					let $score = $('#unscore').text();
					let score;
					if(unrecomStatus){
						score = String(Number($score)+1);
					} else {
						score = String(Number($score)-1);
					}
					$('#unscore').text(score);
				
			});
			
			function checkUserRecommand(){
				$.ajax({
					url : "/Moviebokka/review/checkUserRecommand",
					type : "GET",
					data : {revId : revId, status : recomStatus, unStatus : unrecomStatus}
				}).done(function(result){
					console.log(result);
				}).fail(function(fail){
					console.log(fail);
				});
			}
			
			////

		
		

			var old;

			function getUnique() {
			   return Math.random().toString(36).substr(1, 12);
			}

			function removeInput() {
			    if($('.node-modify').length > 0) {
			        $('.node-modify').each(function(){
			            var txt = $(this).find('.node-input').val();
			            //var line = '<div class="node-text-inner">' + txt + '</div>';
			            var line = '<div class="node-text-inner">' + old + '</div>';
			            $(this).replaceWith(line);
			        });
			    }

			    if($('.panel-body-reply').length > 0) {
			        $('.panel-body-reply').each(function(){
			            $(this).remove();
			        });
			    }

			}

			    /*
			    let gCnt = "${gCnt}"==="" ? 1 : "${gCnt}"; //제일큰 그룹값에 +1
			    */
			    let gCnt = "0";

			 

			    $('#cmt-add').on('click', function() {  /// 젤 상위의 댓글 버튼이 눌렸을 때
			       if($('#cmt-txt').val().trim() == "") {
			          alert("댓글을 입력하세요");
			          $('#cmt-txt').focus();
			          return;
			       } else if($('#cmt-txt').val().trim().length < 2) {
			          alert("댓글을 2자 이상 작성하여 주세요.");
			          $('#cmt-txt').focus();
			          return;
			       }
			        gCnt = parseInt(gCnt)+1;

			       var uniqueId = getUnique();
			       var line = '<div class="col-sm-12 node-parent" data-box="' + uniqueId + '" data-parent="none" data-group="'+gCnt+'" data-depth=0 data-seq=0 >';   // 제일 상위의 댓글 depth 0 seq 0  data-box : 내아이디인듯?
			       line += '  <div class="panel panel-default">';
			        line += '       <div class="panel-heading">'; // 이미지부분
			        line += '          <img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"> 2019-04-05 14:20';
			       line += '          <div class="btn-group pull-right"><button class="btn btn-xs btn-default btn-reply" data-node="' + uniqueId + '">댓글</button><button class="btn btn-xs btn-default btn-mod" data-node="' + uniqueId + '">수 정</button><button class="btn btn-xs btn-default btn-del" data-node="' + uniqueId + '">삭 제</button></div>';
			        line += '       </div>';
			        line += '       <div class="panel-body node-text">'; //글입력 부분
			        line += '           <div class="node-text-inner">' + $('#cmt-txt').val() + '</div>';
			        line += '       </div>';  
			       line += '  </div>'
			       line += '</div>';
			       $('#cmt-wrapper').append(line);
			        let inputComment = $('#cmt-txt').val();
			        let revId = "${reviewDetail.rev_id}";
			       $.ajax({
			            url : '/Moviebokka/comment/createComment',
			            type : 'GET',
			            data : {group : gCnt, depth : 0, order : 0, input : inputComment, revId : revId}
			       }).done(function(result){

			       }).fail(function(fail){

			       });
			       $('#cmt-txt').val('');
			      

			    });
			    
			    $('body').on('click', '.btn-mod', function(e) { //수정 버튼 눌렀을때
			       removeInput();
			        var target = $(this).closest('.panel').find('.node-text-inner');   
			        old = target.html();
			        var input = '<div class="input-group node-modify">';
			        input += '  <input class="form-control node-input" type="text" value="' + target.html() + '">';
			        input += '  <div class="input-group-btn node-input-btn"><button class="btn btn-primary btn-mod-complete"><i class="fa fa-edit"></i></button></div>';
			        input += '</div>';
			        target.replaceWith(input); 
			        $('body').find('.node-input').focus();
			    });

			    $('body').on('click', '.node-input, .node-input-btn, .btn-mod, .btn-reply, .reply-input-txt, .reply-input-btn', function(e) { e.stopPropagation(); });
			    $('body').on('click', 'button.btn-del', function() {  //삭제
			       var node = $(this).closest('[data-box]');
			        if(node.data('parent') == 'none') {
			           if(node.find('.node-child').length > 0) {
			              $(this).closest('.panel').remove(); 
			           } else {
			              node.remove();
			           }
			        } else {
			           if(node.find('.node-child').length > 0) {
			              $(this).closest('.panel').remove(); 
			           } else {
			              node.remove();
			           }
			        }
			       //var node = $(this).closest('.panel').remove(); 
			    });
			    $(document).on('click', function(e) { removeInput(); });
			    
			    $('body').on('click', '.btn-reply', function() {  // 대댓글 눌렀을때
			       removeInput();
			       var node = $(this).closest('[data-box]').find('.panel-body').first();
			       var input = '<div class="panel-body panel-body-reply">';
			       input += '  <hr />';
			        input += '  <div class="input-group node-reply">';
			        input += '      <input class="form-control reply-input-txt" type="text" placeholder="댓글을 입력하세요.">';
			        input += '      <div class="input-group-btn"><button class="btn btn-primary reply-input-btn" data-node="' + $(this).data('node') + '"><i class="fa fa-edit"></i></button></div>';
			        input += '  </div>';
			        input += '</div>'
			       node.after(input);
			        $('body').find('.input-reply-txt').focus();
			    });
			    
			    //댓글 수정
			    $('body').on('click', '.btn-mod-complete', function() { // 수정버튼 누른후에 붓그림 눌렀을때
			        var el = $(this).closest('.node-modify').find('.node-input');
			        if(el.val().trim() == '') {
			           alert("댓글을 입력하세요.");
			            el.focus();
			           return;
			        }
			        var line = '<div class="node-text-inner">' + el.val().trim() + '</div>';
			       $(this).closest('.node-modify').replaceWith(line);
			    });
			    
			    //댓글 입력
			    $('body').on('click', '.reply-input-btn', function() { //대댓글에서 버튼 눌렀을때
			       var uniqueId = getUnique();
			       var node =  $(this).closest('.panel');
			       //var thisNode = $(this).closest('.node-reply');
			       var thisNode = $(this).closest('.panel-body'); // 댓글형태 시작점
			       var parentId = $(this).data('node');
			       var input = thisNode.find('.reply-input-txt'); //입력한 값
			       var childNodes = node.siblings('[data-parent="' + parentId + '"]'); // 안에 값을 넣으므로써 해당 태그 특정
			    /**/var pGroup = $(this).closest('.node-parent').attr("data-group");
			    
			        var myDepth = $(this).closest('.col-sm-12').attr("data-depth"); //
			        var length = $(this).closest('.node-parent').children().length;

			        
			        myDepth = parseInt(myDepth)+1;
			       // console.log(pSeq);
			       if(input.val().trim() == "") {
			          alert("댓글을 입력하세요.");
			          input.focus();
			          return;
			       }
			             
			       var line = '<div class="col-sm-12 node-child" data-box="' + uniqueId + '" data-parent="' + parentId + '" data-group="'+pGroup+'" data-depth="'+myDepth+'" data-seq="0">';
			       line += '  <div class="panel panel-default">';
			        line += '       <div class="panel-heading">';
			        line += '          <img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"> 2019-04-05 14:20';
			       line += '          <div class="btn-group pull-right"><button class="btn btn-xs btn-default btn-reply" data-node="' + uniqueId + '">댓글</button><button class="btn btn-xs btn-default btn-mod" data-node="' + uniqueId + '">수 정</button><button class="btn btn-xs btn-default btn-del" data-node="' + uniqueId + '">삭 제</button></div>';
			        line += '       </div>';
			        line += '       <div class="panel-body node-text">';
			        line += '           <div class="node-text-inner">' + input.val().trim() + '</div>';
			        line += '       </div>';  
			       line += '  </div>'
			       line += '</div>';

			       if(childNodes.length == 0) {
			          node.after(line);
			       } else {
			          childNodes.last().after(line);
			       }

			       var parent = $(this).closest('.node-parent').find('.node-child');
			       console.log(parent.length);
			        for(let i = 0; i<parent.length; i++){
			             var seq = i+1;
			             parent.eq(i).attr("data-seq", seq);
			        }
			        thisNode.remove();
			    });
	
		});
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../partial/header.jsp"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../partial/header.jsp" />
<jsp:include page="../partial/navbar.jsp" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Moviebokka/static/css/mainPage.css">
<title>Insert title here</title>
</head>
<style>
body {
	background-color: #333333;
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

.node-child, .order {
	padding-right: 0 !important;
}

#comRegdate {

	padding-left : 800px;
}
</style>
<body>
	<div class="container">
		<h2>리뷰 내용</h2>
		<div class="list">
			<button type="button" id="list" class="btn btn-default">리뷰목록으로</button>
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
		<div id="children" style='display:none'>
		</div>	
			<!--상위의 댓글이 붙는곳-->
	
	<form action="/Moviebokka/review/deleteReview" method="POST" id="deleteReviewForm" style="display: hidden">
	  <input type="hidden" id="revId" name="revId" value=""/>
	  <input type="hidden" id="movieCode" name="movieCode" value=""/>
	</form>
	
	<div class="tempParent" style='display:none'>
		<div class="col-sm-12 node-parent"  data-parent="none" data-group="" data-depth="0" data-seq="0" data-box="">  
			<div class="panel panel-default">       
				<div class="panel-heading">          
					<img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"><span id="comNick"> ${comment.mem_nick}</span><span id="comRegdate">  ${comment.com_regdate}</span>       
						<div class="btn-group pull-right">
							<button class="btn btn-xs btn-default btn-reply" data-node="">댓글</button><button class="btn btn-xs btn-default btn-mod" data-node="${comment.com_data_box}">수 정</button><button class="btn btn-xs btn-default btn-del" data-node="${comment.com_data_box}">삭 제</button></div>      
						 </div>       
						 <div class="panel-body node-text">           
						 	<div class="node-text-inner"></div>       
						 </div>  				 
			</div>
		</div>
	</div>
	
	<div class="tempChild" style='display:none'>
		<div class="col-sm-12 node-child" data-box="" data-parent="" data-group="" data-depth="" data-seq="">
			<div class="panel panel-default">
				<div class="panel-heading">          
					<img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"><span id="comNick"> ${comment.mem_nick}</span><span id="comRegdate">  ${comment.com_regdate}</span>            
					<div class="btn-group pull-right"><button class="btn btn-xs btn-default btn-reply" data-node="${comment.com_data_box}">댓글</button><button class="btn btn-xs btn-default btn-mod" data-node="${comment.com_data_box}">수 정</button><button class="btn btn-xs btn-default btn-del" data-node="${comment.com_data_box}">삭 제</button>
				</div>       
			</div>       
			<div class="panel-body node-text">           
				<div class="node-text-inner">${comment.com_content}</div>       
			</div>  
		</div>
	</div>
	</div>
	<script>
		$(function(){
			//let session = "${session.mem_nick}";
			let session = "${sessionScope.user.mem_nick}";
			let nick = "${reviewDetail.mem_nick}";
			let movieCode = "${reviewDetail.m_code}";
			let revId = "${reviewDetail.rev_id}";
			var spot;
			
			let del = "${reviewDetail.rev_del}";
			
			if(session !== 'undefined'){
				if(session == nick && del==="0"){
					$('#delete').show();
					$('#update').show();	
				}
			}
			
			
			if(del==="1"){
				$('#cmt-txt').attr('readonly',true);
				$('#cmt-add').hide();
				$('#delete').hide();
				$('#update').hide();
				$('#recommand').attr('disabled', true);
				$('#unrecommand').attr('disabled', true);
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
				location.href = "/Moviebokka/movie/getMovieDetail?movieCode="+movieCode;
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
			
			<c:forEach items="${reviewComments}" var="comment" varStatus="status">
					var depth = "${comment.com_depth}";
					var pId = "${comment.com_group}";
					var dataBox = "${comment.com_data_box}";
					var group = "${comment.com_group}";
					var order = "${comment.com_order}";
					var input = "${comment.com_content}";
					var comNick = "${comment.mem_nick}";
					var comRegdate = "${comment.com_regdate}";
					var parent = "${comment.com_data_parent}";
					var spot;
					
						if(depth == 0){
							getParent(pId,dataBox,input,comNick,comRegdate,group);

						} else {
							getChild(pId,dataBox,input,comNick,comRegdate,group,order,depth,parent);
						}
						
						function getParent(pId,dataBox,input,nick,regdate){
							let $clone = $($('.tempParent')[0]).clone();
						
							$clone.find('.node-parent').attr("data-box",dataBox);
							$clone.find('.node-parent').attr("data-group",group);
							$clone.find('button').attr("node-box",dataBox);
							$clone.find('.node-text-inner').text(input);
							$clone.find('#comNick').text(comNick);
							$clone.find('#comRegdate').text(comRegdate);
							$clone.find('button').attr("data-node",dataBox);
							$clone.show();
							if(session !== comNick){
								console.log("in--p");
								$clone.find('.btn-mod').hide();
								$clone.find('.btn-del').hide();
							}
							$('#cmt-wrapper').append($clone);
							let box = $clone.find('.node-parent').attr("data-box");
						}
						
						function getChild(pId,dataBox,input,nick,regdate,group,order,depth,parent){
							let $clone = $($('.tempChild')[0]).clone();
						
							$clone.find('.node-child').attr("data-box",dataBox);
							$clone.find('.node-child').attr("data-group",group);
							$clone.find('.node-child').attr("data-seq",order);
							$clone.find('.node-child').attr("data-depth",depth);
							$clone.find('.node-child').attr("data-parent",parent);
							$clone.find('.node-text-inner').text(input);
							$clone.find('#comNick').text(comNick);
							$clone.find('#comRegdate').text(comRegdate);
							$clone.find('button').attr("data-node",dataBox);
							$clone.show();
							if(session !== comNick){
								$clone.find('.btn-mod').hide();
								$clone.find('.btn-del').hide();
							}
							$('#children').append($clone);
						
						}
			</c:forEach>
	
			var p= $('#cmt-wrapper').find('.node-parent'); 
			var c =$('#children').find('.node-child');
			
			for(let i = 0; i<p.length; i++){ 
					for(let j = 0; j<c.length; j++){
						if(p.eq(i).attr("data-box") === c.eq(j).attr("data-parent")){
							p.eq(i).append(c.eq(j));
						}
					}
			}
			
			var hideChildren = $('#children').find('.node-child');
			var children = $('#cmt-wrapper').find('.node-child');
			var loop = $('#children').find('.tempChild').length;
			
			for(let k = 0; k<loop; k++){
				children = $('#cmt-wrapper').find('.node-child');
				for(let i = 0; i<children.length; i++){
					for(let j = 0; j<hideChildren.length; j++){
						if(children.eq(i).attr("data-box")===hideChildren.eq(j).attr("data-parent")){
							children.eq(i).append(hideChildren.eq(j));
						}
					}
				}
			}
			

			var old;
			var now = getTimeStamp();

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

			   
			    let gCnt = "${gCnt}"==="" ? 0 : "${gCnt}";
			 	let getSession = "${sessionScope.user.mem_nick}";

			    $('#cmt-add').on('click', function() {
			    	if(getSession===''){
			    		alert('로그인 후 이용해주세연');
			    		location.href = "http://localhost:8090/Moviebokka/user/login2";
			    	}
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
			        line += '       <div class="panel-heading">'; 
			        line += '          <img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"><span id="comNick">'+"${sessionScope.user.mem_nick}"+'</span><span id="comRegdate">'+now+'</span>';
			       line += '          <div class="btn-group pull-right"><button class="btn btn-xs btn-default btn-reply" data-node="' + uniqueId + '">댓글</button><button class="btn btn-xs btn-default btn-mod" data-node="' + uniqueId + '">수 정</button><button class="btn btn-xs btn-default btn-del" data-node="' + uniqueId + '">삭 제</button></div>';
			        line += '       </div>';
			        line += '       <div class="panel-body node-text">'; 
			        line += '           <div class="node-text-inner">' + $('#cmt-txt').val() + '</div>';
			        line += '       </div>';  
			       line += '  </div>'
			       line += '</div>';
			       $('#cmt-wrapper').append(line);
			        let inputComment = $('#cmt-txt').val();
			        let revId = "${reviewDetail.rev_id}";
			       let dataBox = uniqueId;

				       $.ajax({
				            url : '/Moviebokka/comment/createComment',
				            type : 'GET',
				            data : {group : gCnt, depth : 0, order : 0, input : inputComment, revId : revId, dataBox : dataBox, dataParent : "none"}
				       }).done(function(result){
	
				       }).fail(function(fail){
	
				       });			        	
			       
			       $('#cmt-txt').val('');
			      

			    });
			    
			    
			    $('body').on('click', '.btn-mod', function(e) { 
			       removeInput();
			        var target = $(this).closest('.panel').find('.node-text-inner');   
			        old = target.html();
			        var input = '<div class="input-group node-modify">';
			        input += '  <input class="form-control node-input" type="text" value="' + target.html() + '">';
			        input += '  <div class="input-group-btn node-input-btn"><button class="btn btn-primary btn-mod-complete"><i class="fa fa-edit"></i></button></div>';
			        input += '</div>';
			        target.replaceWith(input); 
			        let dataNode = $(this).attr("data-node");
			        console.log(dataNode);
			        let dataBox = $(this).closest('.panel').find('.btn-mod-complete').attr("data-box",dataNode);
			        $('body').find('.node-input').focus();
			        
			        
			    });

			    $('body').on('click', '.node-input, .node-input-btn, .btn-mod, .btn-reply, .reply-input-txt, .reply-input-btn', function(e) { e.stopPropagation(); });
			    $('body').on('click', 'button.btn-del', function() {  //삭제
			       var node = $(this).closest('[data-box]');
			    /*
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
			    */
			        let dataNode = $(this).attr("data-node");
			        let input = $(this).closest('.col-sm-12').find('.node-text-inner').eq(0);
			        let replyBtn = $(this).closest('.col-sm-12').find('.btn-reply').eq(0);
			        let modifyBtn = $(this).closest('.col-sm-12').find('.btn-mod').eq(0);
			        let delBtn = $(this);
			        $.ajax({
			            url : '/Moviebokka/comment/deleteComment',
			            type : 'GET',
			            data : {input : input.text(),dataBox : dataNode}
			       }).done(function(result,e){
			    	   console.log(result);

			    	   input.text(result);
			    	   replyBtn.hide();
			    	   modifyBtn.hide();
			    	   delBtn.hide();
			    	   //e.stopPropagation();
			       }).fail(function(fail){
						console.log(fail);
			       });	
			    });
			    $(document).on('click', function(e) { removeInput(); });
			    
			    $('body').on('click', '.btn-reply', function() { 
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
			    $('body').on('click', '.btn-mod-complete', function() { 
			        var el = $(this).closest('.node-modify').find('.node-input');
			        if(el.val().trim() == '') {
			           alert("댓글을 입력하세요.");
			            el.focus();
			           return;
			        }
			        var line = '<div class="node-text-inner">' + el.val().trim() + '</div>';
			       $(this).closest('.node-modify').replaceWith(line);
			       
			       let dataBox = $(this).attr("data-box");
			       console.log(el.val());
			       console.log(dataBox);
			       $.ajax({
			    	   url : '/Moviebokka/comment/updateComment',
			    	   type : 'GET',
			    	   data : {input : el.val(), dataBox : dataBox}
			       }).done(function(success){
			    	   
			       }).fail(function(fail){
			    	   
			       });
			    	   
			    });
			    
			    //댓글 입력
			    $('body').on('click', '.reply-input-btn', function() {
			       var uniqueId = getUnique();
			       var node =  $(this).closest('.panel');
			       var thisNode = $(this).closest('.panel-body');
			       //var parentId = $(this).data('node');
			       var parentId = $(this).closest('.col-sm-12').attr("data-box");
			       var input = thisNode.find('.reply-input-txt');
			       var childNodes = node.siblings('[data-parent="' + parentId + '"]');
			       var pGroup = $(this).closest('.node-parent').attr("data-group");
			    
			       var myDepth = $(this).closest('.col-sm-12').attr("data-depth"); 
			       var length = $(this).closest('.node-parent').children().length;
			      
			        myDepth = parseInt(myDepth)+1;
			       
			       if(input.val().trim() == "") {
			          alert("댓글을 입력하세요.");
			          input.focus();
			          return;
			       }
			       
			       
			       var line = '<div class="col-sm-12 node-child" data-box="' + uniqueId + '" data-parent="' + parentId + '" data-group="'+pGroup+'" data-depth="'+myDepth+'" data-seq="0">';
			       line += '  <div class="panel panel-default">';
			        line += '       <div class="panel-heading">';
			        line += '          <img class="avatar" src="http://bootdey.com/img/Content/user_1.jpg" width="25" height="25"><span id="comNick">'+"${sessionScope.user.mem_nick}"+'</span><span id="comRegdate">'+now+'</span>';
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
			       let order = parent.length;
			        for(let i = 0; i<parent.length; i++){
			             var seq = i+1;
			             parent.eq(i).attr("data-seq", seq);
			        }
			       thisNode.remove();
			        
			     
			       let pg = $(this).closest('.node-parent');
			   
			       let content = input.val().trim();
			       $.ajax({
			            url : '/Moviebokka/comment/createComment',
			            type : 'GET',
			            data : {group : pGroup, depth : myDepth, order : order, input : content, revId : revId, dataBox : uniqueId, dataParent : parentId}
			       }).done(function(result){
			    	   
			       }).fail(function(fail){

			       });	
			    
			    });
			    
			    function getTimeStamp() {
			    	  var date = new Date();

			    	  var stamp =
			    	    leadingZeros(date.getFullYear(), 4) + '-' +
			    	    leadingZeros(date.getMonth() + 1, 2) + '-' +
			    	    leadingZeros(date.getDate(), 2);
			    	  return stamp;
			    	}

			    	function leadingZeros(n, digits) {
			    	  var zero = '';
			    	  n = n.toString();

			    	  if (n.length < digits) {
			    	    for (i = 0; i < digits - n.length; i++)
			    	      zero += '0';
			    	  }
			    	  return zero + n;
			    	}
	
		});
	</script>
</body>
</html>
$(function(){
	$('#board').on('click',function(){
		let url = "/Moviebokka/board/getFreeboardList?";
		let param = "startNum=1&endNum=5";
		location.href = url+param;
	});
});
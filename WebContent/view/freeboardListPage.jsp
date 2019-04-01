<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./partial/header.jsp"/>
<script src="/Moviebokka/static/js/paging.js"></script> 
	<div class = "container">
		<table class = "table">
			<thead>
				<tr class="row">
					<th class = "col-sm-3">id</th>
					<th class = "col-sm-3">title</th>
					<th class = "col-sm-3">content</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach  items="${boards}" var="board" varStatus="status">
					<tr class = "row">
						<td class = "col-sm-3">${board.id}</td>
						<td class = "col-sm-3"><a href="/Moviebokka/board/detail?id=${board.id}">${board.title}</a></td>
						<td class = "col-sm-3">${board.content}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
	
		var obj =  JSON.parse(JSON.stringify(${paging}));
		console.dir(obj);
		console.log(typeof(obj));
		console.log(obj.totalRowData);
		
	</script>
</body>
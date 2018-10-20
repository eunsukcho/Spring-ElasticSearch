<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	<%@ include file = "./include/navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class = "table table-bordered" width = "500px">
			<tr>
				<th width="10px">no</th>
				<th>title</th>
				<th>writer</th>
				<th>regdate</th>

			</tr>
			<c:forEach items = "${hwlist }" var = "vo"> 
			<tr>
				<td>${vo.hwno }</td>
				<td><a href = "/hwread${pageMaker.makeQuery(pageMaker.cri.page) }&hwno=${vo.hwno }">${vo.title }</a></td>
				<td>${vo.writer }</td>
				<td><fmt:formatDate value="${vo.today }" pattern="yyyy-MM-dd HH:mm"/> </td>
			</tr>
			</c:forEach>
		</table>
		<div>
			<ul>
				<c:if test = "${pageMaker.prev }">
					<li><a href = "hwListPage${pageMaker.makeQuery(pageMaker.startPage - 1) }">&laquo;</a></li>
				</c:if>
				
				<c:forEach begin = "${pageMaker.startPage }" end = "${pageMaker.endPage }" var = "idx">
					<li 
						<c:out value = "${pageMaker.cri.page==idx?'class=active':''}"/>>
						<a href = "hwListPage${pageMaker.makeQuery(idx)}">${idx }</a>
					</li>
				</c:forEach>
				
				<c:if test = "${pageMaker.next && pageMaker.endPage>0}">
					<li><a href = "hwListPage${pageMaker.makeQuery(pageMaker.endPage + 1) }">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
</body>
</html>
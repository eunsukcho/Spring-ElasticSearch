<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>

	<div class="w3-main" style="margin-left:250px">
 		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<h1 class="w3-text-teal">학과 공지사항</h1>
				<table width="100%">
				<c:forEach items="${list }" varStatus="cnt" var="vo">
				<tr>
					<td>${cnt.count }</td>
					<td><a href="${vo.url }">${vo.subject }</a></td>
					<td align="right">${vo.date }</td>
				</tr>
				</c:forEach>
				</table>
	     	</div>
    	</div>
  	</div>

<%@ include file = "./include/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
	<div class="w3-display-container w3-animate-opacity">
		<img src="./resources/images/yuhanbg.png" alt="boat" style="width:100%;min-height:250px;max-height:400px;">
	</div>
	
	<!-- 학과 공지사항 -->
	<div class="w3-main" style="margin-left:250px">
 		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<h1 class="w3-text-teal">Notice</h1>
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
	     	<div class="w3-third w3-container">
      			<a href="/noticeMore" class="w3-rigth w3-margin-top-32">더보기</a>
      		</div>
    	</div>
  	</div>
  	
  	<!-- 자료 게시판 최신글 -->
  	
  	<div class="w3-main" style="margin-left:250px">
 		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<h1 class="w3-text-teal">Data Center</h1>
				<h5>1. ASP.NET 게시판 관련 자료		2018.06.21</h5>
				<h5>2. 데이터베이스 전체 자료		2018.06.11</h5>
	     	</div>
	     	<div class="w3-third w3-container">
      			<a href="#" class="w3-rigth w3-margin-top-32">더보기</a>
      		</div>
    	</div>
  	</div>
  	
  	<!-- 과제 최신글 목록 -->
    <div class="w3-main" style="margin-left:250px">
 		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
				<h1 class="w3-text-teal">New Homework</h1>
				<h5>1. C# 팀프로젝트 제출		2018.06.29</h5>
	     	</div>
	     	<div class="w3-third w3-container">
      			<a href="#" class="w3-rigth w3-margin-top-32">더보기</a>
      		</div>
    	</div>
  	</div>
<%@ include file = "./include/footer.jsp" %>
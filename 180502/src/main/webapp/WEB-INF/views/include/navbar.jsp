<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IT Class</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
	body,h1 {font-family: "Raleway", sans-serif}
	body, html {height: 100%}
	.bgimg 
	{
	    background-image: url('./resources/images/bgimg.png');
	    min-height: 100%;
	    background-position: center;
	    background-size: cover;
	}
</style>
</head>
<body>
<div>

	<div class="w3-container w3-padding-24 w3-center w3-white"> <!--  -->
  		<img src="/resources/images/logo/yuhanlogo.png">
	</div>
	<div>
	<c:forEach items="${loginMemberList }" var="list">
	<div class="w3-bar w3-border w3-teal w3-center w3-padding-16">
			<a href="/hwPersonalList" class="w3-bar-item w3-wide w3-button">과제실</a>
    	<div class="w3-dropdown-hover w3-right">
  			<button class="w3-button">
				${list.memberName }
				<c:set var="name" value="홍길동" />
					<c:choose>
					    <c:when test="${list.memberStatus eq 'S'}">
					        (학생)
					    </c:when>
					    <c:when test="${list.memberStatus eq 'P'}">
					        (교수)
					    </c:when>
					    <c:when test="${list.memberStatus eq 'A'}">
					        (관리자)
					    </c:when>
					</c:choose>
				
				</button>
    			<div class="w3-dropdown-content w3-bar-block w3-card-4">
     				<a href="MyPage_" class="w3-bar-item w3-button">마이페이지</a>
      				
      				<c:choose>
      				<c:when test="${list.memberStatus eq 'S'}">
					        <a href="/updateSubject" class="w3-bar-item w3-button">과목 수정</a>
					    </c:when>
      				<c:when test="${list.memberStatus eq 'P'}">
					        <a href="/createSubject" class="w3-bar-item w3-button">과목 생성</a>
      						<a href="/manageSubject" class="w3-bar-item w3-button">과목 관리</a>
					    </c:when>
					 </c:choose>
					 <a href="/logout" class="w3-bar-item w3-button">로그아웃</a>
    			</div>
    	</div>
    	<a href="/messageHome" class="w3-bar-item w3-wide w3-button w3-right">쪽지함</a>
	</div>
	</c:forEach>
	</div>
</div>
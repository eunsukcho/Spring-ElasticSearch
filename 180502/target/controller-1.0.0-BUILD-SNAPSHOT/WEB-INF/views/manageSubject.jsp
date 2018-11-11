<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	var result = "${result}";
	
	if(result == "fail")
		{
			alert("접근 권한이 없습니다.");
			self.location = "/header";
		}
</script>
<table>
<tr>
	<th>번호</th>
	<th>이름</th>
	<th>수강학생수</th>
	<th>강의실</th>
	<th>학년</th>
	<th>수정</th>
	<th>삭제</th>
	</tr>
	<c:forEach items = "${subjectList }" var = "vo" varStatus="cnt">
	<c:forEach items = "${num }" var = "num" varStatus="cnt">
	<tr>
		<td>${cnt.count }</td>
		<td>${vo.YUHAN_SUBJECT_NAME }</td>
		<td>${num.countStudent }</td>
		<td>${vo.YUHAN_SUBJECT_CLASS_ROOM }</td>
		<td>${vo.YUHAN_SUBJECT_HAK }</td>
		<td><a href="/proUpdateSubject?subjectNo=${vo.YUHAN_SUBJECT_NUMBER }">수정</a></td>
		<td><a href="/deleteSubject?subjectNo=${vo.YUHAN_SUBJECT_NUMBER }">삭제</a></td>
	</tr>
	</c:forEach>
	</c:forEach>
</table>

<%@ include file = "./include/footer.jsp" %>
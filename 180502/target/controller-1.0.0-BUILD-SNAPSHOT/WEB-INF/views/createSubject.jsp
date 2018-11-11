<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<form action="createSubject" method="POST">
<table>
	<tr>
		<td>과목이름</td>
		<td><input type="text" name="subjectName"></td>
	</tr>
	<tr>
		<td>강의실</td>
		<td><input type="text" name="classNum"></td>
	</tr>
	<tr>
		<td>학년 선택</td>
		<td>
		<select name="memberGrade" style="width:175px">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option></select></td>
	</tr>
</table>

<input type="submit" value="완료">
</form>
<%@ include file = "./include/footer.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ include file = "./include/navbar.jsp" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<br>
<form action="professor_check" method="post">
<table border=2 width="800" class="w3-table" id="myTable">
	<tr>
		<td>전체선택
		<input type="checkbox" id="checkBoxAll" name="checkBoxAll">
		</td>
		<td>proNo</td>
		<td>proID</td>
		<td>proName</td>
		<td>proEmail</td>
	</tr>
	<c:forEach items="${professor_check }" var="YuhanProfessorVO">
	
		<tr>
			 <td><input type="checkbox" name="proNo" value="${YuhanProfessorVO.proNo}"> 
			<%--  <input type="checkbox" id="checkBox" name="checkBox" onclick="check(${YuhanProfessorVO.proNo}, this)"> --%>
			 </td>
		
			<td>${YuhanProfessorVO.proNo }</td>
			<td>${YuhanProfessorVO.proID }</td>
			<td>${YuhanProfessorVO.proName }</td>
			<td>${YuhanProfessorVO.proEmail }</td>
		
		</tr>
	</c:forEach>
	
	
</table>
<br>
	<button type="submit" class="w3-button" style="width: 100%;">승인</button>
</form>
<br>

<script>

</script>

<%@ include file = "./include/footer.jsp" %>
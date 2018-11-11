<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	var success = "${success}";
	
	if(success == "ok")
		{
			alert("변경되었습니다.");
			self.location = "/manageSubject";
		}
</script>
<form action="proUpdateSubject" method="POST">
<c:forEach items = "${subjectResult }" var = "vo" varStatus="cnt"> 
<input type="text" value="${vo.YUHAN_SUBJECT_NAME }" name="subjectName">
<input type="text" value="${vo.YUHAN_SUBJECT_CLASS_ROOM }" name="subjectClass">
<input type="hidden" value="${vo.YUHAN_SUBJECT_NUMBER }" name="subjectNo">
</c:forEach>
<input type="submit" value="완료">
</form>
<%@ include file = "./include/footer.jsp" %>
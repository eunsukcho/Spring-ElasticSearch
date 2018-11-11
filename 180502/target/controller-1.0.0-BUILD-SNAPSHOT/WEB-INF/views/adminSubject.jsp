<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function()
{
	var tempString = "";
    //최상단 체크박스 클릭
    $("#checkall").click(function()
    {
        //클릭되었으면
        if($("#checkall").prop("checked"))
        {
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[id=chk]").prop("checked",true);
            //클릭이 안되있으면
        }
        else
        {
            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[id=chk]").prop("checked",false);
        }
    });
    
    $('input:checkbox[id="chk"]').click(function () 
    {
    	
    	alert($("input[type='checkbox']").val());
    	$('input:checkbox[id="rd2"]').is(':checked');
    });
});
</script>
<table>
<tr>
	<th><input type="checkbox" id="checkall"></th>
	<th>이름</th>
	<th>학년</th>
	<th>담당교수</th>
	<th>승인</th>
	</tr>
	<c:forEach items = "${subjectList }" var = "vo" varStatus="cnt"> 
	<tr>
		<td><input type="checkbox" id="chk" value="${vo.YUHAN_SUBJECT_NUMBER}"></td>
		<td>${vo.YUHAN_SUBJECT_NAME }</td>
		<td>${vo.YUHAN_SUBJECT_HAK }</td>
		<td>${vo.proName }</td>
		<td><a href="adminSubjectAccess?subjectNO=${vo.YUHAN_SUBJECT_NUMBER}"><input type="button" value="승인"></a></td>
	</tr>
	</c:forEach>
</table>
<input type="button" value="승인">

<%@ include file = "./include/footer.jsp" %>
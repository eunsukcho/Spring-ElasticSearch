<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file = "./include/navbar.jsp" %>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.ckeditor.com/4.8.0/standard/ckeditor.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#list").click(function(){
		alert("목록 가기")
	});
    
});
function hidden() {
	document.getElementById('frm').submit();
	return false;
};

</script>
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
</head>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-container">
          <p><h1>${elastic.subject }</h1></p>
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>${elastic.professor }</p>
          <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i></p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i></p>
          <p><i class="fa fa-asterisk fa-fw w3-margin-right w3-large w3-text-teal"></i></p>
          
          <hr>
          
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Notice</a></p>
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Homework</a></p>
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Grades</a></p>
         </div>
        </div>
        </div>
        
        <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Homework</h2>
        <div class="w3-container">
        	<div>
          <table class="w3-table w3-bordered">
		    <tr>
		      <td colspan="2"><span style="font-size: small;">제목</span> | ${elastic.title }</td>
		      <td><span style="font-size: small;">제출</span> | </td>
		    </tr>
		    <tr>
		    	<td colspan="2"><span style="font-size: small;">제출기간</span> | ${elastic.startdate } - ${elastic.enddate }</td>
		    </tr>
		    <tr>
		    	<c:set var = "file" value="${file}" />
		    	<c:choose>
		    		<c:when test="${file eq '첨부 파일이 없습니다.' }">
		    			<td colspan="2"><span style="font-size: small;">참고자료</span> | ${file}</td>
		    		</c:when>
		    		<c:otherwise>
		    		<td colspan="2"><span style="font-size: small;">참고자료</span> |
		    			<c:forEach begin="0" end="${file.size()-1 }" var="idx">
		    				 &nbsp <a href="/fileDownload?url=${file.get(idx).fileSaveUrl}&fileName=${file.get(idx).fileName}">${file.get(idx).fileName}</a>
		    			</c:forEach>
		    		</td>
		    		</c:otherwise>
		    	</c:choose>
		    	
		    </tr>
		    <tr>
		    	<td colspan="2">${elastic.content }</td>
		    </tr>
		  </table>
		  </div>
		  <div class="w3-panel w3-border w3-border-meal">
		  	<p>제출 파일이 없습니다.</p>
		  </div>
		  <div class="w3-right w3-container">
		  	<p><button class="w3-button w3-teal">제출하기</button></p>
		  </div>
		  <hr>
		  <div class="w3-container">
		  	<p>댓글</p>
		  	<p><b>교수님</b> : 아쥬 훌륭해요 ^^</p>
		  	<div class="w3-container">
		  		<div class="w3-left">
		  		<textarea class="w3-input w3-border" cols="95"></textarea>
		  		</div>
		  		<div class="w3-right">
		  		<button class="w3-button w3-teal">작성</button>
		  		</div>
		  	</div>
		  </div>
		   <div class="w3-container">
		    <p><button class="w3-button w3-teal">List</button>
		   	<a href="/hwDelete?_id=${_id}&hwno=${hwno}&subjectID=${subjectID}"><button class="w3-button w3-right w3-teal">Delete</button></a>
		   	<a href="/hwUpdate?_id=${_id}&hwno=${hwno}&subjectID=${subjectID}"><button class="w3-button w3-right w3-teal">Update</button></a></p>
		   </div>
        </div>
        
      </div>
      
     
      </div>
        </div>
        </div>

	<form method="post" action="/hwUpdate" name="hiddenForm" id="frm">
		<input type="hidden" value="${subjectID }" name="subjectID">
	</form>
</html>
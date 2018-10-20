<%@ include file = "./include/navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	
		<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-container">
        <c:forEach items = "${subjectList }" var = "sub"> 
          <p><h1>${sub.YUHAN_SUBJECT_NAME}</h1></p>
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>${sub.proName}</p>
          <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>${sub.proEmail}</p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>${sub.YUHAN_SUBJECT_HAK}</p>
          <p><i class="fa fa-asterisk fa-fw w3-margin-right w3-large w3-text-teal"></i>${sub.YUHAN_SUBJECT_CLASS_ROOM}</p>
          
          <hr>
          </c:forEach>
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="/hwRegister?subjectID=${subjectID }">과제 등록</a></p>
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
				<th width="10px">no</th>
				<th>title</th>
				<th>regdate</th>
			</tr>
			<c:forEach begin="0" end="${elastic.size()-1 }" var="idx"> 
			<tr>
				<td>${idx+1 }</td>
				<td><a href="/hwread?_id=${elastic.get(idx)._id }&hwno=${hw.get(idx).hwno}&subjectID=${subjectID}">${elastic.get(idx)._source.title}</a> </td>
				<td>${elastic.get(idx)._source.date}</td>
			</tr>
			</c:forEach>
		  </table>  
		  </div>
        </div>
      </div>
      </div>
        </div>
        </div>
<script>
var result = '${result}';
		
if(result == "Success"){
	alert("정상적으로 글이 등록되었습니다.");
}
</script>
<%@ include file = "./include/footer.jsp" %>
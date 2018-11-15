<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file = "./include/navbar.jsp" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	<style>
		a:link { text-decoration: none;}
	</style>
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
          <c:set var = "rate" value = "${rate }" />
          <c:choose>
		  	<c:when test="${rate eq 'S' }">
		  		<p></p>
		  	</c:when>
		  	<c:otherwise>
		  		<p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="/hwRegister?subjectID=${subjectID }&selectClass=${selectClass}">과제 등록</a></p>
		  	</c:otherwise>
		  </c:choose>
         </div>
        </div>
        </div>
        <div class="w3-twothird">
    		<c:set var = "count" value = "${totalCount }" />
    		<c:if test="${empty count}">
    			<div>
    				등록된 글이 없습니다.
    			</div>
    		</c:if>
			<div class="w3-container w3-card w3-white w3-margin-bottom">
		        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Homework</h2>
		        
		        <div>
			        <select name = "searchType">
			        	<option value ="null" <c:out value="${cri.searchType == null?'selected':''}"/>>---</option>
			        	<option value ="title" <c:out value="${cri.searchType eq 'title'?'selected':''}"/>>Title</option>
						<option value ="content" <c:out value="${cri.searchType eq 'content'?'selected':''}"/>>Content</option>
			        </select>
			        <input type="text" name ='keyword' id = "KeywordInput" value = '${cri.keyword }'>
					<button id = 'searchBtn' class="w3-button w3-grey">Search</button>
				</div>
				
		        <div class="w3-container">
		        <div>
		          <table class="w3-table w3-bordered">
				    <tr>
						<th width="10px">no</th>
						<th>title</th>
						<th>regdate</th>
						<c:set var = "rate" value = "${rate }" />
						 <c:choose>
						  	<c:when test="${rate eq 'S' }">
						  		<th>complete</th>
						  	</c:when>
						 </c:choose>
					</tr>
					<c:forEach begin="1" end="${elastic.size()-1 }" var="idx"> 
					<tr>
						<td>${((totalCount-idx)-(maker.cri.page-1)*10)+1 }</td>
						<td><a href="/hwread${maker.makeSearch(maker.cri.page) }&_id=${elastic.get(idx-1)._id }&hwno=${hw.get(idx-1).hwno}&subjectID=${subjectID}&selectClass=${selectClass }">${elastic.get(idx-1)._source.title}</a></td>
						<td>${elastic.get(idx-1)._source.date}</td>
						<c:set var = "rate" value = "${rate }" />
						 <c:choose>
						  	<c:when test="${rate eq 'S' }">
						  		<c:set var = "reportCount" value="${report}" />
								  <c:choose>
								  	<c:when test="${reportCount.get(idx-1) eq 1 }">
									  	<td>O</td>
								  	</c:when>
								  	<c:otherwise>
									  	<td>X</td>
								  	</c:otherwise>
								  </c:choose>
						  	</c:when>
						 </c:choose>	
					</tr>
					</c:forEach>
				  </table>  
				  </div>
				  <div class="w3-main" style="margin:auto">
					  <div class="w3-bar">
					  <c:if test="${maker.prev}">
					  <span class="w3-button" onClick="location.href='hwList${maker.makeSearch(maker.startPage-1) }&subjectID=${subjectID}&selectClass=${selectClass}'">Previous</span>
					  </c:if>
					  <c:forEach begin = "${maker.startPage }" end = "${maker.endPage }" var = "i">
							<span class="w3-button <c:out value = "${maker.cri.page==i?'w3-teal':''}"/>" onClick="location.href='hwList${maker.makeSearch(i)}&subjectID=${subjectID}&selectClass=${selectClass}'">${i }</span>
					  </c:forEach>
					  <c:if test="${maker.next && maker.endPage > 0}">
						<span class="w3-button" onClick="location.href='hwList${maker.makeSearch(maker.endPage+1)}&subjectID=${subjectID}&selectClass=${selectClass}'">Next</span>
					  </c:if>
					  </div>
				  </div>
		        </div>
      		</div>
      	</div>   
	</div>
</div>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$('#searchBtn').click(function() {
		self.location = "hwList?page=1&subjectID="
						+'${subjectID}'
						+ "&selectClass="
						+'${selectClass}'
						+"&searchType=" 
						+ $("select option:selected").val() 
						+"&keyword=" 
						+ encodeURIComponent($('#KeywordInput').val());
	});
});
var result = '${result}';

if(result == "Success"){
	alert("정상적으로 글이 등록되었습니다.");
}

if(result == "Delete"){
	alert("정상적으로 글이 삭제되었습니다.");
}

</script>
<%@ include file = "./include/footer.jsp" %>
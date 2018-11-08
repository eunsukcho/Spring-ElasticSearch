<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ include file = "./include/navbar.jsp" %>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.ckeditor.com/4.8.0/standard/ckeditor.js"></script>

<script type="text/javascript">
var StringBuffer = function() {
    this.buffer = new Array();
};
StringBuffer.prototype.append = function(str) {
    this.buffer[this.buffer.length] = str;
};
StringBuffer.prototype.toString = function() {
    return this.buffer.join("");
};
</script>
<script type="text/javascript">

$(document).ready(function(){
	var frm = $("form[role = 'form']"); 
	console.log(frm);
	
	if('${rate}' ==  'S'){
		firstLoad();
	}
	
	$("#modify").on("click", function(){
		alert("수정")
		frm.attr("action", "/hwUpdate");
		frm.attr("method", "get"); //수정은 get 방식으로한다.
		frm.submit();
	});
	
	$("#delete").on("click", function(){
		frm.attr("action", "/hwDelete");
		frm.attr("method", "get");
		frm.submit(); //post방식 사용해줄것이기 때문에 메소드는 추가할 필요가없다.
	});
	
	$("#list").on("click", function(){
		frm.attr("method", "get");
		frm.attr("action", "/hwList");
		frm.submit();
	}); 
});
function firstLoad(e){
	alert("로드");
	var hwno = $('input[name="hwno"]').val();
	var page;
	
	if (e == null){
		page = 1;
	}else{
		page = e;
	}
	replyVO = new Object();
	replyVO.hwno = hwno;
	replyVO.repPage = page;
	jsonData = JSON.stringify(replyVO);
	
	$.ajax({
		type : "POST",
		url : "/loadRep",
		data : jsonData,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(data){ 
			$("#commentList").html("");
			for (var i = 0; i < data.length; i++) {
				var liTag = "<tr><td>"+ data[i].professorName+"</td><td>"+data[i].comment+"</td><td>"+data[i].repDate+"</td><td><button class='update-btn' value='"+data[i].repNo+"' onClick='repUpdate(this, "+page+")'>수정</button></td><td><button class='del-btn' value='"+data[i].repNo+"' onClick='repDel(this)'>X</button></td></tr><hr/>";
				$("#commentList").append(liTag);
			}
			paging(page);
		},
		error : function(){
			//alert("댓글 로딩 실패")
		}
	});
}
</script>
</head>
<form role="form" method = "post" action="#">
	    <input type = "hidden" name = "hwno" value = "${hwno}" id = "hwno">
	    <input type = "hidden" name = "professorNo" value = "${professorNo }" id = "professorNo">
	    <input type = "hidden" name = "proName" value = "${proName }" id = "proName">
	    <input type = "hidden" name = "subjectID" value = "${subjectID }">
	    <input type = "hidden" name = "_id" value = "${_id}">
	    <input type = "hidden" name = "page" value = "${cri.page}">
	    <input type = "hidden" name = "perPageNum" value = "${cri.perPageNum}">
	    <input type = "hidden" name = "searchType" value = "${cri.searchType }">
	    <input type = "hidden" name = "keyword" value = "${cri.keyword }">
	    <input type = "hidden" name = "file" value = "${file}">
</form>
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
		      	<td colspan="2"><span style="font-size: small;">과제 제목</span> | ${elastic.title }</td>
		    </tr>
		    <tr>
		    	<td><span style="font-size: small;">제출 시간</span> | ${reportInfo.reportDate}</td>
		    </tr>
		    <tr>
		    	<td colspan="2">${reportInfo.content }</td>
		    </tr>
		    <tr>
		    	<td>
		    	<c:set var = "reportFile" value="${reportFile}" />
				  <c:choose>
				  	<c:when test="${reportFile eq '제출 파일이 없습니다.' }">
				  		<div class="w3-panel w3-border w3-border-meal">
							<p>${reportFile }</p>
						</div>
				  	</c:when>
				  	<c:otherwise>
					  	<div class="w3-panel w3-border w3-border-meal">
					  		<c:forEach begin="0" end="${reportFile.size()-1 }" var="idx">
				    			&nbsp <p><a href="/download.do?filePath=${reportFile.get(idx).filePath}&fileName=${reportFile.get(idx).fileName}&saveFileName=${reportFile.get(idx).saveFileName}">${reportFile.get(idx).fileName}</a></p>
				    		</c:forEach>
					  	</div>
				  	</c:otherwise>
				  </c:choose>
				  </td>
		    </tr>
		  </table>
		  </div>
		  <hr>
		  <div class="w3-container">
		  	<p>댓글</p>
		  	<p><b></b></p>
		  	<div class="bs-docs-example">
				<table id="commentList">
		
				</table>
			</div>
			<div>
				<ul id="paging">
				
				</ul>
			</div>
		  	<div class="w3-container">
		  		<div class="w3-left">
		  		<textarea class="w3-input w3-border" cols="95" id="commentArea"></textarea>
		  		</div>
		  		<div class="w3-right">
		  		<button class="w3-button w3-teal" id="commentBtn" onclick="repAdd()">작성</button>
		  		</div>
		  	</div>
		  </div>
		   <div class="w3-container">
		    <p>
			    <button class="w3-button w3-teal" id = "list">List</button>
			   	<button class="w3-button w3-right w3-teal" id="delete">Delete</button>
			   	<button class="w3-button w3-right w3-teal" id="modify">Update</button>
		   	</p>
		   </div>
        </div>
        
      </div>
      </div>
        </div>
        </div>
</html>
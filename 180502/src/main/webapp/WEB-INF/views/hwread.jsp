<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
	var replyCount = '${replyCount}';
	
	if('${rate}' ==  'S' && replyCount != 0){
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
	
	$("#report").on("click", function(){
		frm.attr("method", "get");
		frm.attr("action", "/hwReport");
		frm.submit();
	});
	
 	$("#reportCheck").on("click", function(){

		var html = $(this).html();
 		var hwno = $('input[name="hwno"]').val();
 		var studentID = $("#studentID").val();
		
 		reportVO = new Object();
 		
 		reportVO.homeworkNo = hwno;
 		reportVO.studentID = studentID;
 		jsonData = JSON.stringify(reportVO);
 		
 		$.ajax({
 			type : "POST",
 			url : "/homeworkdata/reportInfo",
 			data : jsonData,
 			dataType : "json",
 			contentType : "application/json; charset=utf-8",
 			success : function(data){ 
 				alert(data.content);

 		 		var tag = "<p>"+data.content+"</p>";
 	 			$("#reportContent").html("");
 	 			$("#reportContent").append(tag);
 		 	},
 			error : function(){
 				alert("댓글 로딩 실패")
 			}
 		});
	}); 
 	
 	$("#studentCheck").on("click", function(){
 		var hwno = $('input[name="hwno"]').val();
 		
		reportVO = new Object();
 		reportVO.homeworkNo = hwno;
 		jsonData = JSON.stringify(reportVO);
 		
 		$.ajax({
 			type : "GET",
 			url : "/homeworkdata/reportStudentCheck/"+hwno,
 			dataType : "json",
 			success : function(data){ 
 				console.log(data.length);
 				for (var i = 0; i < data.length; i++) {
 					var result = parseInt(i)+parseInt(1);
					var liTag = "<tr><td>" + result +"</td><td>" + data[i].studentID + "</td><td>" + data[i].date + "</td><td>" + data[i].content + "</td><td><button class='showDetail' value='"+data[i].no+"' onClick='showReportDetail(this)'>상세 내용 확인</button></td></tr><hr/>";
					$("#studentCheckList").append(liTag);
					console.log(liTag);
 				}
 		 	},
 			error : function(){
 				alert("댓글 로딩 실패")
 			}
 		});
 		
 	});
 	
 	
});
function showReportDetail(e){
	alert($(e).val());
	var reportNo = $(e).val();
	$("#reportNo").val(reportNo);
	var frm = $("form[role = 'form']"); 
	frm.attr("method", "get");
	frm.attr("action", "/hwReportCheck");
	frm.submit();
}
function firstLoad(page){
	alert("로드");
	var hwno = $('input[name="hwno"]').val();
	var page;
	var studentID = '${studentID}';
	
	replyVO = new Object();
	replyVO.hwno = hwno;
	replyVO.repPage = page;
	replyVO.studentID = studentID;
	jsonData = JSON.stringify(replyVO);
	
	$.ajax({
		type : "POST",
		url : "/loadRep",
		data : jsonData,
		dataType : "json",
		headers : {
			"Content-Type" : "application/json",
		},
		success : function(data){ 
			var liTag = "";
			console.log(data.length);
			
			$("#commentList").html("");
			$(data.list).each(function(){
				liTag = "<li data-rno='" + this.repNo + "' data-comment='"+this.comment+"'class='replyLi'>" + this.professorName + ":" + this.comment + ":" + this.repDate + "<button>MOD</button></li>";
				$("#commentList").append(liTag);
			});
			printPaging(data.pageMaker);
		},
		error : function(){
			//alert("댓글 로딩 실패")
		}
	});
}
function printPaging(pageMaker){
	var str = "";
	
	if(pageMaker.prev){
		str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
	}
	for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
		var strClass = pageMaker.cri.pag == i ?'class=active':'';
		str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.next){
		str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
	}
	$('#paging').html(str);
}
</script>
<form role="form" method = "post" action="#">
		<input type = "hidden" name = "page" value = "${cri.page}">
	    <input type = "hidden" name = "perPageNum" value = "${cri.perPageNum}">
	    <input type = "hidden" name = "searchType" value = "${cri.searchType }">
	    <input type = "hidden" name = "keyword" value = "${cri.keyword }">
	    <input type = "hidden" name = "_id" value = "${_id}">
	    <input type = "hidden" name = "hwno" value = "${hwno}" id = "hwno">
	    <input type = "hidden" name = "subjectID" value = "${subjectID }">
	    <input type = "hidden" name = "selectClass" value = "${selectClass }" id = "selectClass">
	    <input type = "hidden" name = "reportNo" id="reportNo">
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
		    				 &nbsp <a href="/download.do?filePath=${file.get(idx).filePath}&fileName=${file.get(idx).fileName}&saveFileName=${file.get(idx).saveFileName}">${file.get(idx).fileName}</a>
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
		  <c:set var = "rate" value = "${rate }" />
		  <c:choose>
		  	<c:when test="${rate eq 'S' }">
		  		  <p>제출 파일</p>
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
		  	</c:when>
		  	<c:otherwise>
		  		<div></div>
		  	</c:otherwise>
		  </c:choose>
		  
		  <div id="reportContent">
		  	
		  </div>
		  
		  <c:set var = "rate" value = "${rate }" />
		  <c:choose>
		  	<c:when test="${rate eq 'S' }">
			  		<c:set var = "reportCount" value="${report}" />
					  <c:choose>
					  	<c:when test="${reportCount eq '1' }">
						  	<div class="w3-right w3-container">
						  		<p><button class="w3-button w3-teal" id="reportCheck">제출정보보기</button></p>
						  	</div>
					  	</c:when>
					  	<c:otherwise>
						  	<div class="w3-right w3-container">
						  		<p><button class="w3-button w3-teal" id="report">과제제출</button></p>
						  	</div>
					  	</c:otherwise>
					  </c:choose>
		  	</c:when>
		  	<c:otherwise>
		  			<div class="w3-right w3-container">
						<p><button class="w3-button w3-teal" id="studentCheck">제출학생목록</button></p>
					</div>
		  	</c:otherwise>
		  </c:choose>
		  <div class="bs-docs-example">
				<table id="studentCheckList">
		
				</table>
		 </div>
		 <c:set var = "rate" value = "${rate }" />
		 <c:choose>
		  	<c:when test="${rate eq 'S' }">
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
				</div>
		  	</c:when>
		  	<c:otherwise>
		  		
		  	</c:otherwise>
		  </c:choose>

		  <c:set var = "rate" value = "${rate }" />
		  <c:choose>
		  	<c:when test="${rate eq 'S' }">
		  		<div class="w3-container">
		  			<button class="w3-button w3-teal" id = "list">List</button>
		  		</div>
		  	</c:when>
		  	<c:otherwise>
		  		<div class="w3-container">
			    <p>
				    <button class="w3-button w3-teal" id = "list">List</button>
				   	<button class="w3-button w3-right w3-teal" id="delete">Delete</button>
				   	<button class="w3-button w3-right w3-teal" id="modify">Update</button>
			   	</p>
			   </div>
		  	</c:otherwise>
		  </c:choose>
        </div>
      </div>
      </div>
        </div>
        </div>

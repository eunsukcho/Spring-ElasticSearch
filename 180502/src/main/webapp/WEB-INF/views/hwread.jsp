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
	firstLoad();
	paging(1);
	
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

	var hwno = $("#hwno").val();
	var page;
	
	if (e == null){
		page = 1;
	}else{
		page = e;
	} 
	
	alert("페이지 : " + e);

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
				var liTag = "<tr><td>"+ data[i].professorName+"</td><td>"+data[i].comment+"</td><td>"+data[i].repDate+"</td><td><button class='del-btn' value='"+data[i].repNo+"' onClick='repDel(this)'>X</button></td></tr><hr/>";
				$("#commentList").append(liTag);
			}
		},
		error : function(){
			alert("댓글 로딩 실패")
		}
	});
}
function paging(e){
	
	var hwno = $("#hwno").val();
	

	replyVO = new Object();
	replyVO.hwno = hwno;
	jsonData = JSON.stringify(replyVO);
	
	$.ajax({
		type : "POST",
		url : "/totalRep",
		data : jsonData,
		dataType : "text",
		contentType : "application/json; charset=utf-8",
		success : function(data){ 
			var startPage;
			var endPage;
			var total;
			var page = e;
			var perPageNum = 5;
			var displayPageNum = 5;
			
			endPage = Math.ceil(page / displayPageNum) * displayPageNum;
			startPage = endPage - displayPageNum + 1;
			
			var prev = startPage == 1 ? false:true;
			var tempEndPage = Math.ceil(data/perPageNum);
			
			alert("end : " + endPage + " start : " + startPage + " tempEndPage : " + tempEndPage + " total : " + data);
			
			if(endPage > tempEndPage){
				endPage = tempEndPage;
			}
			alert("2end : " + endPage + " start : " + startPage + " tempEndPage : " + tempEndPage + " total : " + data);
			var next = endPage * 5 >= data ? false:true;
			
			$("#paging").html("");
			
			if(prev){
				startPage = startPage - 1;
				paging(e, startPage, endPage);
			}
			for (var i = startPage; i <= endPage; i++) {
				console.log(i);
				var tag = "<button id='moreRep' onclick='firstLoad("+i+")'>" + i + "</button>";
				$("#paging").append(tag);
			}
		},
		error : function(){
			alert("댓글 로딩 실패")
		}
	});
}
function repAdd(){
	replyVO = new Object();
	var comment = $("#commentArea").val();
	var hwno = $("#hwno").val();
	var professorNo = $("#professorNo").val();
	var proName = $("#proName").val();
	if(comment == null || $.trim(comment) == ""){
		alert("댓글을 입력해주세요");
		return false;
	}

	replyVO.comment = comment;
	replyVO.hwno = hwno;
	replyVO.professorNO = professorNo;
	replyVO.professorName = proName;
	
	jsonData = JSON.stringify(replyVO);
	
	$.ajax({
		type : "POST",
		url : "/addRep",
		data : jsonData,
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(data){ 
			console.log(data[0]);
			$("#commentArea").val("");
			$("#commentList").html("");
			for (var i = 0; i < data.length; i++) {
				var liTag = "<tr><td>"+ data[i].professorName+"</td><td>"+data[i].comment+"</td><td>"+data[i].repDate+"</td><td><button class='del-btn' value='"+data[i].repNo+"' onClick='repDel(this)'>X</button></td></tr><hr/>";
				$("#commentList").append(liTag);
			}
			paging();
		},
		error : function(){
			alert("댓글 로딩 실패")
		}
	});
}

function repDel(e){
	var repNo = $(e).val();
	alert(repNo);
	
	$.ajax({
		type : "GET",
		url : "/delRep/"+repNo,
		dataType: "text",
		success : function(data){
			if(data=='SUCCESS'){
				$("#commentList").html("");
				firstLoad();
				paging();
			}
		},
		error : function(){
			alert("댓글 삭제 실패")
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
		  <div class="w3-panel w3-border w3-border-meal">
		  	<p>제출 파일이 없습니다.</p>
		  </div>
		  <div class="w3-right w3-container">
		  	<p><button class="w3-button w3-teal">제출하기</button></p>
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
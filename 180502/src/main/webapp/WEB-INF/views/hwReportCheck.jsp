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
#modDiv {
	width:300px;
	height:100px;
	background-color:gray;
	position:absolute;
	top:50%;
	left:50%
	margin-top:-50px;
	margin-left:-150px;
	padding:10px;
	z-index:1000;
}
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
	
	var replyPage = 1;
	
	$("#paging").on("click", "li a", function(event){
		event.preventDefault();
		replyPage = $(this).attr("href");
		firstLoad(replyPage);
	});

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
	
	$("#replyAddBtn").on("click", function(){
		replyVO = new Object();
		var comment = $("#commentArea").val();
		var hwno = $('input[name="hwno"]').val();
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

				firstLoad();
			},
			error : function(){
				alert("댓글 로딩 실패")
			}
		});
	});	
	
	$("#commentList").on("click", ".replyLi button", function(){
		var reply = $(this).parent();
		alert("reply : " + reply);
		var rno = reply.attr("data-rno");
		var replytext = reply.attr("data-comment");
		
		alert(rno + ":" + replytext);
		$(".model-title").html(rno);
		$("#replytext").val(replytext);
		$("#modDiv").show("slow");
	});
	
	$("#replyDelBtn").on("click", function(){
		var rno = $(".model-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type : "GET",
			url : "/delRep/"+rno,
			dataType: "text",
			success : function(data){
				if(data=='SUCCESS'){
					alert("삭제 되었습니다.");
					$("#modDiv").hide("slow");
					firstLoad();
				}
			},
			error : function(){
				alert("댓글 삭제 실패")
			}
		});
	});
	
	$("#replyModBtn").on("click", function(){
		var rno = $(".model-title").html();
		var replytext = $("#replytext").val();
		
		replyVO = new Object();
		replyVO.comment = replytext;
		replyVO.repNo = rno;
		
		jsonData = JSON.stringify(replyVO);
		
		$.ajax({
			type : "put",
			url : "/updateRep/"+rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data : jsonData,
			dataType: "text",
			success : function(data){
				if(data=='SUCCESS'){
					alert("수정 되었습니다.");
					$("#modDiv").hide("slow");
					firstLoad();
				}
			},
			error : function(){
				alert("댓글 수정 실패")
			}
		});
	});
});
function firstLoad(page){
	alert("로드");
	var hwno = $('input[name="hwno"]').val();
	var page;
	
	replyVO = new Object();
	replyVO.hwno = hwno;
	replyVO.repPage = page;
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
function repDel(e){
	var repNo = $(e).val();
	alert(repNo);
	
	$.ajax({
		type : "GET",
		url : "/replies/delRep/"+repNo,
		dataType: "text",
		success : function(data){
			if(data=='SUCCESS'){
				$("#commentList").html("");
				firstLoad();
			}
		},
		error : function(){
			alert("댓글 삭제 실패")
		}
	});
}
function repUpdate(e){
	var repNo = $(e).val();
	alert(repNo);

	$.ajax({
		type : "GET",
		url : "/replies/updateRep/"+repNo,
		dataType: "text",
		success : function(data){
			if(data=='SUCCESS'){
				alert("수정 완료");
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
				<ul id="commentList">
		
				</ul>
			</div>
			<div id="modDiv" style="display:none;">
				<div class='model-title'></div>
				<div>
					<input type="text" id="replytext">
				</div>
				<div>
					<button type="button" id="replyModBtn">Modify</button>
					<button type="button" id="replyDelBtn">Delete</button>
					<button type="button" id="closeBtn">Close</button>
				</div>
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
		  		<button class="w3-button w3-teal" id="replyAddBtn">작성</button>
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
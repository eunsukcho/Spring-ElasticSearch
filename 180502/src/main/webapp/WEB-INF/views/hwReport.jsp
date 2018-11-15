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
.dragAndDropDiv {
				border: 2px dashed #92AAB0;
				width: 800px;
				height: 100px;
				color: #92AAB0;
				text-align: center;
				vertical-align: middle;
				padding: 10px 0px 10px 10px;
				font-size:200%;
				display: table-cell;
			}
			.statusbar{
			    border-top:1px solid #A9CCD1;
			    min-height:25px;
			    width:99%;
			    padding:10px 10px 0px 10px;
			    vertical-align:top;
			}
			.statusbar:nth-child(odd){
			    background:#EBEFF0;
			}
			.filename{
				display:inline-block;
				vertical-align:top;
				width:250px;
			}
			.filesize{
				display:inline-block;
				vertical-align:top;
				color:#30693D;
				width:100px;
				margin-left:10px;
				margin-right:5px;
			}
			.abort{
			    background-color:#A8352F;
			    -moz-border-radius:4px;
			    -webkit-border-radius:4px;
			    border-radius:4px;display:inline-block;
			    color:#fff;
			    font-family:arial;font-size:13px;font-weight:normal;
			    padding:4px 15px;
			    cursor:pointer;
			    vertical-align:top
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
	
	$("#modify").on("click", function(){
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
	
	var objDragAndDrop = $(".dragAndDropDiv");
    var dataStatus;
    var fd = new FormData();
    var fileURL;
    
    //css
	$(document).on("dragenter",".dragAndDropDiv",function(e){
	    e.stopPropagation();
	    e.preventDefault();
	    $(this).css('border', '2px solid #0B85A1');
	});
	$(document).on("dragover",".dragAndDropDiv",function(e){
	    e.stopPropagation();
	    e.preventDefault();
	});
	$(document).on('dragenter', function (e){
		e.stopPropagation();
		e.preventDefault();
	});
	$(document).on('dragover', function (e){
	  e.stopPropagation();
	  e.preventDefault();
	  objDragAndDrop.css('border', '2px dotted #0B85A1');
	});
	$(document).on('drop', function (e){
		e.stopPropagation();
		e.preventDefault();
	});
	$(".dragAndDropDiv").on("drop", function(e) {
		$(this).css('border', '2px solid green');
		e.preventDefault();
		//DROP 되는 위치에 들어온 파일 정보를 배열 형태로 받아온다.
		var files = e.originalEvent.dataTransfer.files;
		//DIV에 DROP 이벤트가 발생 했을 때 다음을 호출한다.
		handleFileUpload(files);
	});
	//
	
	function handleFileUpload(files) // 파일 DROP과 동시에 호출
	{
		var newFileList = Array.from(files);
		//파일 길이만큼 반복해 FormData에 셋팅
	    var megaByte = 1024*1024;
	    for (var i = 0; i < files.length; i++) {
	        fd.append(files[i].name, files[i]);
	        var tag = createFile(files[i].name, files[i].size,i);
	        $('#fileTable').append(tag);
	    }
	   
	    $(".del-btn").click(function(){ //버튼 누르기	   
	   		/* fd.delete(files[$(this).val()].name); */
	   		fd = new FormData();
		   	newFileList.splice($(this).val(),1);
	   		files = newFileList;
	   		$(this).parent().parent().remove();
	   		
	   		for (var i = 0; i < files.length; i++) {
		        fd.append(files[i].name, files[i]);
		 	}	
   		});
	  dataStatus = files.length;
	}
	
	 
	function createFile(fileName, fileSize, i) { //드래그 박스 밑 올린 파일 이름이 나오는 부분
		
		console.log(fileName, fileSize); //주긴 줌
		var file = {
				name : fileName,
				size : fileSize,
				format : function() {
					var sizeKB = this.size / 1024;
					if (parseInt(sizeKB) > 1024) {
						var sizeMB = sizeKB / 1024;
						this.size = sizeMB.toFixed(2) + " MB";
					} else {
						this.size = sizeKB.toFixed(2) + " KB";
					}
					//파일이름이 너무 길면 화면에 표시해주는 이름을 변경해준다.
					if(name.length > 70){
						this.name = this.name.substr(0,68)+"...";
					}
					return this;
				},
				tag : function() {
					var tag = new StringBuffer();
					tag.append('<tr>');
					tag.append('<td>'+this.name+'</td>');
					tag.append('<td>'+this.size+'</td>');
					tag.append("<td><button class='del-btn w3-btn w3-teal' value='"+i+"'>X</button></td>");
					tag.append('</tr>');
					return tag.toString();			
				}
		}
		return file.format().tag();
	}

	var sendFileToServer = function(hwno, professorno, subjectID){ //파일 전송 함수
		fd.append('hwno', hwno);
		fd.append('professorNo', '${professorNo}');
		fd.append('subjectID', subjectID);
		fd.append('studentID', '${studentID}');
		
		$.ajax({
			type : "POST",
			url : "/reportFileUpload", //Upload URL
			data : fd,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				frm.attr("action", "/hwread");
				frm.attr("method", "get"); 
				frm.submit();
			},
			error : function(){
				alert("글 등록 실패")
			}
		});
	}
	
    $("#addReport").click(function(){ //버튼 누르기
		fd.append('subjectID', $('#subjectID').val()); //지금은 임의로 설정해준다. 나중에 바꿀것
		sendData(); // 과제 등록
		 //파일 등록
	});
    
    function sendData(){
    	$("#content").val(CKEDITOR.instances.content.getData());
    	
    	reportVO = new Object();

    	var hwno = $("#hwno").val();
    	
		var content = $("#content").val();
		
		var professorNo = parseInt('${professorNo}');
		var subjectID = $("#subjectID").val();
		var selectClass = $("#selectClass").val();
		var jsonData;
		var file;

		if(dataStatus >= 1)
			file = 'Y';
		else
			file = 'N';
		
		reportVO.homeworkNo = hwno;
		reportVO.content = content;
		reportVO.subjectID = subjectID
		reportVO.selectClass = selectClass;
		jsonData = JSON.stringify(reportVO);
		
		console.log(jsonData);
		
		$.ajax({
			type : "POST",
			url : "/homeworkdata/addReport",
			data : jsonData,//보내는 데이터
			dataType : "text",//받는 데이터 타입
			contentType : "application/json; charset=utf-8",//보내는 타입
			success : function(data){ //받는 데이터
				if (file == 'Y'){
					sendFileToServer(hwno, professorNo, subjectID);
				}
				alert("과제 등록 성공")
				frm.attr("action", "/hwread");
				frm.attr("method", "get"); 
				frm.submit();
			},
			error : function(){
				alert("글 등록 실패")
			}
		});
    }
});

</script>
</head>
<form role="form" method = "post" action="#" id="fform">
		<input type = "hidden" name = "page" value = "${cri.page}">
	    <input type = "hidden" name = "perPageNum" value = "${cri.perPageNum}">
	    <input type = "hidden" name = "searchType" value = "${cri.searchType }">
	    <input type = "hidden" name = "keyword" value = "${cri.keyword }">
	    <input type = "hidden" name = "_id" value = "${_id}">
	    <input type = "hidden" name = "hwno" value = "${hwno}" id = "hwno">
	    <input type = "hidden" name = "subjectID" value = "${subjectID }" id="subjectID">
	    <input type = "hidden" name = "selectClass" value = "${selectClass}" id="selectClass">
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
		    </tr>
		    <tr>
		    	<td colspan="2"><span style="font-size: small;">제출기간</span> | ${elastic.startdate } - ${elastic.enddate }</td>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<textarea rows="5" cols="50" name = "content" id="content" onFocus="content()"></textarea>
	    			<script>	          					 
				   		CKEDITOR.replace('content');
					</script>
		    	</td>
		    </tr>
		    <tr>
		    	<td colspan="2">
		    		<div id="fileUpload" class="dragAndDropDiv">Drag & Drop Files Here</div>
		    		<table id='fileTable' class="w3-right">
						<tr>
						</tr>
				    </table>
		    	</td>
		    </tr>
		  </table>
		  </div>
		  <div class="w3-right w3-container">
		  	<p><button class="w3-button w3-teal" id="addReport">제출하기</button></p>
		  </div>
        </div> 
      </div>
      </div>
        </div>
        </div>
</html>
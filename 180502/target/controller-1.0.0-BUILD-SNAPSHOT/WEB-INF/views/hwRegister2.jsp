<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file = "./include/navbar.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
			.dragAndDropDiv {
				border: 2px dashed #92AAB0;
				width: 650px;
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
<script>
$(document).ready(function(){
	
    $( "#startday" ).datepicker();
    $( "#endday").datepicker();
    
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
		//브라우저로 이동되는 이벤트를 방지하고 드랍 이벤트를 우선시 한다.
		e.preventDefault();
		//DROP 되는 위치에 들어온 파일 정보를 배열 형태로 받아온다.
		var files = e.originalEvent.dataTransfer.files;
		//DIV에 DROP 이벤트가 발생 했을 때 다음을 호출한다.
		handleFileUpload(files);
	});
	//
	
	function handleFileUpload(files) // 파일 가져다놓았을 때 함수
	{
		alert("dd")
		alert(files.length)
		//파일 길이만큼 반복해 FormData에 셋팅
	   var megaByte = 1024*1024;
	   for (var i = 0; i < files.length; i++) 
	   {
	        fd.append(files[i].name, files[i]);
	        console.log(files[i].name, files[i]); //값잘들어옴.
	        var ResultTag = createFile(files[i].name, files[i].size);
	        $('#fileTable').append(ResultTag + "\n");
	   }
	  dataStatus = files.length;
	}

	
	function createFile(fileName, fileSize) { //드래그 박스 밑 올린 파일 이름이 나오는 부분
		alert("creatFile");
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
					tag.append("<td><button id='"+this.name+"' onclick='delFile(this)'>취소</button></td>");
					tag.append('</tr>');
					return tag.toString();			
				}
		}
		return file.name;
	}

	var sendFileToServer = function(){ //파일 전송 함수
		$.ajax({
			type : "POST",
			url : "/fileUpload", //Upload URL
			data : fd,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if(data) {
					alert('성공');
					filrURL = data;
				}else {
					alert('실패');
				}
			}
		});
	}
	
    $("#addHomework").click(function(){ //버튼 누르기
		fd.append('subjectID', $('#subjectID').val()); //지금은 임의로 설정해준다. 나중에 바꿀것
		sendData(); // 과제 등록
		 //파일 등록
	});
    
    function sendData(){
    	registerVO = new Object();
		var title = $("#title").val();
		var writer = $("#writer").val();
		var startday = $("#startday").val();
		var endday = $("#endday").val();
		var content = $("#content").val();
		var subjectID = $("#subjectID").val();
		var jsonData;
		var file;
		
		alert("subjectID" + subjectID);
		if(title == null || $.trim(title) == ""){
			alert("제목을 입력하세요");
			return false;
		}
		if(startday > endday){
			alert("시작날짜가 더 클 수 없다.")
		}
		if(dataStatus >= 1)
			file = 'Y';
		else
			file = 'N';
		
		registerVO.title = title;
		registerVO.writer = writer;
		
		registerVO.startday = new Date(startday);
		registerVO.endday = new Date(endday);
		registerVO.content = content;
		registerVO.dataStatus = file;
		registerVO.endStatus = "N";
		registerVO.deleteStatus = "N";
		registerVO.professorno = "2"; //나중에 바꿀 부분
		registerVO.subjectID = subjectID
		jsonData = JSON.stringify(registerVO);
		
		console.log(jsonData);
		
		$.ajax({
			type : "POST",
			url : "/homeworkdata/addhw",
			data : jsonData,//보내는 데이터
			dataType : "text",//받는 데이터 타입
			contentType : "application/json; charset=utf-8",//보내는 타입
			success : function(data){ //받는 데이터
				alert("과제 등록 성공");
				sendFileToServer();
			},
			error : function(){
				alert("글 등록 실패")
			}
		})
    }
});
</script>
</head>
<body>
    <div>
    	<div>
    		<div>
    			<label>Title</label>
    			<input type = "text" name = "title" id="title" placeholder = "제목을 입력하세요">
    		</div>
    		<div>
    			<label>Writer</label>
    			<input type = "text" name = "writer" id="writer" placeholder = "작성자를 입력하세요">
    		</div>
    		<div>
    			<label>StartDay</label>
    			<input type = "text" name = "startday" id="startday" readonly="readonly">
    			<label>EndDay</label>
    			<input type = "text" name = "endday" id="endday" readonly="readonly">
    		</div>
    		<div>
    			<label>File</label>
    			<div id="fileUpload" class="dragAndDropDiv">Drag & Drop Files Here</div>
    			<table id='fileTable'>
		   			<tr>
		   			</tr>
	   			</table>
    		</div>
    		<div>
    			<label>Content</label>
    			<textarea rows="5" cols="70" name = "content" id="content"></textarea>
    		</div>
    		
    	</div>
    	<div>
			<input type = "button" id="addHomework" value="글 작성">
			<input type="hidden" value="${subjectID }" name='subjectID' id='subjectID'>
    	</div>
    </div>
</body>
</html>
<%@ include file = "./include/footer.jsp" %>
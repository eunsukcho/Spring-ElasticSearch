<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
<style>
.ui-datepicker {
    background: #ffffff;
    border: 1px solid #cecece;
    color: #009688;
}

.ui-timepicker {
    background: #ffffff;
    border: 1px solid #cecece;
    color: #009688;
}

			.dragAndDropDiv {
				border: 2px dashed #92AAB0;
				width: 1500px;
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
<script src='//cdn.rawgit.com/fgelinas/timepicker/master/jquery.ui.timepicker.js'></script>
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
    $( "#startday" ).datepicker({ dateFormat: "yy-mm-dd" });
    $( "#endday").datepicker({ dateFormat: "yy-mm-dd" });
    
    $(function(){
    	 $("#startdayTime").timepicker();
    	 $("#enddayTime").timepicker();
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

	var sendFileToServer = function(data, professorno, subjectID){ //파일 전송 함수
		fd.append('hwno', data);
		fd.append('professorNo', professorno);
		fd.append('subjectID', subjectID);
		alert(professorno + subjectID);
		$.ajax({
			type : "POST",
			url : "/fileUpload", //Upload URL
			data : fd,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				/* alert('성공'); */
				self.location="/hwList?subjectID="+subjectID;
			},
			error : function(){
				alert("글 등록 실패")
			}
		});
	}
	
    $("#addHomework").click(function(){ //버튼 누르기
		fd.append('subjectID', $('#subjectID').val()); //지금은 임의로 설정해준다. 나중에 바꿀것
		sendData(); // 과제 등록
		 //파일 등록
	});
    
    function sendData(){
    	alert( $("select[name=subjectClass]").val());
    	

    	$("#content").val(CKEDITOR.instances.content.getData());
    	registerVO = new Object();
		var title = $("#title").val();
		var writer = $("#writer").val();
		var startday = $("#startday").val();
		var startdayTime = $("#startdayTime").val();
		var endday = $("#endday").val();
		var enddayTime = $("#enddayTime").val();
		var content = $("#content").val();
		var professorNo = parseInt($("#professorNo").val());
		var subjectID = $("#subjectID").val();
		var jsonData;
		var file;
		alert(new Date($.now()));
		
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
		registerVO.start = startday + " " +startdayTime;
		registerVO.end = endday+ " " + enddayTime;
		registerVO.content = content;
		registerVO.dataStatus = file;
		registerVO.endStatus = "N";
		registerVO.deleteStatus = "N";
		registerVO.professorno = professorNo; //나중에 바꿀 부분
		registerVO.subjectID = subjectID
		registerVO.subjectClass = $("select[name=subjectClass]").val();
		jsonData = JSON.stringify(registerVO);
		
		console.log(jsonData);
		
		$.ajax({
			type : "POST",
			url : "/homeworkdata/addhw",
			data : jsonData,//보내는 데이터
			dataType : "text",//받는 데이터 타입
			contentType : "application/json; charset=utf-8",//보내는 타입
			success : function(data){ //받는 데이터
				if (file == 'Y'){
					sendFileToServer(data, professorNo, subjectID);
				}
				alert("과제 등록 성공")
				self.location="/hwList?subjectID="+subjectID;
			},
			error : function(){
				alert("글 등록 실패")
			}
		});
    }
});

function cancle_onClick()
{
	if (confirm("작성을 그만 두고 이전 페이지로 돌아가겠습니까?") == true)
	{
		self.location="/hwList?subjectID="+${subjectID};
	}
	else
	{
		return;
	}
}
</script>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  <div class="w3-container w3-card w3-white w3-margin-bottom">
  <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Write Homework</h2>
  <div class="w3-row w3-container" style="margin-bottom: 7px">
  <input class="w3-input w3-border" type = "text" name = "title" id="title" placeholder = "TITLE" style="margin-bottom: 7px">
  <input class="w3-input w3-border" type = "text" name = "writer" id="writer" placeholder = "WRITER" style="margin-bottom: 7px; margin-right:7px">
  <select name="subjectClass" style="width:175px">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
  </select>
  <div class="w3-row" style="margin-bottom:7px">
		<div class="w3-col m3 w3-center">
			<input class="w3-input w3-border" type = "text" name = "startday" id="startday" readonly="readonly" placeholder = "START DATE">
		</div>
		<div class="w3-col m3 w3-center">
			<input class="w3-input w3-border"  type = "text" name =	"startdayTime" id = "startdayTime" readonly="readonly" placeholder = "START TIME">
		</div>
		<div class="w3-col m3 w3-center">
			<input class="w3-input w3-border" type = "text" name = "endday" id="endday" readonly="readonly" placeholder = "END DATE">
		</div>
		<div class="w3-col m3 w3-center">
			<input class="w3-input w3-border" type = "text" name =	"enddayTime" id = "enddayTime" readonly="readonly" placeholder = "END TIME">
		</div>
  </div>
  <div id="fileUpload" class="dragAndDropDiv">Drag & Drop Files Here</div>
	    			<table id='fileTable' class="w3-right">
			   			<tr>
			   			</tr>
		   			</table>
    		</div>
    		
    		<div class="w3-row" style="margin-bottom:7px">
    		<textarea rows="5" cols="70" name = "content" id="content"></textarea>
    			<script>	          					 
			   CKEDITOR.replace('content'); 
			</script>
    		</div>
  			<div class="w3-row">
  				<div class="w3-container" style="margin-bottom:7px; margin-top:20px">
  				<input class="w3-btn w3-teal w3-right" style="width:120px; margin-bottom:7px" type = "button" id="addHomework" value="작성">
  				<button type="button" class="w3-btn w3-gray w3-right" style="width:120px; margin-right:7px;" id="cancelBtn" onclick="cancle_onClick()">Cancel</button>
  				</div>
  			</div>
  </div>
  </div>
  </div>	
			<input type="hidden" value="${subjectID }" name='subjectID' id='subjectID'>
			<input type="hidden" value=1999 name='professorNo' id='professorNo'>
			
<%@ include file = "./include/footer.jsp" %>
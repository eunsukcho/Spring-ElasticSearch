<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% request.setCharacterEncoding("utf-8"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
	a.no-uline { text-decoration:none }
</style>
<script>
var perpage=1;
$(document).ready(function() 
{
	 $('#pageingBtn1').addClass("w3-teal");	
	 clickBtn1(1);
			$('#menuBtn1').on('click', function()
					{
				
						clickBtn1(1);
				
					});
			
			$('#menuBtn2').on('click', function()
			{
				clickBtn2(1);	
			});
			
			$('#menuBtn3').on('click', function()
			{
				clickBtn3(1);
			});
			
			
		});
		
		function cancleBtn_onClick(messageNo)
		{
				if (confirm("쪽지 전송을 취소하시겠습니까?") == true)
				{
					self.location="/deleteMessage?messageNum="+messageNo;
				}
				else
				{
					return;
				}
		}



function ajaxcall(page){
	
	$.ajax({
		   url: '/messageHomeList',
		   type : 'GET',
		   async : false,
		   dataType : 'json',
		   data:{'page':page},
		   contentType: "application/json; charset=UTF-8",  
     timeout: 10000,
		   error : function(request,status,error){
		       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   },
		   success: function(data)
		   {
		       //alert(data[1].yuhan_MESSAGE_TO_MEMBER_NUMBER);
		   
			   var results = data;
			   var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>From</th><th>Subject</th><th>Date</th></tr>';
			   $("#myTable").find("th").remove();
			   $("#myTable").find("tr:gt(0)").remove();
			   $.each(results , function(i){
	                str += '<tr><td><input type="checkbox" id="checkBox"></td><td>' + results[i].yuhan_MESSAGE_FROM_MEMBER_NUMBER;
	                str += '</td><td><a href="/message?messageNum='+results[i].yuhan_MESSAGE_NUMBER+'&messageTo='+results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER+'">' + results[i].yuhan_MESSAGE_SUBJECT + '</a></td><td>' + results[i].YUHAN_MESSAGE_SEND_DATE +'</td>';
	                str += '</tr>';
	           });
	           $("#myTable").append(str); 
	           
	           $("#pageingBtn"+page).addClass("w3-teal");
		   }
		}); 
	
	
	
}

function searchBtn(page)
{
	 var pagingBtnStr = "";
	 $("#pagingDiv *").remove();
	 $('.pagingBtn').removeClass("w3-teal");
				$.ajax({
					url: '/searchMessage'+ '${maker.makeQuery(1)}' + "&searchType=" + $("select option:selected").val()
					+ "&keyword=" + encodeURIComponent($('#keywordInput').val()),
				   type : 'GET',
				   async : false,
				   dataType : 'json',
				   contentType: "application/json; charset=UTF-8",  
		           timeout: 10000,
				   error : function(request,status,error)
				   {
				       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				   },
				   success: function(data)
				   {
					   var results = data;
					   var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>From</th><th>Subject</th><th>Date</th></tr>';
					   $("#myTable").find("th").remove();
					   $("#myTable").find("tr:gt(0)").remove();
					   if(results.length == 0)
						{
						   str += '<tr><td colspan="4" class="w3-center">검색 결과가 없습니다.</td></tr>';
						}
					   else
						   {
						   
						   
						   $.each(results , function(i){
							   var messageTo = results[i].yuhan_MESSAGE_FROM_MEMBER_NUMBER;
				                str += '<tr><td><input type="checkbox" id="checkBox"></td><td data-type="from">' + messageTo;
				                str += '</td><td><a href="/message?messageNum='+results[i].yuhan_MESSAGE_NUMBER+'&messageTo='+results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER+'">' + results[i].yuhan_MESSAGE_SUBJECT + '</a></td><td>' + results[i].YUHAN_MESSAGE_SEND_DATE +'</td>';
				                str += '</tr>';
				           });
						   }
					   
					   $("#myTable").append(str);
						   
					   }
					});
					}

function clickBtn2(page)
{
	$('#menuBtn1').removeClass("w3-teal");
	$('#menuBtn3').removeClass("w3-teal");
	$('#menuBtn2').addClass("w3-teal");
	$('.pagingBtn').removeClass("w3-teal");
	
	var pagingBtnStr = "";
	$("#selectType *").remove();
	$("#pagingDiv *").remove();
	$.ajax({
		   url: '/myMessageHomeList?page='+page,
		   type : 'GET',
		   async : false,
		   dataType : 'json',
		   contentType: "application/json; charset=UTF-8",  
           timeout: 10000,
		   error : function(request,status,error){
		       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   },
		   success: function(data)
		   {
			   var message = data.data;
			   var maker = data.maker;
		   
			   var results = message;
			   var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>Send To</th><th>Subject</th><th>Send Date</th><th>Read Date</th><th>Send Cancle</th></tr>';
	            
			   $("#myTable").find("th").remove();
			   $("#myTable").find("tr:gt(0)").remove();
			    $.each(results , function(i){
				   var printDate = "";
				   var messageCancle = "";
				   if(results[i].YUHAN_MESSAGE_READ_DATE != null)
					   {
					   		printDate = results[i].YUHAN_MESSAGE_READ_DATE;
					   }
				   
				   if(results[i].yuhan_MESSAGE_READ_STATUS == 'N')
					   {
					   		messageCancle = "전송취소";
					   }
				   if(results[i].yuhan_MESSAGE_STATUS == 'C')
					   {
					   		messageCancle = "취소됨";
					   }
				   
	                str += '<tr><td><input type="checkbox" id="checkBox"></td><td>' + results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER + '</td>';
	                str += '<td>' + results[i].yuhan_MESSAGE_SUBJECT + '</td><td>';
	                str += results[i].YUHAN_MESSAGE_SEND_DATE+'</td><td>'+printDate+'</td><td><p onClick="cancleBtn_onClick('+ results[i].yuhan_MESSAGE_NUMBER +')">'+messageCancle+'</p></td></tr>';
	            });
			    
			    if(maker.prev)
			    {
			    	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn2('+ (maker.startPage - 1) +')">Previous</span>';
			    }
	           for(var i=maker.startPage; i<=maker.endPage; i++)
	        	   {
	        	   		if(maker.cri.page == i)
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button w3-teal"';
	        	   		}
	        	   		else
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button"';
	        	   		}
	        	   		
	        	   		pagingBtnStr += ' onClick="clickBtn2('+i+')">' + i + '</span>';
	        	   				
	        	   }
	            if(maker.next && maker.endPage > 0)
		        {
	            	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn2('+ (maker.endPage + 1) +')">Next</span>';
		        }
	           $("#myTable").append(str);
	           $("#pagingDiv").append(pagingBtnStr);
		   }
		});
}

function clickBtn3(page)
{
	$('#menuBtn1').removeClass("w3-teal");
	$('#menuBtn2').removeClass("w3-teal");
	$('#menuBtn3').addClass("w3-teal");
	$('.pagingBtn').removeClass("w3-teal");
	
	var pagingBtnStr = "";
	$("#selectType *").remove();
	$("#pagingDiv *").remove();
	
	$.ajax({
		   url: '/messageSaveList?page=' + page,
		   type : 'GET',
		   async : false,
		   dataType : 'json',
		   contentType: "application/json; charset=UTF-8",  
     timeout: 10000,
		   error : function(request,status,error){
		       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   },
		   success: function(data)
		   {
			   var message = data.data;
			   var maker = data.maker;
		   
			   var results = message;
			   var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>From</th><th>Subject</th><th>Date</th></tr>';
			   $("#myTable").find("th").remove();
			   $("#myTable").find("tr:gt(0)").remove();
			   
			   $.each(results , function(i){
				   var messageTo = results[i].yuhan_MESSAGE_FROM_MEMBER_NUMBER;
	                str += '<tr><td><input type="checkbox" id="checkBox"></td><td data-type="from">' + messageTo;
	                str += '</td><td><a href="/message?messageNum='+results[i].yuhan_MESSAGE_NUMBER+'&messageTo='+results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER;
	                if(results[i].yuhan_MESSAGE_READ_STATUS == 'N')
	                {	
	                	str += '" style="color:blue';
	                }
	                str += '">' + results[i].yuhan_MESSAGE_SUBJECT + '</a></td><td>' + results[i].YUHAN_MESSAGE_SEND_DATE + '</td>';
	                str += '</tr>';
	           });
			   console.log(message);
			   if(maker.prev)
			    {
			    	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn3('+ (maker.startPage - 1) +')">Previous</span>';
			    }
	           for(var i=maker.startPage; i<=maker.endPage; i++)
	        	   {
	        	   		if(maker.cri.page == i)
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button w3-teal"';
	        	   		}
	        	   		else
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button"';
	        	   		}
	        	   		
	        	   		pagingBtnStr += ' onClick="clickBtn3('+i+')">' + i + '</span>';
	        	   				
	        	   }
	            if(maker.next && maker.endPage > 0)
		        {
	            	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn3('+ (maker.endPage + 1) +')">Next</span>';
		        }
	           $("#myTable").append(str);
	           $("#pagingDiv").append(pagingBtnStr);
		   }
		});
}

function clickBtn1(page)
{
	var pagingBtnStr = "";
	$('#menuBtn1').addClass("w3-teal");
	$('#menuBtn2').removeClass("w3-teal");
	$('#menuBtn3').removeClass("w3-teal");
	$("#selectType *").remove();
	$("#pagingDiv *").remove();
	$.ajax({
		   url: '/messageHomeList?page='+page,
		   type : 'GET',
		   async : false,
		   dataType : 'json',
		   //data:{pageIndex:pageIndex},
		   contentType: "application/json; charset=UTF-8",  
     timeout: 10000,
		   error : function(request,status,error){
		       alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		   },
		   success: function(data)
		   {
			   var message = data.data;
			   var maker = data.maker;
		   
			   var results = message;
			   var selectStr = '<div class="w3-col m6">';
			   selectStr += '<input type="button" class="w3-button w3-teal" id="sendMessage" value="답장">';
			   selectStr += '<button class="w3-button w3-teal" id="saveBtn">저장하기</button>';
			   selectStr += '</div>';
			   selectStr += '<div class="w3-col m2">';
			   selectStr += '<select name="searchType" class="w3-input w3-border">';
			   selectStr += '<option value="all"';
			   selectStr += '<c:out value="${cri.searchType == null?\'selected\':\'\'}"/>>All</option>';
			   selectStr += '<option value="id" <c:out value="${cri.searchType == \'id\'?\'selected\':\'\'}"/>>ID</option>';
			   selectStr += '<option value="subject" <c:out value="${cri.searchType == \'subject\'?\'selected\':\'\'}"/>>subject</option>';
			   selectStr += '</select>';
			   selectStr += '</div>';
			   selectStr += '<div class="w3-col m3">';
			   selectStr += '<input type="text" class="w3-input w3-right w3-border" id="keywordInput">';
			   selectStr += '</div>';
			   selectStr += '<div class="w3-col m1">';
			   selectStr += '<input type="button" value="검색" id="searchBtn" onClick="searchBtn(1)" class="w3-button w3-right w3-teal">';
			   selectStr += '</div>';
			   var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>From</th><th>Subject</th><th>Date</th></tr>';
			   $("#myTable").find("th").remove();
			   $("#myTable").find("tr:gt(0)").remove();
			   $.each(results , function(i){
	                str += '<tr><td><input type="checkbox" id="checkBox"></td><td>' + results[i].yuhan_MESSAGE_FROM_MEMBER_NUMBER;
	                str += '</td><td><a href="/message?messageNum='+results[i].yuhan_MESSAGE_NUMBER+'&messageTo='+results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER;
	                if(results[i].yuhan_MESSAGE_READ_STATUS == 'N')
	                {	
	                	str += '" style="color:blue';
	                }
	                str += '">' + results[i].yuhan_MESSAGE_SUBJECT + '</a></td><td>' + results[i].YUHAN_MESSAGE_SEND_DATE +'</td>';
	                str += '</tr>';
	           });
			   if(maker.prev)
			    {
			    	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn1('+ (maker.startPage - 1) +')">Previous</span>';
			    }
	           for(var i=maker.startPage; i<=maker.endPage; i++)
	        	   {
	        	   		if(maker.cri.page == i)
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button w3-teal"';
	        	   		}
	        	   		else
	        	   		{
	        	   			pagingBtnStr += '<span class="pagingBtn w3-button"';
	        	   		}
	        	   		
	        	   		pagingBtnStr += ' onClick="clickBtn1('+i+')">' + i + '</span>';
	        	   				
	        	   }
	            if(maker.next && maker.endPage > 0)
		        {
	            	pagingBtnStr += '<span class="pagingBtn w3-button" onClick="clickBtn1('+ (maker.endPage + 1) +')">Next</span>';
		        }
	           $("#myTable").append(str);
	           $("#pagingDiv").append(pagingBtnStr);
	           $("#selectType").append(selectStr);
	           
	           $('#sendMessage').on('click', function()
	       			{
	       					if($("#checkBox:checked").length == 0)
	       						{
	       							alert("답장보낼 메세지를 선택해주세요.");
	       						}
	       					else if($("#checkBox:checked").length >= 2)
	       						{
	       							alert("쪽지를 하나만 선택해주세요.");
	       						}
	       					else
	       						{
	       							//console.log($("#checkBox:checked").parent().parent().text());
	       							var td = $("#checkBox:checked").parent().parent();
	       							console.log($(td).find("td[data-type='from']").text());
	       							self.location = "/reSendMessage?messageTo="+$(td).find("td[data-type='from']").text();
	       						}
	       			});
	           
	           $('#saveBtn').on('click', function()
	           {
	        	   if($("#checkBox:checked").length == 0)
						{
							alert("저장할 메세지를 선택해주세요.");
						}
					else
						{
							
						}
	           });
		   }
		});	
}


function pageingBtnClick(a)
{
	ajaxcall(a);
	$(".pageingBtn").removeClass("w3-teal");
	$('#pageingBtn'+a).addClass("w3-teal");
	
	
	
}
</script>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  <div class="w3-container w3-card w3-white w3-margin-bottom">
  <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Message Home</h2>
  <div class="w3-row w3-container" style="margin-bottom: 7px">
  
  	<div class="w3-quarter w3-container">
  	<div>
  	<a href="messageSend"><button class="w3-btn w3-teal" style="width:100%">Send Message</button></a>
  	</div>
  <div class="w3-main">
	  	<ul class="w3-ul w3-border w3-hoverable">
		  <li class="w3-teal" id="menuBtn1">받은 쪽지함</li>
		  <li id="menuBtn2">보낸 쪽지함</li>
		  <li id="menuBtn3">쪽지 보관함</li>
		</ul>
		</div>
  	</div>
	  <div class="w3-threequarter w3-container">
	  <div class="w3-main" style="margin-bottom: 7px">
	  	<div class="w3-row" id="selectType" style="margin-bottom:7px">
	  	
	  </div>
	  <div class="w3-main">
	  	<table class="w3-table" id="myTable">
	  	</table>
	  </div>
	  <div class="w3-main" style="margin:auto">
		  <div class="w3-bar w3-center" id="pagingDiv">
		  
		  </div>
	  </div>
	  </div>
  </div>
  </div>
  </div>
  </div>
  
</div>
<%@ include file = "./include/footer.jsp" %>
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
    $.ajax({
         url: '/messageHomeList',
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
             //alert(data[1].yuhan_MESSAGE_TO_MEMBER_NUMBER);
         
            var results = data;
            var str = '<tr class="w3-teal"><th><input type="checkbox"></th><th>From</th><th>Subject</th><th>Date</th></tr>';
            $("#myTable").find("th").remove();
            $("#myTable").find("tr:gt(0)").remove();
            var tempI = null;
            $.each(results , function(i){
                   str += '<tr><td><input type="checkbox" id="checkBox"></td><td>' + results[i].yuhan_MESSAGE_FROM_MEMBER_NUMBER;
                   str += '</td><td><a href="/message?messageNum='+results[i].yuhan_MESSAGE_NUMBER+'&messageTo='+results[i].yuhan_MESSAGE_TO_MEMBER_NUMBER+'">' + results[i].yuhan_MESSAGE_SUBJECT + '</a></td><td>' + results[i].YUHAN_MESSAGE_SEND_DATE +'</td>';
                   str += '</tr>';
              });
              $("#myTable").append(str); 
              
              $('#sendMessage').on('click', function()
                   {
                         if($("#checkBox:checked").length == 0)
                            {
                               alert($("#checkBox:checked").length);
                            }
                         else if($("#checkBox:checked").length >= 2)
                            {
                               alert("쪽지를 하나만 선택해주세요.");
                            }
                         else
                            {
                               alert("어쩌지..");
                            }
                   });
         }
      }); 
   
         /* $('#menuBtn1').on('click', menuBtn1_Click()); */
         
         $('#menuBtn1').on('click', function()
               {
            
            $(".w3-teal").removeClass("w3-teal");
            $('#menuBtn1').addClass("w3-teal");
            
            $.ajax({
                  url: '/messageHomeList',
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
                  }
               });
               });
         
         $('#menuBtn2').on('click', function()
               {
            $('#menuBtn1').removeClass("w3-teal");
            $('#menuBtn3').removeClass("w3-teal");
            $('#menuBtn2').addClass("w3-teal");
            $.ajax({
                  url: '/myMessageHomeList',
                  type : 'GET',
                  async : false,
                  dataType : 'json',
                  data:{
                      'page' : $('#sigungu-se option:selected').val()
                  },
                  contentType: "application/json; charset=UTF-8",  
                    timeout: 10000,
                  error : function(request,status,error){
                      alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                  },
                  success: function(data)
                  {
                      //alert(data[1].yuhan_MESSAGE_TO_MEMBER_NUMBER);
                  
                     var results = data;
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
                       $("#myTable").append(str);
                  }
               });
               });
         
         $('#menuBtn3').on('click', function()
               {
            $('#menuBtn1').removeClass("w3-teal");
            $('#menuBtn2').removeClass("w3-teal");
            $('#menuBtn3').addClass("w3-teal");
               });
         
         
      });
      
      function cancleBtn_onClick(messageNo)
      {
            if (confirm("쪽지 전송을 취소하시겠습니까?") == true)
            {
               self.location="/deleteMessage?messageNo="+messageNo;
            }
            else
            {
               return;
            }
      }
      
function menuBtn1_Click() 
{   
   $('#menuBtn2').removeClass("w3-teal");
   $('#menuBtn3').removeClass("w3-teal");

   
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
         success: function(data){
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
     <a href="/messageSend"><button class="w3-btn w3-teal" style="width:100%">Send Message</button></a>
     </div>
  <div class="w3-main">
        <ul class="w3-ul w3-border w3-hoverable">
        <li class="w3-teal" id="menuBtn1">받은 쪽지함&nbsp;&nbsp;${noReadMessageCount }</li>
        <li id="menuBtn2">보낸 쪽지함</li>
        <li id="menuBtn3">쪽지 보관함</li>
      </ul>
      </div>
     </div>
     <div class="w3-threequarter w3-container">
     <div class="w3-main" style="margin-bottom: 7px">
        <input type="button" id="sendMessage" value="답장">
        <button>저장하기</button>
        <input type="text">
     </div>
     <div class="w3-main">
        <table class="w3-table" id="myTable">
        </table>
     </div>
     <div class="w3-main" style="margin:auto">
        <div class="w3-bar">
        <c:if test="${maker.prev}">
         <a href="/massageHome?page=${maker.startPage - 1 }" class="w3-button">Previous</a>
         </c:if>
        <c:forEach begin="${maker.startPage }" end="${maker.endPage }" var="i">
         <span <%-- href="/messageHome?page=${i }" --%> class="w3-button pageingBtn" id="pageingBtn${i }" onClick="pageingBtnClick(${i }); perpage=${i };">${i }</span>
         </c:forEach>
        </div>
     </div>
     </div>
  </div>
  </div>
  </div>
  
</div>
<%@ include file = "./include/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<form action="/messageSend" method="post" name="messageSend">
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Send Message</h2>
        <div class="w3-row w3-container">
        <div class="w3-threequarter w3-container">
        <input class="w3-input w3-border" type="text" style="margin-bottom: 7px; background-color: #EAEAEA" id="messegeUser" name="YUHAN_MESSAGE_TO_MEMBER_NUMBER" readonly value="">
        	<input class="w3-input w3-border" type="text" style="margin-bottom: 7px" name="YUHAN_MESSAGE_SUBJECT" placeholder="Insert Message Subject...">
        	<textarea rows="10" class="w3-input w3-border" style="resize:none; margin-bottom: 7px;" name="YUHAN_MESSAGE_CONTENT"></textarea>
       
       		
        	</div>
        <div class="w3-quarter w3-container">
        <input class="w3-input w3-border w3-padding" type="text" placeholder="검색하기" id="myInput" onkeyup="myFunction()" style="margin-bottom: 7px">
        <div class="w3-border"  style="overflow:auto; display:block; height:40%">
        <table class="w3-table" id="myTable">
		 <tr>
		 	<th><input type="checkbox" id="checkBoxAll" name="checkBoxAll"></th>
		 	<th colspan="2"><b>전체 보내기</b></th>
		 </tr>
			<c:forEach items="${userList }" varStatus="cnt" var="userList">
				<%-- <tr>
					<td colspan="3"><b class="text-center">
						<c:set var="name" value="홍길동" />
					<c:choose>
					    <c:when test="${userList.memberGrade eq '3'}">
					        3학년
					    </c:when>
					    <c:when test="${userList.memberGrade eq '2'}">
					        2학년
					    </c:when>
					    <c:otherwise>
					        1학년
					    </c:otherwise>
					</c:choose>
					
					<c:choose>
					    <c:when test="${userList.memberClass eq '1'}">
					        1반
					    </c:when>
					    <c:when test="${userList.memberGrade eq '2'}">
					        2반
					    </c:when>
					    <c:otherwise>
					        3반
					    </c:otherwise>
					</c:choose>
					::</b>
					</td> --%>
				</tr>
				<tr>
					<td><input type="checkbox" id="checkBox" name="checkBox" onclick="check(${cnt.count}, this)"></td>
					<td>${userList.memberHak }</td>
					<td id="memberName">${userList.memberName }</td>
				</tr>
				</c:forEach>
		</table>
		</div>
        </div>
        </div>
        <div class="w3-container" style="margin-bottom:7px; margin-top:20px">
       		<button type="submit" class="w3-btn w3-teal w3-right" style="width:120px">Send &nbsp; ❯</button>
       		<button type="button" class="w3-btn w3-gray w3-right" style="width:120px; margin-right:7px" id="cancelBtn">Cancel</button>
		   	
        	</div>
      </div>
  </div>
</div>

<div id="submitMessage" class="w3-modal" style="display: none;">
  <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
    <div class="w3-container w3-white w3-center">
      <h2 class="w3-wide">Success Send Message</h2>
      <p>쪽지를 성공적으로 보냈습니다.</p>
      <a href="/messageHome"><button type="button" class="w3-button w3-padding-large w3-teal w3-margin-bottom" onclick="document.getElementById('submitMessage').style.display='none'">돌아가기</button></a>
    </div>
  </div>
</div>
</form>

<script>
	$(document).ready(function()
	{
		var seen = {}; 
		$('table tr').each(function() { 
		    var txt = $(this).text(); 
		    if (seen[txt]) 
		     $(this).remove(); 
		    else 
		     seen[txt] = true; 
		}); 
		
		$("#myInput").on("keyup", function() 
		{
		    var value = $(this).val().toLowerCase();
		    $("#myTable tr").filter(function() 
		    {
	      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    	});
	  	});

	$("#cancelBtn").on("click", function() 
	{
		if (confirm("변경사항이 저장되지 않을 수 있습니다.") == true)
		{
			self.location="/messageHome";
		}
		else
		{
			return;
		}
	});
	
	/* $("#checkBoxAll").click(function()
	{
		if($("#checkBoxAll").prop("checked"))
		{
			$("input[name=checkBox]").prop("checked",true);
			
			var table = document.getElementById('myTable');
			
		}
		$("input[name=checkBox]").prop("checked",false);
	}); */
		    
	var tbl = $("#myTable");
	
	$(":checkBox:first", tbl).click(function()
	{
        // 클릭한 체크박스가 체크상태인지 체크해제상태인지 판단
        if($(this).is(":checked"))
        {
            $(":checkBox", tbl).attr("checked", "checked");
            //var arr = $('input[name=checkBox]:checked').parent().child().serializeArray().map(function(item) { return item.value });
            
            $(":checkBox:not(:first)", tbl).each(function(i,elements)
            {
            	index = $(elements).index(":checkBox:not(:first)", tbl); 
            	
            	var hak = $("#myTable" + " tr:gt("+ (index) +")").find("td:eq(1)").html();
            	var name = $("#myTable" + " tr:gt("+(index)+")").find("td:eq(2)").html()
            	var tampStr = "("+hak+", "+name+") ";
            	
            	console.log($("#myTable" + " tr:gt("+ index +")").find("td:eq(1)").html());
            	
            	
            	document.getElementById('messegeUser').value += tampStr;
            	
            	tampStr = "";
            });
        }
        else
        {
            $(":checkBox", tbl).removeAttr("checked");
            
            document.getElementById('messegeUser').value = "";
        }
        // 모든 체크박스에 change 이벤트 발생시키기               
        $(":checkBox", tbl).trigger("change");
    });
		    
	// 헤더에 있는 체크박스외 다른 체크박스 클릭시
    $(":checkBox:not(:first)", tbl).click(function(){
        var allCnt = $(":checkBox:not(:first)", tbl).length;
        var checkedCnt = $(":checkBox:not(:first)", tbl).filter(":checked").length;
         
        // 전체 체크박스 갯수와 현재 체크된 체크박스 갯수를 비교해서 헤더에 있는 체크박스 체크할지 말지 판단
        if(allCnt!=checkedCnt)
        {
        	$(":checkBox:first", tbl).prop( "checked", false);
        }
        else
        {
        	$(":checkBox:first", tbl).attr("checked", "checked");
        }
    });
	
	var messageSend = "${messageSend}";
		
		if(messageSend == "Ok")
			{
			console.log("출력되나용");
			document.getElementById('submitMessage').style.display='block';
			}
	
	});
	
	

	function check(val, checkBox)
	{
		if (checkBox.checked == true)
		{
			var table = document.getElementById('myTable');
	        for (var r = 0, n = table.rows.length; r < n; r++) 
	        {
	            for (var c = 0, m = table.rows[r].cells.length; c < m; c++) 
	            {
	                var value = table.rows[val].cells[2].innerHTML;
	                var hak = table.rows[val].cells[1].innerHTML;
	            }
	        }
	        document.getElementById('messegeUser').value += "("+ hak + ", " + value + ") ";
		}
		else
		{		
			var tempStr = document.getElementById('messegeUser').value;
			
			var table = document.getElementById('myTable');
	        for (var r = 0, n = table.rows.length; r < n; r++) 
	        {
	            for (var c = 0, m = table.rows[r].cells.length; c < m; c++) 
	            {
	                var value = table.rows[val].cells[2].innerHTML;
	                var hak = table.rows[val].cells[1].innerHTML;
				}
	        }
	        
	        var checkUser = "("+ hak + ", " + value + ") ";
	        
	        document.getElementById('messegeUser').value = tempStr.replace(checkUser, "");
        }
	}
</script>


<%@ include file = "./include/footer.jsp" %>
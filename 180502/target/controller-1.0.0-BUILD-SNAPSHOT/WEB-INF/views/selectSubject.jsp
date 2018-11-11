<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	var success = '${success}';
	if(success == "ok")
	{
		alert("설정 되었습니다.");
		self.location="/main";
	}
	

$(document).ready(function()
		{
			$("#myInput").on("keyup", function() 
			{
			    var value = $(this).val().toLowerCase();
			    $("#subject option").filter(function() 
			    {
		      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    	});
		  	});
		});
		
		

function addItem()
   {
	var target = document.getElementById("subject");
	var targetText = (target.options[target.selectedIndex].text);
	var targetNum = (target.options[target.selectedIndex].value);
	
	var selectSubject = document.getElementById("selectSubject");
    
    	var op = new Option();
    	
    	for (i = target.length - 1; i>=0; i--) 
		{
			   if (target.options[i].selected) 
			   {
				   var op = new Option();
				   
				   op.value = target.options[i].value;
			        op.text = target.options[i].text;
			        
			        selectSubject.options.add(op);
			        $('#select_Subject').append("<input type='hidden' name='selectSubject" + i + "' value='" + op.value + "'>");
			        
			        target.remove(i);
			   }
		}
        
        
   } 
   
   function removeItem()
   {
	   var target = document.getElementById("selectSubject");
		var targetText = (target.options[target.selectedIndex].text);
		var targetNum = (target.options[target.selectedIndex].value);
		
		var selectSubject = document.getElementById("subject");
	    
		for (i = target.length - 1; i>=0; i--) 
		{
			   if (target.options[i].selected) 
			   {
				   var op = new Option();
				   
				   op.value = target.options[i].value;
			        op.text = target.options[i].text;
			        
			        selectSubject.options.add(op);
			        
			        target.remove(i);
			   }
		}  
   }
</script>
<form action="selectSubject" method="POST" id="select_Subject">
<div class="w3-main" style="margin-left:400px; margin-bottom:3%; margin-top:3%">
 		<div class="w3-row w3-padding-64">
			<div class="w3-twothird w3-container">
			<h1 class="w3-text-teal">과목 선택</h1>
			<table class="w3-table" width="100%">
				<tr>
					<td width="45%"><input class="w3-input w3-border w3-padding" type="text" placeholder="검색하기" id="myInput" onkeyup="myFunction()" style="margin-bottom: 7px"></td>
					<td colspan="2">　</td>
				</tr>
				<tr>
					<td rowspan="2">
<select class="w3-select w3-border" name="subject" id="subject" size="10" multiple style="overflow-y: auto;">
    	<c:forEach items="${selectSubject }" varStatus="cnt" var="vo">
        <option value="<c:out value='${vo.YUHAN_SUBJECT_NUMBER }'/>">${vo.YUHAN_SUBJECT_NAME }</option>
    	</c:forEach>
    	
    </select>
					</td>
					<td width="10%" style="margin:auto;" class="w3-display-container">
						<input type="button" onClick="addItem()" style="margin:auto; width:100%; margin-top:80%"  class="w3-button w3-teal w3-padding-large" value="ADD">
						<input type="button" onClick="removeItem()" style="margin:auto; width:100%; margin-top:7%" class="w3-button w3-teal w3-padding-large" value="LEFT">
					</td>
					<td rowspan="2" width="45%">
<select class="w3-select w3-border" name="selectSubject" id="selectSubject" size="10" multiple style="overflow-y: auto;">
    </select>
					</td>
				</tr>
			</table>
			<input type="submit" id="submitBtn" class="w3-right w3-btn w3-teal w3-padding-large" value="완료">
			</div>
			</div>
			</div>
			</form>
			
<%@ include file = "./include/footer.jsp" %>
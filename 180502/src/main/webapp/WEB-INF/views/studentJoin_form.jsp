<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">

	var joinCheck = '${joinCheck}';
	if(joinCheck == "Success")
	{
		alert(joinCheck);
		self.location="/main";
	}
	
	
	var checked = 0;
	/*******************************************/
	
	function sendIt(){
	 
			
		
			if(f.memberID.value.toString() ==""){
				alert("아이디를 입력하지 않았습니다.")
				document.f.memberID.focus()
		         return false;
			}
	   
			 for (i = 0; i < document.f.memberID.value.length; i++) {
		            ch = document.f.memberID.value.charAt(i)
		            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
		                alert("아이디는 대소문자, 숫자만 입력가능합니다. 특수문자 공백은 사용불가능합니다.")
		                document.f.memberID.focus()
		                document.f.memberID.select()
		                return false;
		            }
		        }
			 
			 if (document.f.memberID.value.length<4 || document.f.memberID.value.length>12) {
		            alert("아이디를 4~12자까지 입력해주세요.")
		            document.f.memberID.focus()
		            document.f.memberID.select()
		            return false;
		        }
			 
			 if (document.f.memberID.value.indexOf(" ") >= 0) {
		            alert("아이디에 공백을 사용할 수 없습니다.")
		            document.f.memberID.focus()
		            document.f.memberID.select()
		            return false;
		        }
			 
			 if (document.f.memberPW.value == "") {
		            alert("비밀번호를 입력하지 않았습니다.")
		            document.f.memberPW.focus()
		            return false;
		        }
	      
			 if (f.memberPW.value == f.memberID.value) {
		            alert("아이디와 비밀번호가 같습니다. [보안 위험]")
		            document.f.memberPW.focus()
		            return false;
		        }
			 
			  if (document.f.memberPW.value != document.f.memberPWch.value) {
		            alert("비밀번호가 일치하지 않습니다")
		            document.f.memberPWch.value = ""
		            document.f.memberPWch.focus()
		            return false;
		        }
			  
			  if (document.f.memberHak.value == "") {
		            alert("학번을 입력하지 않았습니다.")
		            document.f.memberHak.focus()
		            return false;
		        }
			  
			  if (document.f.memberName.value == "") {
		            alert("이름을 입력하지 않았습니다.")
		            document.f.memberName.focus()
		            return false;
		        }
			  
			  for (i = 0; i < document.f.memberName.value.length; i++) {
		            //var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		            ch = document.f.memberName.value.charAt(i)
		            if (!(ch >= '가' && ch <= '힣')) {
		                alert("이름에는 한글만 입력가능합니다.")
		                document.f.memberName.focus()
		                document.f.memberName.select()
		                return false;
		            }
		        }
			  
			  if(checked == 0){
					 alert("아이디 중복확인을 눌러주세요.")
					return false;
				}
	}
	
	</script>
	
	
	<script type="text/javascript">
	 	$(document).ready(function(){
	 		
	 		
	        $("#checkid").on("click", function(){
	        	var memberID = $("#memberID").val();
	        	
	            $.ajax({
	                type: "POST",
	                url: "/checkid/" + memberID,
	                dataType:"json",
	                success: function(data){
	                	//alert(data.result);
	                    if($.trim(data.result) == 0){
	                    	alert("사용이 가능한 아이디 입니다.");
	                    	checked = 1;
	                    	  $('#checkMsg').html('<label style="color:blue">사용가능</label>');
	                    	}
	                    else{
	                    	alert("사용이 불가능한 아이디 입니다.");
	                    	 document.f.memberID.focus()
	     		            document.f.memberID.select()
	     		           $('#checkMsg').html('<label style="color:red">사용불가능</label>');
	                    }
	                },
	                error : function(){
	                	alert("실패");
	                }
	            });    //end ajax    
	        });    //end on    
	        
	    });
	</script>
<style>
	.s{ font-size : 8pt;
		color : grey;}
</style>
<form name="f" action="studentJoin_form" method="POST" onsubmit="return sendIt();">
<!-- 학과 공지사항 -->
	<div class="w3-main">
 		<div class="w3-row" style="text-align: center; margin-top:7%; margin-bottom:7%">
			<div class="w3-container" style="display: inline-block; margin-left:auto; margin-right:auto">
				<h1 class="w3-text-teal"><b>JOIN</b> </h1>
				<table class="w3-table w3-center" align="center">
				<tr>
					<th style="text-align: center">ID</th>
					<th><input type="text" id ="memberID" name="memberID">
						<input type="button" id="checkid"  value = "중복확인" class="w3-button w3-teal"> 
						<div id="checkMsg">
						
						</div><label class="s" >아이디는 대소문자, 숫자만 입력가능합니다.(4~12 글자)</label>
					</th>
				</tr>
				<tr>
					<th style="text-align: center">Password</th>
					<th><input type="password" id="memberPW" name="memberPW"></th>
				</tr>
				<tr>
					<th style="text-align: center">Password Check</th>
					<th><input type="password"  id="memberPWch"></th>
				</tr>
				<tr>
					<th style="text-align: center">학번</th>
					<th><input type="number" id="memberHak" name="memberHak"></th>
				</tr>
				<tr>
					<th style="text-align: center">이름</th>
					<th><input type="text" id="memberName" name="memberName"></th>
				</tr>
				<tr>
					<th style="text-align: center">학년</th>
					<th><select name="memberGrade" style="width:175px">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option></select></th>
				</tr>
				<tr>
					<th style="text-align: center">반</th>
					<th><select name="memberClass" style="width:175px">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option></select></th>
				</tr>
				<tr>
					<td colspan="2">
					<!-- <button type="submit" class="w3-button w3-teal" style="width: 100%;"  onclick="sendIt()">가입하기</button> -->
					<input type="submit" class="w3-button w3-teal" style="width: 100%;" value="가입하기"
			            onclick="sendit()"> 
					</td>
				</tr>
			</table>
	     	</div>
    	</div>
  	</div>
</form>
<%@ include file = "./include/footer.jsp" %>
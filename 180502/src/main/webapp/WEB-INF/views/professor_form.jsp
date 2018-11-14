<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
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
	 
			
		
			if(f.proID.value.toString() ==""){
				alert("아이디를 입력하지 않았습니다.")
				document.f.proID.focus()
		         return false;
			}
	   
			 for (i = 0; i < document.f.proID.value.length; i++) {
		            ch = document.f.proID.value.charAt(i)
		            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z')&&!(ch >= 'A' && ch <= 'Z')) {
		                alert("아이디는 대소문자, 숫자만 입력가능합니다. 특수문자 공백은 사용불가능합니다.")
		                document.f.proID.focus()
		                document.f.proID.select()
		                return false;
		            }
		        }
			 
			 if (document.f.proID.value.length<4 || document.f.proID.value.length>12) {
		            alert("아이디를 4~12자까지 입력해주세요.")
		            document.f.proID.focus()
		            document.f.proID.select()
		            return false;
		        }
			 
			 if (document.f.proID.value.indexOf(" ") >= 0) {
		            alert("아이디에 공백을 사용할 수 없습니다.")
		            document.f.proID.focus()
		            document.f.proID.select()
		            return false;
		        }
			 
			 if (document.f.proPW.value == "") {
		            alert("비밀번호를 입력하지 않았습니다.")
		            document.f.proPW.focus()
		            return false;
		        }
	      
			 if (f.proPW.value == f.proID.value) {
		            alert("아이디와 비밀번호가 같습니다. [보안 위험]")
		            document.f.proPW.focus()
		            return false;
		        }
			 
			  if (document.f.proPW.value != document.f.proPWch.value) {
		            alert("비밀번호가 일치하지 않습니다")
		            document.f.proPWch.value = ""
		            document.f.proPWch.focus()
		            return false;
		        }

			  if (document.f.proName.value == "") {
		            alert("이름을 입력하지 않았습니다.")
		            document.f.proName.focus()
		            return false;
		        }
			  
			  for (i = 0; i < document.f.proName.value.length; i++) {
		            //var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		            ch = document.f.proName.value.charAt(i)
		            if (!(ch >= '가' && ch <= '힣')) {
		                alert("이름에는 한글만 입력가능합니다.")
		                document.f.proName.focus()
		                document.f.proName.select()
		                return false;
		            }
		        }
			  
			  if (document.f.proEmail.value == "") {
		            alert("이메일을 입력하지 않았습니다.")
		            document.f.proEmail.focus()
		            return false;
		        }
			  
				var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;   
				  
				if(regex.test(proEmail) === false) {  
				    alert("잘못된 이메일 형식입니다.");  
				    return false;
				}

				if(checked == 0){
					 alert("아이디 중복확인을 눌러주세요.")
					return false;
				}
				
			

			
	}
	
	</script>
	
	<script type="text/javascript" src="/resources/plugins/jQuery/jQuery-2.1.4.min.js">
	</script>
	
	
	
	<script type="text/javascript">

 	 
 	$(document).ready(function(){
        $('#checkidP').on('click', function(){
            $.ajax({
                type: 'POST',
                url: '/checkidP',
                data: {
                    "proID" : $('#proID').val()
                },
                success: function(data){
                    if($.trim(data) == 0){
                    	alert("사용이 가능한 아이디 입니다.");
                    	checked = 1;
                    	  $('#checkMsg').html('<label style="color:blue">사용가능</label>');
                    	}
                    else{
                    	alert("사용이 불가능한 아이디 입니다.");
                    	 document.f.proID.focus()
     		            document.f.proID.select()
     		           $('#checkMsg').html('<label style="color:red">불사용가능</label>');
                    }
                }
            });    //end ajax    
        });    //end on    
    });



</script>

<style>
	.s{ font-size : 8pt;
		color : grey;}
		

</style>
<form name="f" action="professor_form" method="POST" onsubmit="return sendIt();">

  

<!-- 학과 공지사항 -->
	<div class="w3-main">
 		<div class="w3-row" style="text-align: center; margin-top:7%; margin-bottom:7%">
			<div class="w3-container" style="display: inline-block; margin-left:auto; margin-right:auto">
				<h1 class="w3-text-teal">PROFESSOR JOIN </h1>
				<table class="w3-table w3-center" align="center">
	<tr>
		<th style="text-align: center">ID</th>
		<th>
			<input type="text"  name="proID" id= "proID">
			<input type="button" id="checkidP"  value = "중복확인" class="w3-button w3-teal"> 
			<div id="checkMsg">
			
			</div><label class="s" >아이디는 대소문자, 숫자만 입력가능합니다.(4~12 글자)</label>
			
			
		</th>
	</tr>
	<tr>
		<th style="text-align: center">Password</th>
		<th><input type="password" name="proPW" id="proPW"></th>
	</tr>
	<tr>
		<th style="text-align: center">Password Check</th>
		<th><input type="password" id="proPWch"></th>
	</tr>
	<tr>
		<th style="text-align: center">ProNum</th>
		<th><input type="text" name="proNo" id="proNo"></th>
	</tr>
	<tr>
		<th style="text-align: center">Name</th>
		<th><input type="text" name="proName" id="proName"></th>
	</tr>
	<tr>
		<th style="text-align: center">e-mail</th>
		<th><input type="email" name="proEmail" id="proEmil"></th>
	</tr>

	<tr>
		<td colspan="2">
			<input type="submit" class="w3-button w3-teal" style="width: 100%;" value="가입하기"
            onclick="sendit()"/> 
		</td>
	</tr>
</table>
	     	</div>
	     	
    	</div>
  	</div>



</form>
<%@ include file = "./include/footer.jsp" %>
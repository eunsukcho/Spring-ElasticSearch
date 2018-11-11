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
</script>
<form action="professor_form" method="POST">

  

<!-- 학과 공지사항 -->
	<div class="w3-main">
 		<div class="w3-row" style="text-align: center; margin-top:7%; margin-bottom:7%">
			<div class="w3-container" style="display: inline-block; margin-left:auto; margin-right:auto">
				<h1 class="w3-text-teal">PROFESSOR JOIN </h1>
				<table class="w3-table w3-center" align="center">
	<tr>
		<th style="text-align: center">ID</th>
		<th><input type="text"  name="proID"></th>
	</tr>
	<tr>
		<th style="text-align: center">Password</th>
		<th><input type="password" name="ProPW"></th>
	</tr>
	<tr>
		<th style="text-align: center">Password Check</th>
		<th><input type="password"></th>
	</tr>
	<tr>
		<th style="text-align: center">Name</th>
		<th><input type="text" name="proName"></th>
	</tr>
	<tr>
		<th style="text-align: center">e-mail</th>
		<th><input type="number" name="proEmail"></th>
	</tr>

	<tr>
		<td colspan="2"><button type="submit" class="w3-button w3-teal" style="width: 100%;">가입하기</button></td>
	</tr>
</table>
	     	</div>
	     	
    	</div>
  	</div>



</form>
<%@ include file = "./include/footer.jsp" %>
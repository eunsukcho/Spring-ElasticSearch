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
<form action="studentJoin_form" method="POST">
<!-- 학과 공지사항 -->
	<div class="w3-main">
 		<div class="w3-row" style="text-align: center; margin-top:7%; margin-bottom:7%">
			<div class="w3-container" style="display: inline-block; margin-left:auto; margin-right:auto">
				<h1 class="w3-text-teal">JOIN </h1>
				<table class="w3-table w3-center" align="center">
					<tr>
						<th style="text-align: center">ID</th>
						<th><input type="text"  name="memberID"></th>
					</tr>
					<tr>
						<th style="text-align: center">Password</th>
						<th><input type="password" name="memberPW"></th>
					</tr>
					<tr>
						<th style="text-align: center">Password Check</th>
						<th><input type="password"></th>
					</tr>
					<tr>
						<th style="text-align: center">학번</th>
						<th><input type="number" name="memberHak"></th>
					</tr>
					<tr>
						<th style="text-align: center">이름</th>
						<th><input type="text" name="memberName"></th>
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
						<td colspan="2"><button type="submit" class="w3-button w3-teal" style="width: 100%;">가입하기</button></td>
					</tr>
				</table>
	     	</div>
    	</div>
  	</div>
</form>
<%@ include file = "./include/footer.jsp" %>
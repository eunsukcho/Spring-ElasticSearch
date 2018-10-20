<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		window.onload= function(){
			var loginCheck = '${loginCheck}';
			var joinCheck = '${joinCheck}';
			var IDPWCheck = '${IDPWCheck}';
			if(loginCheck == "Fail")
			{
				alert("로그인 실패하였습니다.");
				self.location="/header";
			}
			else(loginCheck == "Success")
			{
				var id = '${id}';
				
				if(joinCheck == "joinNo")
				{
					self.location="/studentJoin?id=" + id;
					
					return;
				}
			}
			if(IDPWCheck == "Ok")
			{
				self.location="/main";
			}
			else if(IDPWCheck == "No")
			{
				alert("ID, PW 체크해주세요.");
				self.location="/header";
			}
		}
	</script>
</head>
<body>
</body>
</html>
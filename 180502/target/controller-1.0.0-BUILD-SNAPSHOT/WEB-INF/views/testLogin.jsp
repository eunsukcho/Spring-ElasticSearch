<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/testLogin" method="POST">
	아이디 : <input type = "text" name = "memberID">
	비빌번호 : <input type = "text" name = "memberPW">
	<div>
		학생<input type="checkbox" name = "rate" value="s">
		교수<input type="checkbox" name = "rate" value="p">
	</div>
	<input type = "submit" value = "로그인">
</form>
</body>
</html>
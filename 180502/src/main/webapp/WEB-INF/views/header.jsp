<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var loginCheck = '${loginCheck}';

if(loginCheck == "Fail")
	{
		alert("로그인 실패");
	}
	
	var sessionCheck = '${sessionCheck}';
	
	if(sessionCheck == "LoginOk")
		{
			self.location="/main";
		}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
	body,h1 {font-family: "Raleway", sans-serif}
	body, html {height: 100%}
	.bgimg 
	{
	    background-image: url('./resources/images/bgimg.png');
	    min-height: 100%;
	    background-position: center;
	    background-size: cover;
	}
</style>
</head>
<body>
	<div class="bgimg w3-display-container w3-animate-opacity w3-text-white">
	<div class="w3-display-topleft w3-padding-large w3-xlarge">
		<img src="./resources/images/logo/yuhanlogo.png">
	</div>
	<div class="w3-display-middle">
		<h1 class="w3-jumbo w3-animate-top">IT 종합 정보 시스템</h1>
			<hr class="w3-border-grey" style="margin:auto;width:40%">
			<div class="w3-container w3-teal">
  				<h2>Sign In</h2>
			</div>
			<div class="w3-container w3-light-grey">
			<form class="w3-container" action="/login" method="post">
  			<p>
  				<input class="w3-input" type="text" placeholder="ID" name="id"></p>
  			<p>
			  <input class="w3-input" type="password" placeholder="Password" name="password"></p>
			
			<p>
				<input type="submit" class="w3-btn w3-round-large w3-teal" value="login" style="width:100%">
			</p>
			</form>
			<p>
				<span class="w3-right w3-padding w3-hide-small"><a href="#">Sign up here</a></span>
			</p>
			</div>
	</div>
	<div class="w3-display-bottomleft w3-padding-large">
		이진주 조은숙 김윤지 안제온 <a href="http://www.yuhan.ac.kr" target="_blank">IT Software</a>
	</div>
	</div>
</body>
</html>
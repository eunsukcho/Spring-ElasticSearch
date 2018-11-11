<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	body 
	{
   		background-image: url("resources/images/contact/contactBg.jpg");
   		background-repeat: no-repeat;
   		background-attachment: fixed;
   		width:100%;
	}
	
	#contactBody
	{
		background-color: white;
	}
	
	#stringContact
	{
		font-size: 50pt;
		color: white;
		margin-left: 2%;
		margin-bottom: 2%;
	}
	
	
</style>
<script>

function myMap() {
var mapProp= {
    center:new google.maps.LatLng(37.487342,126.821466),
    zoom:17,
};
var uluru = {lat: 37.487342, lng: 126.821466};
var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);
var marker = new google.maps.Marker({position: uluru, map: map});
var content = "Yuhan IT Software"; // 말풍선 안에 들어갈 내용

// 마커를 클릭했을 때의 이벤트
var infowindow = new google.maps.InfoWindow({content: content});
infowindow.open(map,marker);
}
</script>

<div class="w3-container w3-display-container w3-grayscale" style="height:500px">
	<div class="w3-display-bottomleft" id="stringContact"><b>Contact US</b></div>
</div>





<div class="w3-row" style="background-color: white;">
  <div class="w3-col w3-container" style="width:55%; margin-top: 20px; margin-bottom: 20px">
	<div class="w3-container" id="contactBody">
<form action="contact" method="post" id="formBg">
	<div style="margin-left:7%">
<label style="margin-bottom: 7px">Your Email *</label>
<input type="text" class="w3-input w3-border" name="setfrom">
<label style="margin-bottom: 7px">Contact Title *</label>
<input type="text" class="w3-input w3-border" name="title">
<label style="margin-bottom: 7px">Contact Content</label>
<textarea class="w3-input w3-border" rows="8" cols="5" name="content"></textarea>
<input type="submit" class="w3-right w3-button w3-wide w3-teal w3-padding-large" value="Send" style="margin-top:7px">
	</div>
</form>
</div>
  </div>
  <div class="w3-col w3-container" style="width:45%;  margin-top: 20px; margin-bottom: 20px">
  	<div id="googleMap" style="width:80%; height:400px;"></div>
  </div>
</div>
<script src="http://maps.google.com/maps/api/js?key=AIzaSyDcwAwH2w1vk37wUEKrk5Bsj_nOGsFgL08&callback=myMap"></script>

<%@ include file = "./include/footer.jsp" %>
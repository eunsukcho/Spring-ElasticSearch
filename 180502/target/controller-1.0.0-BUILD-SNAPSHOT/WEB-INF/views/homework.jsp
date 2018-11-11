<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>

<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-container">
          <p><h1>ASP.NET</h1></p>
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>장인주</p>
          <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>ex@mail.com</p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>5 4 0 5</p>
          <p><i class="fa fa-asterisk fa-fw w3-margin-right w3-large w3-text-teal"></i>3</p>
          
          <hr>
          
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Notice</a></p>
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Homework</a></p>
          <p><i class="fa fa-circle fa-fw w3-margin-right w3-large w3-text-teal"></i><a href="#">Grades</a></p>
         </div>
        </div>
        </div>
        
        <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Homework</h2>
        <div class="w3-container">
        	<div>
          <table class="w3-table w3-bordered">
		    <tr>
		      <td colspan="2"><span style="font-size: small;">제목</span> | 기말고사 대체 시험 [1]</td>
		      <td><span style="font-size: small;">제출</span> | O</td>
		    </tr>
		    <tr>
		    	<td colspan="2"><span style="font-size: small;">제출기간</span> | 06.22 13:00 - 06.24 23:59</td>
		    </tr>
		    <tr>
		    	<td colspan="2">기말 프로젝트 제출하세유~</td>
		    </tr>
		  </table>
		  </div>
		  <div class="w3-panel w3-border w3-border-meal">
		  	<p>제출 파일이 없습니다.</p>
		  </div>
		  <div class="w3-right w3-container">
		  	<p><button class="w3-button w3-teal">제출하기</button></p>
		  </div>
		  <hr>
		  <div class="w3-container">
		  	<p>댓글</p>
		  	<p><b>교수님</b> : 아쥬 훌륭해요 ^^</p>
		  	<div class="w3-container">
		  		<div class="w3-left">
		  		<textarea class="w3-input w3-border" cols="95"></textarea>
		  		</div>
		  		<div class="w3-right">
		  		<button class="w3-button w3-teal">작성</button>
		  		</div>
		  	</div>
		  </div>
		   <div class="w3-container">
		    <p><button class="w3-button w3-teal">List</button>
		   	<button class="w3-button w3-right w3-teal">Delete</button>
		   	<button class="w3-button w3-right w3-teal">Update</button></p>
		   </div>
        </div>
        
      </div>
      
      </div>
        </div>
        </div>

<%@ include file = "./include/footer.jsp" %>
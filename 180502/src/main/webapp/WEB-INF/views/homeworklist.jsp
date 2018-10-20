<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>

<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px; height:70%">

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
        <hr>
        <div class="w3-container">
          <table class="w3-table-all w3-padding">
		    <thead>
		      <tr class="w3-teal">
		      	<th style="width:4%">Check</th>
		        <th style="width:4%">No</th>
		        <th style="width:30%">Subject</th>
		        <th class="w3-center" style="w3-right">Date</th>
		        <th class="w3-center" style="width:10%">Submit</th>
		      </tr>
		    </thead>
		    <tr>
		    	<td><input type="checkbox" style="align-items: center"></td>
		      <td>2</td>
		      <td>기말 프로젝트 제출</td>
		      <td class="w3-center">06.22 13:00 - 06.24 23:59</td>
		      <td class="w3-center">제출완료</td>
		    </tr>
		    <tr>
		    	<td style="align-items: center"><input type="checkbox"></td>
		      <td>1</td>
		      <td>마스터 페이지 응용 제출</td>
		      <td class="w3-center">06.01 00:00 - 06.22 23:59</td>
		      <td class="w3-center">-</td>
		    </tr>
		   </table>
		   <div class="w3-padding-24">
		   <button class="w3-button w3-right w3-teal">다운로드</button>
		   	<button class="w3-button w3-right w3-teal">Write</button>
		   </div>
		   <br>
        </div>
        
      </div>
      
      </div>
        </div>
        </div>

<%@ include file = "./include/footer.jsp" %>
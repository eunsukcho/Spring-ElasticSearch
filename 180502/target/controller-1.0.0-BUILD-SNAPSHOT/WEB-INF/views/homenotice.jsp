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
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-file fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Notice</h2>
        <hr>
        <div class="w3-container">
          <table class="w3-table-all w3-padding">
		    <thead>
		      <tr class="w3-teal">
		        <th>No</th>
		        <th>Subject</th>
		        <th>Writer</th>
		        <th>Date</th>
		        <th>Read</th>
		      </tr>
		    </thead>
		    <tr>
		      <td>2</td>
		      <td>ASP.NET 종강 안내</td>
		      <td>이진주</td>
		      <td>2018.06.22</td>
		      <td>3</td>
		    </tr>
		    <tr>
		      <td>1</td>
		      <td>기말고사 대체 프로젝트 공지</td>
		      <td>이진주</td>
		      <td>2018.06.01</td>
		      <td>21</td>
		    </tr>
		   </table>
		   <div class="w3-padding-24">
		   	<button class="w3-button w3-right w3-teal">Write</button>
		   </div>
		   <br>
        </div>
        
      </div>
      
      </div>
        </div>
        </div>
<%@ include file = "./include/footer.jsp" %>
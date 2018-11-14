<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
<style>
 a{ text-decoration:none;}
</style>
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
  <div class="w3-container w3-card w3-white w3-margin-bottom w3-padding">
        <h2 class="w3-text-grey w3-padding-16">회원가입</h2>
        
        <div class="w3-cell-row w3-center w3-layout-middle">
        
           <div class="w3-container w3-teal w3-cell w3-padding-24">
           <a href="studentJoin_form">
              <i class="fa fa-user fa-fw w3-margin-right w3-xxlarge w3-margin-top"></i>
             <p class="w3-wide">IT학생</p>
          </a>
           </div>
        
           <div class="w3-container w3-border w3-cell w3-padding-24">
           <a href="professor_form">
           <i class="fa fa-users fa-fw w3-margin-right w3-xxlarge w3-text-teal w3-margin-top"></i>
             <p class="w3-wide">IT 교수</p>
          </a>   
           </div>
      </div>
  </div>
</div>
</div>
</div>
<%@ include file = "./include/footer.jsp" %>
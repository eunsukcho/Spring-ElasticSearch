<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>


<html>
<style>
 a{ text-decoration:none;}
</style>

<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
  <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16">미제출 과제</h2>
        <div class="w3-container">
         <!--  <h5 class="w3-opacity"><b>[데이터베이스프로그래밍] 데이터베이스 8주차</b></h5>
          <h6 class="w3-text-teal">05.18 13:00 - 06.22 18.00</h6>
          <hr>
           <h5 class="w3-opacity"><b>[C#] C# 팀프로젝트</b></h5>
          <h6 class="w3-text-teal">06.01 10:00 - 06.24 19:00</h6> -->
          
             <c:forEach items = "${listm }" var = "vo"> 
                
                        <a href  = "hwread"><h5 class="w3-opacity"><b>[ ${vo.subject } ] ${vo.title }</b></h5></a> <P>
                        <h6 class="w3-text-teal">${vo.start } - ${vo.end }</h6>
                        <h7>등록일 : ${vo.formatToday } </h7>
                        <hr/>
            
            </c:forEach>
        </div>
      </div>
      
        <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16">새로 올라온 과제</h2>
        <div class="w3-container">
          <!-- <h5 class="w3-opacity"><b>[모바일프로그래밍(2)] 프로바이더 리졸브 과제</b></h5><label class="w3-teal">제출완료</label>
          <h6 class="w3-text-teal">05.20 13:00 - 05.27 23:59</h6> -->
          
       
             
                <c:forEach items = "${list }" var = "vo"> 
                
                     <a href  = "#">   <h5 class="w3-opacity"><b>[ ${vo.subject } ] ${vo.title }</b></h5> </a> <P>
                        <h6 class="w3-text-teal">${vo.start } - ${vo.end }</h6>
                        <h7>등록일 : ${vo.formatToday } </h7>
                        <hr/>
            
            </c:forEach>
             
        </div>
      </div>
      
    <div class="w3-container w3-card w3-white w3-margin-bottom">
       <div class="w3-container">
        <h2 class="w3-text-grey w3-padding-16" style="float:left;">최근 제출 내역</h2>
        <span style="float: right; margin-top:2%"><a href="#">더보기</a></span>
        </div>
        <div class="w3-container">
     <!--      <h5 class="w3-opacity"><b>[모바일프로그래밍(2)] 프로바이더 리졸브 과제</b></h5>
          <h6 class="w3-text-teal">05.20 13:00 - 05.27 23:59</h6>
        </div>
        <hr>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>[소프트웨어공학] 프로젝트 일정 관리 PPT</b></h5>
          <h6 class="w3-text-teal">05.13 18:00 - 05.24 10:00</h6> -->
          
          <c:forEach items = "${listc }" var = "vo"> 
                
                        <a href  = "#">   <h5 class="w3-opacity"><b>[ ${vo.subject } ] ${vo.title }</b></h5> </a> <P>
                        <h6 class="w3-text-teal">${vo.start } - ${vo.end }</h6>
                        <h7>등록일 : ${vo.formatToday } </h7>
                        <hr/>
            
            </c:forEach>
      </div>
      </div>
      </div>
      </div>
</html>
<%@ include file = "./include/footer.jsp" %>
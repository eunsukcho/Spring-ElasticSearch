<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	<style>
		a:link { text-decoration: none;}
	</style>
	
<%-- <div class="w3-main">
 		<div class="w3-row" style="text-align: center; margin-top:10%; margin-bottom:11%">
			<div class="w3-container" style="display: inline-block; margin-left:auto; margin-right:auto">
			<c:set var = "rate" value="${rate}" />
		    	<c:choose>
		    		<c:when test="${rate eq 'S' }">
		    			<h1 class="w3-text-teal">수강과목 </h1>
		    			<ul class="w3-ul w3-border">
							<c:forEach items = "${hw }" var = "vo"> 
								<li class="w3-hover-teal"><a href="/hwList?subjectID=${vo.subjectID }&selectClass=${selectClass}">${vo.subjectName }</a></li>
							</c:forEach>
						</ul>
		    		</c:when>
		    		<c:otherwise>
		    			<h1 class="w3-text-teal">담당과목 </h1>
							<c:forEach items = "${hak }" var = "hak"> 
								<div class="w3-container">${hak}학년</div>
								<c:forEach items = "${hw }" var = "vo"> 
									<c:if test="${vo.yuhan_subject_hak == hak}">
										<div>${vo.subjectName }</div>
										<c:forEach items = "${subjectClass }" var = "subjectClass">
											<c:if test="${subjectClass.subjectName == vo.subjectName}">
												<div><a href = "/hwList?subjectID=${subjectClass.subjectID }&selectClass=${subjectClass.memberClass }">${subjectClass.memberClass }</a></div>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
							</c:forEach>
		    		</c:otherwise>
		    	</c:choose>
				
				
			</div>
		</div>
</div> --%>
    <!-- <tr class="w3-text-black">
      <td>ASP.NET</td>
      <td></td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
  -->
   
    <c:set var = "rate" value="${rate}" />
		    	<c:choose>
		    		<c:when test="${rate eq 'S' }">
		    		<div class="w3-container w3-center">
					<div style="margin-top:3%; margin-bottom:3%">
					<h1 style="margin:auto;"><p class="w3-text-teal">강의실 목록</p></h1>
		    		<table class="w3-table-all w3-center" style="width:18%; margin:auto">
		    		  <thead>
						      <tr class="w3-teal w3-padding-24">
						        <th width="40%" class="w3-center">과목명</th>
						        <th class="w3-right">강의실</th>
						      </tr>
					 </thead>
							<c:forEach items = "${hw }" var = "vo"> 
								<tr>
								<td class="w3-center">
									<b>${vo.subjectName }</b>
								</td>
								<td class="w3-right">
								<a href="/hwList?subjectID=${vo.subjectID }&selectClass=${selectClass}">
									<button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button>
								</a>
								</td>
								</tr>
							</c:forEach>
						 </table>
						 </div>
						 </div>
						 <%@ include file = "./include/footer.jsp" %>
		    		</c:when>
		    		
		    		<c:otherwise>
		    		<div class="w3-container w3-center">
		<div style="margin-top:3%; margin-bottom:3%">
		<h1 style="margin:auto;"><p class="w3-text-teal">강의실 목록</p></h1>
						<div style="/* border: 1px solid #48BAE4;  height: auto;*/">
								<c:forEach items = "${hak }" var = "hak"> 
								
								<h5 class="w3-opacity">${hak}학년</h5>
								
								
								<c:forEach items = "${hw }" var = "vo"> 
								
									<c:if test="${vo.yuhan_subject_hak == hak}">
										<div style="border: 1px solid black; /* height: auto; */ padding:10px; width : 300px; text-align: center; margin:auto;">
										<h6 class="w3-text-teal">${vo.subjectName }</h6>
										
										<c:forEach items = "${subjectClass }" var = "subjectClass">
										
											<c:if test="${subjectClass.subjectName == vo.subjectName}">
												<a href = "/hwList?subjectID=${subjectClass.subjectID }&selectClass=${subjectClass.memberClass }">${subjectClass.memberClass }</a> &nbsp;
											</c:if>
											
										
										</c:forEach>
									</div>
									<br>
									</c:if>
								</c:forEach>
							</c:forEach>
						</div>
						  </div>
						  </div>
						<%@ include file = "./include/footer.jsp" %>
						

						
		    		</c:otherwise>
		    	</c:choose>


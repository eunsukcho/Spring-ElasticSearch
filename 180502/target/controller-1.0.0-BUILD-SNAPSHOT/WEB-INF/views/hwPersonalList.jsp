<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	<style>
		a:link { text-decoration: none;}
	</style>
	
<div class="w3-main">
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
</div>
<%@ include file = "./include/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script>
	var noFromUser = "${noFromUser}";
	
	if(noFromUser == "No")
		{
			alert("잘못된 접근입니다.");
			self.location("/messageHome");
		}
	
	function sendBtn_onClick()
	{
		var messageTo = "${messageTo}";
		self.location="/messageSend?messageTo="+messageTo;
	}
</script>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  <div class="w3-container w3-card w3-white w3-margin-bottom">
  <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Message</h2>
  <div class="w3-row w3-container" style="margin-bottom: 7px">
  <table class="w3-table w3-border" width="100%">
				<c:forEach items="${messageList }" varStatus="cnt" var="vo">
				<tr>
					<td><b>보낸 사람</b>&nbsp;&nbsp;|&nbsp;&nbsp;${vo.YUHAN_MESSAGE_FROM_MEMBER_NUMBER }</td>
				</tr>
				<tr>
					<td><b>보낸 시간</b>&nbsp;&nbsp;|&nbsp;&nbsp;<fmt:formatDate value="${vo.YUHAN_MESSAGE_SEND_DATE}" pattern="yyyy/MM/dd [HH:mm:ss]"/></td>
				</tr>
				<tr style="margin-bottom: 50%">
					<td>${vo.YUHAN_MESSAGE_CONTENT }</td>
				</tr>
				</c:forEach>
				</table>
				<div class="w3-container" style="margin-bottom:7px; margin-top:20px">
				<a href="/messageHome"><button type="button" class="w3-btn w3-white w3-left w3-border" style="width:120px; margin-right:7px" id="cancelBtn">목록으로</button></a>
       		<button type="button" class="w3-btn w3-teal w3-right" style="width:120px" onClick="sendBtn_onClick()">답장 &nbsp; ❯</button>
       		<a href="/messageDelete"><button type="button" class="w3-btn w3-gray w3-right" style="width:120px; margin-right:7px" id="cancelBtn">삭제하기</button></a>
		   	
        	</div>
  </div>
  </div>
  </div>
  </div>



<%@ include file = "./include/footer.jsp" %>
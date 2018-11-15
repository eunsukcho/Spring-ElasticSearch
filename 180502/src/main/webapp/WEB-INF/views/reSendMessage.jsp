<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "./include/navbar.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
		$(document).ready(function()
		{
			var messageSend = "${messageSend}";
			
			if(messageSend == "Ok")
			{
				console.log("출력되나용");
				document.getElementById('submitMessage').style.display='block';
			}
		});
</script>
<form action="/reSendMessage" method="post" name="messageSend">
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding" style="margin-top:4%; margin-bottom:4%">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-code fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Send Message</h2>
        <div class="w3-row w3-container">
        <c:forEach items="${sendUser }" var="list">
        <input class="w3-input w3-border" type="text" style="margin-bottom: 7px; background-color: #EAEAEA" id="messegeUser" name="YUHAN_MESSAGE_TO_MEMBER_NUMBER" value="(${list.memberHak}, ${list.memberName})" readonly>
        <input type="hidden" name="messageTo" value="${list.memberID }">
        </c:forEach>	
        	<input class="w3-input w3-border" type="text" style="margin-bottom: 7px" name="YUHAN_MESSAGE_SUBJECT" placeholder="Insert Message Subject...">
        	<textarea rows="10" class="w3-input w3-border" style="resize:none; margin-bottom: 18px;" name="YUHAN_MESSAGE_CONTENT"></textarea>
        	</div>
        </div>
        <div class="w3-container" style="margin-bottom:7px; margin-top:20px">
       		<button type="submit" class="w3-btn w3-teal w3-right" style="width:120px">Send &nbsp; ❯</button>
       		<button type="button" class="w3-btn w3-gray w3-right" style="width:120px; margin-right:7px" id="cancelBtn">Cancel</button>
        	</div>
      </div>
</div>

<div id="submitMessage" class="w3-modal" style="display: none;">
  <div class="w3-modal-content w3-animate-zoom" style="padding:32px">
    <div class="w3-container w3-white w3-center">
      <h2 class="w3-wide">Success Send Message</h2>
      <p>쪽지를 성공적으로 보냈습니다.</p>
      <a href="/messageHome"><button type="button" class="w3-button w3-padding-large w3-teal w3-margin-bottom" onclick="document.getElementById('submitMessage').style.display='none'">돌아가기</button></a>
    </div>
  </div>
</div>
</form>
<%@ include file = "./include/footer.jsp" %>
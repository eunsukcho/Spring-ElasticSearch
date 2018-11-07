<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <script>
    	var deleteCheck = '${delete}';
    	
    	if(deleteCheck == "Ok")
    		{
    			alert("전송 취소 되었습니다.");
    			
    			self.location="/messageHome";
    		}
    </script>
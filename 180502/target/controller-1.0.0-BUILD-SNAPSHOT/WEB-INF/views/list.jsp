<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/navbar.jsp" %>
	<div class="w3-container w3-center" style="height: 70%">
		<div style="margin-top:8%">
		<h1 style="margin:auto;">강의실 목록</h1>
	<table class="w3-table-all w3-center" style="width:50%; margin:auto">
    <thead>
      <tr class="w3-teal w3-padding-24">
        <th width="40%">과목명</th>
        <th class="w3-center">과제</th>
        <th class="w3-right">강의실</th>
      </tr>
    </thead>
    <tr class="w3-text-black">
      <td>ASP.NET</td>
      <td></td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
    <tr>
      <td>JavaFramework</td>
      <td></td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
    <tr>
      <td>데이터베이스프로그래밍</td>
      <td class="w3-center">N</td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
    <tr>
      <td>모바일프로그래밍(2)</td>
      <td class="w3-center">N</td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
    <tr>
      <td>소프트웨어공학</td>
      <td></td>
      <td class="w3-right"><button class="w3-btn w3-round-xxlarge w3-small w3-padding-small w3-teal">강의실</button></td>
    </tr>
  </table>
  </div>
</div>
<%@ include file = "./include/footer.jsp" %>
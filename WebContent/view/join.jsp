<%@page import="com.weatherFood.member.memberDAO"%>
<%@page import="com.weatherFood.member.memberBean"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
ApiKey Apikey = new ApiKey();
String title ="회원가입";
%>
<title>FProject | <%=title %></title>
<%@include file="partials/style.jsp" %>
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<div class="form-container">
        <form action="../controller/joinPro.jsp" method="post" style="display:flex; flex-direction: column;" onsubmit="return checkId()">
            <div class="form-container__email-container">
            <input class="email-container__inputEmail" type="email" placeholder="이메일을 입력해주세요!" name="id" required>
            <span class="idSearchInfo"></span>
            </div>
			
            <input type="password" placeholder="비밀번호를 입력해주세요!" name="pw" required>
            <input type="password" placeholder="비밀번호를 한번더 입력해주세요!" name="pw2" required>
            <input type="text" placeholder="이름을 입력해주세요!" name="name" required>
    <p>
		    <input type="radio" id="r1" name="gender" value="man" checked>
		    <label for="r1"><span></span>남</label>
		    <input type="radio" id="r2" name="gender" value="wom">
		    <label for="r2"><span></span>여</label>
			
        	<input type="submit" value="회원가입">
        </form>
</div>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
<script src="js/searchId.js"></script>
</body>
</html>
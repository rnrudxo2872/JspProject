<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="com.weatherFood.board.boardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="공유 글쓰기";
%>
<title>FProject | <%=title %></title>
<%

%>
<%@include file="partials/style.jsp" %>
</head>
<body>

<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<div class="insertBoard-container">
	<form action="../controller/insertBoardPro.jsp" class="insertBoard-container-form" enctype="multipart/form-data">
    <input id=title type="text" name="title" placeholder="제목을 입력해 주세요.">
    <input type="file" name="filename">
    <textarea name="content" cols="30" rows="10" placeholder="내용을 입력해 주세요."></textarea>
    <input type="submit" value="글쓰기">
    </form>
</div>

<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
<script src="js/changePageBlock.js"></script>
</body>
</html>
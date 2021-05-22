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
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("id");
if(id == null){
	%>
	<script>
		alert("세션값이 유효하지 않습니다! 로그인을 해주세요.");
		location.href = "../view/main.jsp";
	</script>
	<%
}
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
	<form action="./insertBoardAction" class="insertBoard-container-form" enctype="multipart/form-data" method="post">
    <input id=title type="text" name="title" placeholder="제목을 입력해 주세요.">
    <input type="file" name="filename">
    <input type="hidden" name="user_name" value="<%=id%>">
    <textarea name="content" cols="30" rows="10" placeholder="내용을 입력해 주세요."></textarea>
    <input type="submit" value="글쓰기">
    </form>
</div>

<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
</body>
</html>
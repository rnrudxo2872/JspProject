<%@page import="java.util.ArrayList"%>
<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="공유 게시판";
%>
<title>FProject | <%=title %></title>
<!--  
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png"></link>
-->
<link rel="stylesheet" href="style/weather.css">
<link rel="stylesheet" href="style/frame.css">
<link rel="stylesheet" href="style/map.css">
<link rel="stylesheet" href="style/board.css">
<%
ArrayList<>
%>
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>
    <main class="board-container">
        <div>
            공유게시판
        </div>
        <footer class="board-container-footer">
            <div class="board-container-footer__pagelink">
                <a href="page=1">1</a>
                <a href="page=2">2</a>
            </div>
            <form class="board-container-footer__search" action="">
                <input type="text" placeholder="여기" name="searching">
                <input type="submit" value="검색">
            </form>
            	<a class="board-container-footer__insertBoard" href="insertBoard">글쓰기</a>
        </footer>
    </main>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
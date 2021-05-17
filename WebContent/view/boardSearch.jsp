<%@page import="com.weatherFood.board.searchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
request.setCharacterEncoding("utf-8");
String searchWord = request.getParameter("search");
//int searchType = Integer.parseInt(request.getParameter("type"));
String title = "검색결과";

searchBean searchObj = new searchBean();
searchObj.setSearchWord("안녕");
searchObj.setSearchType(3);

boardDAO bdao = new boardDAO();
ArrayList arr = bdao.searchBoard(searchObj, 0, 5);

%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<article>

</article>

<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
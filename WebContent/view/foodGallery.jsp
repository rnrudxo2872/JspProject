<%@page import="java.util.List"%>
<%@page import="com.weatherFood.image.scrap"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="인증 갤러리";
scrap scrapper = new scrap();
List images = scrapper.parseHTML();
%>
<title>FProject | <%=title %></title>
<%@include file="partials/style.jsp" %>
<link rel="stylesheet" href="style/gallery.css">
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<article class="food-contanier">
<%for(int i = 0; i < images.size(); i++){ %>
<img class="foodBlock" alt="음식<%=i %>" src="<%=images.get(i)%>">
<%} %>
</article>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
<script src="js/createMap.js"></script>
</body>
</html>
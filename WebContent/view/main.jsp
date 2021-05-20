<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String curUser = (String)session.getAttribute("id");

String title = "main";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FProject | <%=title %></title>
<%@include file="partials/style.jsp" %>
<link rel="stylesheet" href="style/random.css">
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<article class="App">
</article>

<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
<script type="module" src="js/main/main.js"></script>
<!-- <script type="module" src="js/randomFood/foodSelect.js"></script> -->
</body>
</html>
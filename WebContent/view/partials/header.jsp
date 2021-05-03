<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String title = request.getParameter("title");
String name = (String)session.getAttribute("id");
%>
<%@include file="style.jsp" %>
<div class="header-container">
<div>
<h1 class="pageTitle"><%=URLDecoder.decode(title,"utf-8") %></h1>
<div class="logForm">
<%if(name == null){
%>
<a href="login.jsp">로그인</a>
<%}else{%>
<span><%=name %>님</span>
<span><a href="../controller/logout.jsp">로그아웃</a></span>
<%}%>
</div>
</div>
<%@include file="nav.jsp"%>
<jsp:include page="weatherParseTest.jsp"></jsp:include>
</div>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String title = request.getParameter("title");
String name = (String)session.getAttribute("id");
%>
<div class="header-container">
<div>
<h1><%=URLDecoder.decode(title,"utf-8") %></h1>
<%if(name == null){
%>
<div><a href="login.jsp">로그인</a></div>
<%}else{%>
<span><%=name %>님</span>
<%}%>
</div>
<%@include file="nav.jsp"%>
<jsp:include page="weatherParseTest.jsp"></jsp:include>
</div>
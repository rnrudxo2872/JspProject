<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String title = request.getParameter("title");
%>
<h1><%=URLDecoder.decode(title,"utf-8") %></h1>
<jsp:include page="weatherParseTest.jsp"></jsp:include>
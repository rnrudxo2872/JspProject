<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String title = request.getParameter("title");
%>
<h1><%=title %></h1>

<jsp:include page="weatherParseTest.jsp"></jsp:include>

<%@page import="com.weatherFood.member.memberDAO"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

memberDAO mdao = new memberDAO();
boolean exist = mdao.checkId(id);

String statusAns = "";
String color = "";
if(exist){
	statusAns = "중복된 아이디가 있습니다.";
	color = "red";
}
else{
	statusAns = "사용가능한 아이디입니다!";
	color = "green";
}
%>

<root>
    <status><%=statusAns %></status>
    <status><%=color %></status>
</root>
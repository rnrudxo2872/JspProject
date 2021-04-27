<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String lat = request.getParameter("lat");
String lon = request.getParameter("lon");

System.out.print("위도 :" + lat + ", 경도 :" + lon);
%>
<h1>위도 :<%=lat %>, 경도 : <%=lon %></h1>

</body>
</html>
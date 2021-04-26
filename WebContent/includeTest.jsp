<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

%>
<c:import var="dataJson" url="https://api.openweathermap.org/data/2.5/weather?lat=120&lon=123&lang=kr&appid=9ec4f040743c8a068d8372f0f8af47d2&units=metric"/>
<c:out value="${myData}"/>
</body>
</html>
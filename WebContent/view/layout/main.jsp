<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--  
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png"></link>
-->
<link rel="stylesheet" href="../stlye/weather.css"></link>
</head>
<body>
<%
String title ="Testing"; //request.getparameter로 받을 거다.
%>

<jsp:include page="../partials/header.jsp">
<jsp:param value="<%=title %>" name="title"/>
</jsp:include>

<!-- 
here content part
 -->
 
<jsp:include page="../partials/footer.jsp"></jsp:include>
</body>
</html>
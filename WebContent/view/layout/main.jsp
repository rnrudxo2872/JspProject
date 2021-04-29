<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
String title =""; //request.getparameter로 받을 거다.
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
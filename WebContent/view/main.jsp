<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String curUser = (String)session.getAttribute("id");
/*
if(curUser == null){
	%>
	<script>
	alert("유효한 세션값이 없습니다!");
	location.href="login.jsp"; 
	</script>
	<%
}
*/

String title = "main";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>FProject | <%=title %></title>
<%@include file="partials/style.jsp" %>
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<article>
메인
</article>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
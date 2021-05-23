<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
ApiKey Apikey = new ApiKey();
String title ="로그인";
if((String)session.getAttribute("id") != null){
	%>
	<script>
		alert("잘못된 경로입니다!");
		history.back();
	</script>
	<%
}
%>
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

<div class="form-container">
        <form action="./loginAction.use" method="post">
            <input type="email" placeholder="아이디(이메일)을 입력해주세요!" name="id" required>
            <input type="password" placeholder="비밀번호를 입력해주세요!" name="pw" required>
    <p>

            <input type="submit" value="로그인">
        </form>
        <button onclick="location.href='join.use'">회원이 아니신가요?</button>
</div>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
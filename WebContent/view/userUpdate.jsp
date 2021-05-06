<%@page import="com.weatherFood.member.memberDAO"%>
<%@page import="com.weatherFood.member.memberBean"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="회원 정보수정";
String id = (String)session.getAttribute("id");
if(id == null){
	%>
	<script>
	alert("세션값이 만료되었습니다!");
	location.href = "main.jsp";
	</script>
	<%
}
memberBean mb = new memberBean();
memberDAO mdao = new memberDAO();
try{
	mb = mdao.getInfo(id);
}catch(Exception e){
	System.out.println("회원 정보 가져오기 실패!");
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
        <form action="../controller/userUpdatePro.jsp" method="post" style="display:flex; flex-direction: column;">
            <div class="form-container__email-container">
            <input class="email-container__inputEmail" type="email" value="<%=mb.getId() %>" name="id" readonly>
            <span class="idSearchInfo"></span>
            </div>
			
            <input type="password" placeholder="비밀번호를 입력해주세요!" name="pw" required>
            <input type="password" placeholder="비밀번호를 한번더 입력해주세요!" name="pw2" required>
            <input type="text" value="<%=mb.getName() %>" name="name" required>
    <p>
		    <input type="radio" id="r1" name="gender" value="man" <%if(mb.getGender().equals("man")){%>checked<%} %> readonly>
		    <label for="r1"><span></span>남</label>
		    <input type="radio" id="r2" name="gender" value="wom" <%if(mb.getGender().equals("wom")){%>checked<%} %> readonly>
		    <label for="r2"><span></span>여</label>
			
        	<input type="submit" value="회원가입">
        </form>
<div>

</div>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
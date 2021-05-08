<%@page import="com.weatherFood.member.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>탈퇴중 ...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String pw = request.getParameter("pw");
%>

<jsp:useBean id="mb" class="com.weatherFood.member.memberBean"/>
<jsp:setProperty property="*" name="mb"/>

<%
memberDAO mdao = new memberDAO();
int state = mdao.userDel(mb,pw);
if(state == -1){
	%>
	<script>
	alert("비밀번호가 다릅니다!");
	history.back();
	</script>
	<%
}else if(state==0){
	%>
	<script>
	alert("무언가 잘못 됐습니다!");
	history.back();
	</script>
	<%
}else{
	session.invalidate();
	%>
	<script>
	alert("정상적으로 처리되었습니다! 이용해 주셔서 감사합니다.")
	location.href="../view/main.jsp";
	</script>
	<%
}
%>
</body>
</html>
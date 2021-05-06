<%@page import="com.weatherFood.member.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="mb" class="com.weatherFood.member.memberBean" />
<jsp:setProperty property="*" name="mb"/>
<%
memberDAO mdao = new memberDAO();

//0 아이디 X, 1 비밀번호 틀림, 2 정상, -5 비정상
int flag = mdao.updateInfo(mb);
if(flag == 0){
	%>
	<script>
	alert("아이디가 없습니다!");
	location.href="../view/main.jsp";
	</script>
	<%
}else if(flag == 1){
	%>
	<script>
	alert("비밀번호가 다릅니다!");
	history.back();
	</script>
	<%
}else if(flag == -5){
	%>
	<script>
	alert("Something wrong!");
	location.href="../view/main.jsp";
	</script>
	<%
}else{
	%>
	<script>
	alert("수정 성공!");
	location.href="../view/main.jsp";
	</script>
	<%
}
%>

</body>
</html>
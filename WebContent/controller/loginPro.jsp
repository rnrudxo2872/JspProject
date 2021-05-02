<%@page import="com.weatherFood.member.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");

String id = request.getParameter("id") ;
String pw = request.getParameter("pw");

//파라미터 유무 확인
if(id == null || pw == null){
%>
<script type="text/javascript">
alert("정보값이 잘못됐습니다!");
history.back();
</script>
<%}%>

<jsp:useBean id="mb" class="com.weatherFood.member.memberBean" />
<jsp:setProperty property="*" name="mb"/>
<%
memberDAO mdao = new memberDAO();
int flag = mdao.loginFun(mb);

//아이디가 없다면,
if(flag == 0){
	%>
	<script>
	if(window.confirm("아이디가 없습니다! 회원가입 하시겠습니까?"))
		location.href="../view/join.jsp";
	else
		history.back();
	</script>
	<%
}else if(flag == 1){
	%>
	<script>
	alert("비밀번호가 틀렸습니다!");
	history.back();
	</script>
	<%
}else{
	session.setAttribute("id", mb.getId());
	response.sendRedirect("../view/main.jsp");
}
%>
</body>
</html>
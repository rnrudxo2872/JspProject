<%@page import="com.weatherFood.member.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join ...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");

String pw1 = request.getParameter("pw");
String pw2 = request.getParameter("pw2");
if(!pw1.equals(pw2)){%>
<script>
alert("입력된 비밀번호가 서로 다릅니다! 다시 확인해주세요");
history.back();
</script>
<%}else{%>
<jsp:useBean id="mb" class="com.weatherFood.member.memberBean" />
<jsp:setProperty property="*" name="mb"/>
<%
memberDAO mdao= new memberDAO();
boolean joinSuc = mdao.insertMember(mb);

System.out.print(joinSuc);
//아이디 중복없이 회원가입 성공했다면,
if(joinSuc){
	System.out.print("회원가입 성공!");
	response.sendRedirect("../view/login.jsp");
}else{%>
	<script>
	alert("이미 사용중인 아이디가 있습니다! 중복 체크해주세요!");
	</script>
<%
response.sendRedirect("../view/login.jsp");
}
}%>
</body>
</html>
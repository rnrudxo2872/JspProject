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
String pw = request.getParameter("password");

//파라미터 유무 확인
if(id == null || pw == null){
%>
<script type="text/javascript">
alert("정보값이 잘못됐습니다!");
history.back();
</script>
<%}



%>
</body>
</html>
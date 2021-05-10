<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="com.weatherFood.board.boardBean"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="글 수정";
%>
<title>FProject | <%=title %></title>
<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("id");
int boardNum = Integer.parseInt(request.getParameter("num"));

if(id == null){
	%>
	<script>
		alert("세션값이 유효하지 않습니다! 로그인을 해주세요.");
		location.href = "../view/main.jsp";
	</script>
	<%
}

boardBean bb = new boardBean();
boardDAO bdao = new boardDAO();
bb = bdao.getBoard(boardNum);

System.out.println(request.getParameter("filename"));
%>
<%@include file="partials/style.jsp" %>
</head>
<body>

<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<div class="insertBoard-container">
	<form action="../controller/updateBoardPro.jsp" class="insertBoard-container-form" enctype="multipart/form-data" method="post">
    <input id=title type="text" name="title" value="<%=bb.getTitle()%>">
    <div>
    <input id="filename" type="file" name="filename">
    <span id="existFile"><%if(bb.getFile() != null){%><%=bb.getFile() %><button class="insertBoard-container__dleButton" onclick="delExistFile()">❌</button><%} %></span>
    </div>
    <textarea name="content" cols="30" rows="10"><%=bb.getContent()%></textarea>
    <input type="submit" value="글쓰기">
    <input type="hidden" value="<%=boardNum %>" name="num">
    <input id="fileChange" type="hidden" value=<%if(bb.getFile() == null){%>"0"<%}else{ %>"1"<%} %>>
    </form>
</div>

<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
<script src="js/updateBoardJs.js"></script>
</body>
</html>
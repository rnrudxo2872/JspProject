<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="com.weatherFood.board.boardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String title ="공유 게시판";
%>
<title>FProject | <%=title %></title>
<!--  
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png"></link>
-->
<%
String pageNum = request.getParameter("pageNum");
int boardNum = 0;
boardBean bb = new boardBean();

try{
	boardNum = Integer.parseInt(request.getParameter("num"));
	boardDAO bdao = new boardDAO();
	bb = bdao.getBoard(boardNum);
	
	bdao.updateReadCount(bb.getNum());
}catch(Exception e){
	%>
	<script>
	alert("없는 게시물입니다!");
	location.href = "shareBoard.jsp";
	</script>
	<%
}

%>
<%@include file="partials/style.jsp" %>
</head>
<body>

<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>
<main class="boardDetail">
    <table>
        <tr id="table_title">
        	<td style="font-weight:900; width:100px;">제목 :</td>
            <th><%=bb.getTitle()%></th>
            <td style="width:100px;">조회수</td>
            <td style="width:100px;"><%=bb.getReadcount() %></td>
        </tr>
        <tr>
            <th id="table_writer"><%=bb.getUser_name()%></th>
            <td id="table_date" colspan="3"><%=bb.getDate()%></td>
        </tr>
        <tr>
            <td id="table_file" colspan="4">첨부파일 : <%=bb.getFile()%></td>
        </tr>
        <tr>
            <td id="table_content" colspan="4"><%=bb.getContent()%></td>
        </tr>
    </table>
</main>
<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
</body>
</html>
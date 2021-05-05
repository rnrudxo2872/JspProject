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
//DB 글 개수
boardDAO bdao = new boardDAO();
int cnt = bdao.getBoardCount();

//보여줄 게시판 개수
int pageSize;
if(request.getParameter("pageSize") == null)
	pageSize = 5;
else
	pageSize = Integer.parseInt(request.getParameter("pageSize"));

//현재페이지
String pageNum = request.getParameter("pageNum");
if(pageNum == null)
	pageNum = "1";

int currentPage = Integer.parseInt(pageNum);

int startRow = (currentPage - 1) * pageSize + 1;
int endRow = currentPage * pageSize;

ArrayList<boardBean> bbs = new ArrayList<boardBean>();

//목록 로딩
bbs = bdao.getBoards(startRow, pageSize);
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
    <input id=title type="text" name="title">
    <input type="file" name="filename">
    <textarea name="content" cols="30" rows="10">내용을 입력해 주세요.</textarea>
</div>

<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
<script src="js/changePageBlock.js"></script>
</body>
</html>
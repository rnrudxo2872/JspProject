<%@page import="com.weatherFood.comment.commentBean"%>
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
<div class="detail-container">
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
    <%
    String curId = (String)session.getAttribute("id");
    String curBoardId = bb.getUser_name();
    
    if(curId != null && curId.equals(curBoardId)){ %>
    <div>
    <div class="detail-container__userInter">
    <span><a class="board-container-footer__insertBoard" href="shareBoard.jsp?pageNum=<%=pageNum%>">글 목록</a></span>
    <span><a class="board-container-footer__insertBoard" href="updateBoard.jsp?num=<%=bb.getNum()%>">글 수정</a></span>
    <span><a class="board-container-footer__insertBoard" onclick="confirmDel(<%=boardNum%>)">글 삭제</a></span>
    </div>
    <%} %>
    <div class="comment-container" style="box-sizing: border-box; overflow: hidden;">
    	<% if(curId != null){%>
    	<form class="comment-container__form">
    	<input id="userComment" type="text" placeholder="코멘트를 남겨보세요!" name="userComment">
    	</form>
    	<%} %>
    	<input id="user_id" type="hidden" value="<%if(curId != null) {%><%=curId%><%}%><%else{%>None<%}%>">
		<input id="board_num" type="hidden" value="<%=boardNum %>">
	<div class="commentList-head"></div>
	<div class="comment-container__commentList">
	
	</div>
    </div>
    </div>
    </div>
</main>
<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
<script src="js/deleteBoard.js"></script>
<script src="js/comment/commentFun.js"></script>
<script src="js/comment/updateComment.js"></script>
</body>
</html>
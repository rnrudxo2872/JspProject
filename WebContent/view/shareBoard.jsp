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
<link rel="stylesheet" href="style/weather.css">
<link rel="stylesheet" href="style/frame.css">
<link rel="stylesheet" href="style/map.css">
<link rel="stylesheet" href="style/board.css">
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
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>
    <main class="board-container">
        <table class="board-container-boardList">
			<tr>
	            <th>게시글 번호</th>
	            <th>제목</th>
	            <th>작성자</th>
	            <th>작성 날짜</th>
        	</tr>
			<%
			for(int i = 0; i < bbs.size(); i++){
			boardBean curBoard = (boardBean)bbs.get(i);
			%>
			<tr>
	            <th><%=curBoard.getNum() %></th>
	            <th><%=curBoard.getTitle() %></th>
	            <th><%=curBoard.getUser_name()%></th>
	            <th><%=curBoard.getDate() %></th>
        	</tr>
			<%} %>       
        </table>
        <footer class="board-container-footer">
            <div class="board-container-footer__pagelink">
            <%
            //게시글이 있다면
            if(cnt != 0){
            	//페이지 갯수
            	int pageCount = cnt/pageSize + (cnt%pageSize == 0 ? 0 : 1);
            	
            	//한 페이지에서 보여줄 페이지 블럭 수
            	int pageBlock = 5;
            	
            	//한 블럭의 시작 페이지
            	int startPage = ((currentPage - 1)/pageBlock) * pageBlock + 1;
            	
            	//블럭 끝 페이지
            	int endPage = startPage + pageBlock - 1;
            	
            	if(endPage > pageCount){
            		endPage = pageCount;
            	}
            	
            	//이전
            	if(startPage > pageBlock){
					%>
					<a href="shareBoard.jsp?pageNum=<%=startPage - pageBlock%>">[이전]</a>
					<%
            	}
            	
            	for(int i = startPage; i<= endPage; i++){
            		%>
            		<a <%if(i == currentPage){%><%} else{%>href="shareBoard.jsp?pageNum=<%=i%>"<%}%>><%=i %></a>
            		<%
            	}
            	
            	if(endPage < pageCount){
            		%>
            		<a href="shareBoard.jsp?pageNum=<%=endPage + 1%>">다음</a>
            		<%
            	}
            }
			%>
            </div>
            <form class="board-container-footer__search" action="">
                <input type="text" placeholder="여기" name="searching">
                <input type="submit" value="검색">
            </form>
            	<a class="board-container-footer__insertBoard" href="insertBoard">글쓰기</a>
        </footer>
    </main>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
</body>
</html>
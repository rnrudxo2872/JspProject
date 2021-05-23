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
String id = null;
int boardNum = -1;
try{
	request.setCharacterEncoding("utf-8");
	id = (String)session.getAttribute("id");
	boardNum = Integer.parseInt(request.getParameter("num"));
}catch(NumberFormatException err){
	System.out.println(err);
	response.sendRedirect("shareBoard.jsp");
	return;
}catch(Exception err){
	System.out.println(err);
	return;
}

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

String pageNum = request.getParameter("pageNum");
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
	<form action="updateBoardAction" class="insertBoard-container-form" enctype="multipart/form-data" method="post">
    <input id=title type="text" name="title" value="<%=bb.getTitle()%>">
    <div>
    <input id="filename" type="file" name="filename">
    <span id="existFile"><%if(bb.getFile() != null){%><%=bb.getFile() %><button class="insertBoard-container__dleButton" onclick="delExistFile()">❌</button><%} %></span>
    </div>
    <textarea name="content" cols="30" rows="10"><%=bb.getContent()%></textarea>
    <input type="submit" value="글쓰기">
    <input type="hidden" value="<%=boardNum %>" name="num">
    <input type="hidden" value="<%=id %>" name="user_name">
    <%if(pageNum != null){%><input type="hidden" value="<%=pageNum%>" name="pageNum"><%} %>
    <input id="fileChange" type="hidden" value=<%if(bb.getFile() == null){%>"0"<%}else{ %>"1"<%} %> name="fileChange">
    </form>
</div>

<div>
<%@include file="partials/footer.jsp" %>
</div> 
</div>
<script src="../js/updateBoardJs.js"></script>
<script>
function updateSubmit(event){
	
	let preFileName = "<%=bb.getFile()%>";
	let CurFilePath = fileInput.value;
	let curFileName = CurFilePath.substring(CurFilePath.indexOf(preFileName),CurFilePath.length);
	
	//alert(curFileName);
	
	if(curFileName === preFileName && existFile.style.display!="none"){
		alert("같은 파일을 중복 첨부는 안됩니다!");
		fileInput.value = "";
		event.preventDefault();
		return;
	}
	
	if(fileInput.value != "" && fileChange.value == "1"){
		if(!confirm("파일을 교체하시겠습니까?")){
			event.preventDefault();
			console.log(existFile.style.display);
			console.log(fileChange.value);
			fileChange.value = "Overwrite";
		}
	}
	//원래있던 파일 삭제시,
	if(existFile.style.display=="none"){
		fileChange.value = "Del";
	}
}

updateForm.addEventListener("submit",updateSubmit);
</script>
</body>
</html>
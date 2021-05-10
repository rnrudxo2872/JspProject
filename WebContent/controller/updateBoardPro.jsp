<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="com.weatherFood.board.boardBean"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 업로드 중...</title>
</head>
<body>

<%
String realPath = request.getRealPath("/upload");



//파일 업로드 최대크기(500MB)
int maxFileSize = 500 * 1024 * 1024;

//인코딩
String enc = "utf-8";

//파일 업로드객체
MultipartRequest mul
= new MultipartRequest(
		request,
		realPath,
		maxFileSize,
		enc,
		new DefaultFileRenamePolicy()
		);

//파일이름
System.out.println("저장이름 : " + mul.getFilesystemName("filename"));
System.out.println("근본이름 : " + mul.getOriginalFileName("filename"));

//DB 데이터 삽입
boardBean bb = new boardBean();
bb.setTitle(mul.getParameter("title"));
bb.setContent(mul.getParameter("content"));
bb.setFile(mul.getFilesystemName("filename"));
bb.setUser_name((String)session.getAttribute("id"));

System.out.println(mul.getFilesystemName("filename"));

//넘어온 게시물 번호
int num = Integer.parseInt(mul.getParameter("num"));

//원래 게시물을 조회.
boardBean originBB = new boardBean();
boardDAO bdao = new boardDAO();

originBB = bdao.getBoard(num);

System.out.println(originBB.getFile_sys());


//게시글 ip주소
bb.setIp(request.getRemoteAddr());
/* boardDAO bdao = new boardDAO();
int num = bdao.insertBoard(bb);
if(num != 0)
	response.sendRedirect("../view/boardContent.jsp?num="+num);
else{
	%>
	<script>
	alert("게시글 오류!");
	location.href = "../view/min.jsp";
	</script>
	<%
} */
%>
</body>
</html>
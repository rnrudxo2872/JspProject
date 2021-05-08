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
System.out.println(realPath);

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
%>
</body>
</html>
<%@page import="com.weatherFood.comment.commentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
System.out.println("안녕??");
int commentNum = Integer.parseInt(request.getParameter("idx"));
System.out.println(commentNum);

commentDAO cdao = new commentDAO();
cdao.delComment(commentNum);
%>
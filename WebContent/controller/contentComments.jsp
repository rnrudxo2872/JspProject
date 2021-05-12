<%@page import="com.weatherFood.comment.commentDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int boardNum = Integer.parseInt(request.getParameter("num"));

commentDAO cdao = new commentDAO();
JSONArray comments = cdao.getComments(boardNum);
System.out.println("댓글 => " + comments);
%>
<%=comments%>
<%@page import="com.weatherFood.comment.commentDAO"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%
int num = Integer.parseInt((String)request.getParameter("num"));
commentDAO cdao = new commentDAO();
int commentSize = cdao.getBoardCommentSize(num);
%><%=commentSize%>
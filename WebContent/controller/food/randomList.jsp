<%@page import="com.weatherFood.main.foodDAO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%
foodDAO fdao = new foodDAO();
JSONArray randomList = fdao.getRandomFood();
System.out.println(randomList);
%><%=randomList%>
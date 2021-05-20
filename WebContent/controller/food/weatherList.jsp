<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.weatherFood.main.foodDAO"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"
%><%
    foodDAO fdao = new foodDAO();
    JSONArray foodList = fdao.getWeatherRecomList(request.getParameter("weather"));
%><%=foodList%>
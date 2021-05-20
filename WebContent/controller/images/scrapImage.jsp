<%@page import="com.weatherFood.image.scrap"%>
<%@page import="org.json.simple.JSONArray"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
scrap scrapper = new scrap();
JSONArray result = scrapper.parseHTML();
%>
<%=result%>
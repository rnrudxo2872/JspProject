<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.weatherFood.function.getbody"%>
<%@page import="com.weatherFood.papago.PapagoTranslator"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
PapagoTranslator papago = new PapagoTranslator();
String now = getbody.readBody(request);

JSONParser jsonParser = new JSONParser();
JSONObject jsonObject = (JSONObject)jsonParser.parse(now);

String eng = (String)jsonObject.get("eng");
%>
<%=papago.engToKor(eng)%>
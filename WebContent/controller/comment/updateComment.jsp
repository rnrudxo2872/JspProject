<%@page import="com.weatherFood.comment.commentDAO"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.weatherFood.function.getbody"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("여기");
	String requestBody = getbody.readBody(request);
	
	JSONParser jsonParser = new JSONParser();
	JSONObject updateBody= (JSONObject)jsonParser.parse(requestBody);

	commentDAO cdao = new commentDAO();
	int flag = cdao.updateComment(updateBody);
	
	if(flag == -1){
		System.out.println("회원 정보값 다름!");
	}else if(flag == 0){
		System.out.println("업데이트 실패!");
	}else{
		System.out.println("정상 업데이트!");
	}
%>
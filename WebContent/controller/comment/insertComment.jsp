<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.weatherFood.comment.commentDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.weatherFood.function.getbody"%>
<%@page import="com.weatherFood.comment.commentBean"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
JSONParser jsonParser = new JSONParser();
String now = getbody.readBody(request);

JSONObject jsonObject = (JSONObject)jsonParser.parse(now);

String curUser = (String)jsonObject.get("user_id");
String curComment = (String)jsonObject.get("comment");
String parseBoardNum = (String)jsonObject.get("board_num");
int curBoardNum = Integer.parseInt(parseBoardNum);

commentBean cb = new commentBean();
cb.setUser_id(curUser);
cb.setComment(curComment);
cb.setBoard_num(curBoardNum);

commentDAO cdao = new commentDAO();
cdao.insertComment(cb);

jsonObject = cdao.getLatestcomment();
//해당 게시판 코멘트 불러옴.
JSONArray comments = new JSONArray();
System.out.println(jsonObject);
comments.add(jsonObject);

//예외(글을 썻기 때문에 있을 수 없는 일임)
if(comments == null){
	return;
}
%>
<%=comments%>
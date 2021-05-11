<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.weatherFood.comment.commentDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.weatherFood.function.getbody"%>
<%@page import="com.weatherFood.comment.commentBean"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//System.out.println(getbody.readBody(request));
JSONParser jsonParser = new JSONParser();
String now = getbody.readBody(request);

JSONObject jsonObject = (JSONObject)jsonParser.parse(now);
jsonObject.put("date", "방금전");

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

//해당 게시판 모든 코멘트 불러옴.(후에 구간별로 불러오게 수정예정)
JSONArray comments = cdao.getComments(curBoardNum);

//따로 JSONArray에 추가! 아마 db에 적용되는 시간이 있나 봄.
comments.add(0, jsonObject);

//예외(글을 썻기 때문에 있을 수 없는 일임)
if(comments == null){
	return;
}
/* System.out.println(comments);
JSONObject dd = (JSONObject)comments.get(0);
System.out.println(dd.get("date")); */
%>
<%=comments%>
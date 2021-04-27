<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    request.setCharacterEncoding("UTF-8"); 
    String cp = request.getContextPath(); 
 
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
 
%>
 
<root>
    <status>권장도서</status> 
 
    <record id="1">
        <subject><%=subject %></subject> <!-- 사용자가 넘긴 데이터들 출력 -->
        <content><%=content %></content>
    </record>
 
    <!-- 추가 데이터 -->
    <record id="2">
        <subject>피구왕 통키</subject> 
        <content>스포츠</content>
    </record>
 
</root>
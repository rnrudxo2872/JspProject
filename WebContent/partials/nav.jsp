<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String root = request.getContextPath(); %>
<nav class="nav-container">
    <div class="nav-container__content">
        <div><a href="/">메인페이지</a></div>
        <div><a href="<%=root%>/serviceFoodPlace">맛집 찾기</a></div>
        <div><a href="<%=root%>/shareBoard">공유 게시판</a></div>
        <div><a href="<%=root%>/foodGallery">인증 게시판</a></div>
    </div>
</nav>
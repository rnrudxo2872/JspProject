<%@page import="com.key.ApiKey"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
ApiKey Apikey = new ApiKey();
String title ="맛집 찾기!";

String recFood = request.getParameter("food");
String viewWord = recFood == null ? "맛집" : recFood+"맛집";
recFood = recFood == null ? "맛집" : recFood;
%>
<title>FProject | <%=title %></title>
<%@include file="partials/style.jsp" %>
</head>
<body>
<div class="basicFrame">
<div class="header">
<jsp:include page="partials/header.jsp" flush="true">
<jsp:param value='<%=URLEncoder.encode(title, "UTF-8") %>' name="title"/>
</jsp:include>
</div>

<article>
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

    <div class="hAddr">
 		<div class="hAddr__item">
	        <span id="centerAddr"></span>
	        <button class="reload"><%=viewWord %>을 여기서 다시 찾아볼까요?</button>
 		</div>
        <div class="hAddr__item">
        	<input type="text" class="searchKeyword">
        	<button class="reload">키워드 검색!</button>
        </div>
    </div>
    <div id="menu_wrap" class="bg_white">
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
    
</div>
</article>
<div>
<%@include file="partials/footer.jsp"%>
</div> 
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Apikey.getKakaoMap()%>&libraries=services"></script>
<script type="text/javascript">let recFood = "<%=recFood%>";</script>
<script src="js/createMap.js"></script>
</body>
</html>
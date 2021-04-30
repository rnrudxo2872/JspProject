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
String title ="OO할 때 먹을 것들!";

String recFood = "파전";
%>
<title>FProject | <%=title %></title>
<!--  
<link rel="shortcut icon" type="image/x-icon" href="./images/logo.png"></link>
-->
<link rel="stylesheet" href="style/weather.css">
<link rel="stylesheet" href="style/frame.css">
<link rel="stylesheet" href="style/map.css">

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
    <div id="map" style="width:1200px;height:400px;position:relative;overflow:hidden;"></div>

    <div class="hAddr">
        <span id="centerAddr"></span>

    </div>
    

</div>
         <ul id="placesList"></ul>
        <div id="pagination"></div>
here content
</article>
<div>
<jsp:include page="partials/footer.jsp"></jsp:include>
</div> 
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Apikey.getKakaoMap()%>&libraries=services"></script>
<script type="text/javascript">let recFood = "<%=recFood%>";</script>
<script src="js/createMap.js"></script>
</body>
</html>
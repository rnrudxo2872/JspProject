<%@page import="com.weather.Weather"%>
<%@page import="com.weather.WeatherData"%>
<%@page import="com.key.ApiKey"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let templ;
let templon;
let SuccessCatch = (curPosition) => {
	templ = curPosition.coords.latitude;
	templon = curPosition.coords.longitude;
}

let ErrorCatch = () => {
    console.log("GeoCoordinates missing");
}

navigator.geolocation.getCurrentPosition(SuccessCatch, ErrorCatch);
let lat = templ;
let lon = templon;

</script>
</head>
<body>
<%
ApiKey Apikey = new ApiKey();
Weather mWeather = new Weather();
//Cookie[] cookies = request.getCookies();
String lat = "<script>document.writeln(lat)</script>";
String lon = "<script>document.writeln(lon)</script>";

/* if(cookies == null){
	
}
for(int i = 0; i < cookies.length; i++){
	if(cookies[i].getName().equals("lat")){
		lat = cookies[i].getValue();
	}else if(cookies[i].getName().equals("lon")){
		lon = cookies[i].getValue();
	}
} */

WeatherData nowWeather = mWeather.getWeather(lat, lon);
System.out.print(nowWeather);
%>
<div id="map" style="width: 500px; height: 400px"></div>

<div style="display:flex; flex-direction: row; max-height: 15px;">
<span class="weatherData"></span>
<span class="weatherIcon"></span>
</div>


<script type="text/javascript" src="weather.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=<%=Apikey.getKakaoMap()%>"></script>
<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>
</body>
</html>
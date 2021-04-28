<%@page import="com.weather.Weather"%>
<%@page import="com.weather.WeatherData"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String cp = request.getContextPath(); 
String lat = request.getParameter("lat");
String lon = request.getParameter("lon");

Weather mWeather = new Weather();
WeatherData nowWeather = mWeather.getWeather(lat, lon);
System.out.print(nowWeather.getWeatherCondition());
%>
<root>
    <status><%=nowWeather.getPlace() %></status> 
    <status><%=nowWeather.getTemp() %></status> 
    <status><%=nowWeather.getWeatherCondition() %></status> 
    <status><%=nowWeather.getDesc() %></status> 
    <status><%=nowWeather.getIcon() %></status> 
 
 
</root>
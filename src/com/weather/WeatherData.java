package com.weather;

public class WeatherData {
	private String weatherCondition;
	private String temp;
	private String place;
	private	String desc; //구름조금;
	private String Icon;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}
	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	
	@Override
	public String toString() {
		return "WeatherData [weatherCondition=" + weatherCondition + ", temp=" + temp + ", place=" + place + ", desc="
				+ desc + ", Icon=" + Icon + "]";
	}
}

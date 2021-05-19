package com.weatherFood.main;

public class foodBean {
	private int idx;
	private String name;
	private String temper;
	private String weather;
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemper() {
		return temper;
	}

	public void setTemper(String temper) {
		this.temper = temper;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String toString() {
		return "foodBean [idx=" + idx + ", name=" + name + ", temper=" + temper + ", weather=" + weather + "]";
	}
}

package com.weatherFood.controller;

public class ForwardDTO {
	private String URL;
	private boolean isRedirect;
	
	public ForwardDTO(String URL, boolean isRedirect) {
		this.URL = URL;
		this.isRedirect = isRedirect;
	}
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String toString() {
		return "ForwardDTO [URL=" + URL + ", isRedirect=" + isRedirect + "]";
	}
}

package com.weatherFood.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class sessionDAO {
	public HttpSession session;
	
	public sessionDAO(HttpServletRequest req) {
		this.session = req.getSession();
	}
	
	public String getPrevURL(){
		return (String)session.getAttribute("prevPage");
	}
	
	public String getSession(String param){
		return (String)session.getAttribute(param);
	}
	
	public void setSession(String name, Object value){
		session.setAttribute(name, value);
	}
}

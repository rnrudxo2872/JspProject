package com.weatherFood.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.session.sessionDAO;

public class logoutAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public logoutAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	@Override
	public ForwardDTO execute() {
		
		sessionDAO Ssdao = new sessionDAO(req);
		Ssdao.session.removeAttribute("id");
		
		return new ForwardDTO(Ssdao.getPrevURL(), true);
	}
}

package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;
import com.weatherFood.action.Action;
import com.weatherFood.session.sessionDAO;

public class Controller extends HttpServlet{
	servletDAO sdao = null;
	sessionDAO Sedao = null;
	String curCmd = null;
	ForwardDTO fdto = null;
	Action action = null;
	
	protected void setInit(HttpServletRequest req, HttpServletResponse res){
		sdao = new servletDAO(req, res);
		Sedao = new sessionDAO(req);
		curCmd = sdao.getCurSubURI();
		fdto = new ForwardDTO(null,false);
	}
	
	protected void setInit(HttpServletRequest req, HttpServletResponse res, int split){
		sdao = new servletDAO(req, res);
		Sedao = new sessionDAO(req);
		curCmd = sdao.getCurSubURI(split);
		fdto = new ForwardDTO(null,false);
	}
	
	protected void render(HttpServletResponse res) throws IOException, ServletException{
		if(fdto != null){
			sdao.render(fdto);
		}
	}
}

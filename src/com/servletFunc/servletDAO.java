package com.servletFunc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.controller.ForwardDTO;

public class servletDAO {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private String URI;
	
	public String getURI() {
		return URI;
	}
	
	public servletDAO(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		this.URI = req.getRequestURI();
	}

	public void forwardRequest(String sendURL) throws ServletException, IOException{
		RequestDispatcher dis = req.getRequestDispatcher(sendURL);
		dis.forward(req, res);
	}
	
	public void redirectRespond(String sendURL) throws IOException{
		res.sendRedirect(sendURL);
	}
	
	public void render(ForwardDTO fdto) throws IOException, ServletException{
		String sendURL = fdto.getURL();
		
		if(fdto.isRedirect()){
			redirectRespond(sendURL);
			return;
		}
		forwardRequest(sendURL);
	}
	
	public String getCurSubURI(){
		String reqURI = req.getRequestURI();
		int ContextLen = req.getContextPath().length();
		
		return reqURI.substring(ContextLen);
	}
	
	public String getCurSubURI(int start){
		String reqURI = req.getRequestURI();
		int ContextLen = req.getContextPath().length()+start;
		
		return reqURI.substring(ContextLen);
	}
}

package com.servletFunc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servletDAO {
	private HttpServletRequest req;
	private HttpServletResponse res;
	
	public servletDAO(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	public void forwardRequest(String sendURL) throws ServletException, IOException{
		RequestDispatcher dis = req.getRequestDispatcher(sendURL);
		dis.forward(req, res);
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

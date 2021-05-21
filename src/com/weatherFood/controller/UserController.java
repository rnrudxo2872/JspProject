package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;

@WebServlet("*.use")
public class UserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servletDAO sdao = new servletDAO(req, res);
		
		String curCmd = sdao.getCurSubURI();
		
		String sendURL = null;
		boolean sendRedirect = false;
		
		System.out.println(curCmd);
		if(curCmd.equals("/login.use")){
			sendURL = "./login.jsp";
			
		}else if(curCmd.equals("/join.use")){
			sendURL = "./join.jsp";
			
		}else if(curCmd.equals("/loginAction.use")){
			
		}else if(curCmd.equals("logout.use")){
			
		}
		if(sendURL != null){
			sdao.forwardRequest(sendURL);
			return;
		}
		res.setStatus(404);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	
}

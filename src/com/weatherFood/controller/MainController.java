package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;
import com.weatherFood.session.sessionDAO;

@WebServlet({"/foodGallery","/serviceFoodPlace","/shareBoard"})
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servletDAO sdao = new servletDAO(req,res);
		sessionDAO Ssdao = new sessionDAO(req);
		
		String curCmd = sdao.getCurSubURI();
		
		ForwardDTO fdto = new ForwardDTO(null,false);
		
		System.out.println(curCmd);
		if(curCmd.equals("/") || curCmd.equals("/main")){
			fdto.setURL("./main.jsp");
		
		}else if(curCmd.equals("/foodGallery")){
			fdto.setURL("./foodGallery.jsp");
		
		}else if(curCmd.equals("/shareBoard")){
			fdto.setURL("./shareBoard.jsp");
		
		}else if(curCmd.equals("/serviceFoodPlace")){
			fdto.setURL("./serviceFoodPlace.jsp");
		}
		
		if(fdto.getURL() != null){
			Ssdao.setSession("prevPage", "."+curCmd);
			sdao.render(fdto);
			return;
		}
		res.setStatus(404);
	}
	
}

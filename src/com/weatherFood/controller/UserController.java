package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;
import com.weatherFood.action.Action;
import com.weatherFood.action.loginAction;
import com.weatherFood.action.logoutAction;
import com.weatherFood.session.sessionDAO;

@WebServlet("*.use")
public class UserController extends HttpServlet{
	servletDAO sdao = null;
	sessionDAO Sedao = null;
	String curCmd = null;
	ForwardDTO fdto = null;
	Action action = null;
	
	private void setInit(HttpServletRequest req, HttpServletResponse res){
		sdao = new servletDAO(req, res);
		Sedao = new sessionDAO(req);
		curCmd = sdao.getCurSubURI();
		fdto = new ForwardDTO(null,false);
	}
	
	private void render(HttpServletResponse res) throws IOException, ServletException{
		if(fdto.getURL() != null || fdto != null){
			//Sedao.setSession("prevPage", "."+curCmd);
			sdao.render(fdto);
			return;
		}
		res.setStatus(404);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res);
		
		System.out.println(curCmd);
		if(curCmd.equals("/login.use")){
			fdto.setURL("./login.jsp");
			
		}else if(curCmd.equals("/join.use")){
			fdto.setURL("./join.jsp");
			
		}else if(curCmd.equals("/logout.use")){
			action = new logoutAction(req, res);
			fdto = action.execute();
		}
		
		render(res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res);
		System.out.println(curCmd);
		
		if(curCmd.equals("/loginAction.use")){
			action = new loginAction(req, res);
			fdto = action.execute();
		}
		
		render(res);
	}
	
}

package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.action.joinAction;
import com.weatherFood.action.loginAction;
import com.weatherFood.action.logoutAction;
import com.weatherFood.action.userDeleteAction;
import com.weatherFood.action.userUpdateAction;

@WebServlet("*.use")
public class UserController extends Controller{
	
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
		
		}else if(curCmd.equals("/update.use")){
			fdto.setURL("./userUpdate.jsp");
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
		
		}else if(curCmd.equals("/joinAction.use")){
			action = new joinAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/updateAction.use")){
			action = new userUpdateAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/delAction.use")){
			action = new userDeleteAction(req, res);
			fdto = action.execute();
		}
		
		render(res);
	}
	
}

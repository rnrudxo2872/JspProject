package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/foodGallery","/serviceFoodPlace","/shareBoard","/boardSearch"})
public class MainController extends Controller {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res);
		
		System.out.println(curCmd);
		if(curCmd.equals("/") || curCmd.equals("/main")){
			fdto.setURL("./main.jsp");
		
		}else if(curCmd.equals("/foodGallery")){
			fdto.setURL("./foodGallery.jsp");
		
		}else if(curCmd.equals("/shareBoard")){
			fdto.setURL("./shareBoard.jsp");
		
		}else if(curCmd.equals("/serviceFoodPlace")){
			fdto.setURL("./serviceFoodPlace.jsp");
			
		}else if(curCmd.equals("/boardSearch")){
			fdto.setURL("./boardSearch.jsp");
			
		}
		
		renderAfterSavePrev(res);
	}
	
}

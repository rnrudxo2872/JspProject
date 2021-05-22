package com.weatherFood.controller.dataset;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;

public class DataController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servletDAO sdao = new servletDAO(req,res);
	
		String curCmd = sdao.getCurSubURI();

		String sendURL = null;
		boolean sendRedirect = false;
		
		if(curCmd.equals("/weatherData.xml")){
			sendURL = "./controller/weatherPage.jsp";
		
		}else if(curCmd.equals("/randomFoodList.json")){
			sendURL = "./controller/food/randomList.jsp";
			
		}else if(curCmd.equals("/weatherFoodList.json")){
			sendURL = "./controller/food/weatherList.jsp";
		
		}else if(curCmd.equals("/imageSource.json")){
			sendURL = "./controller/images/scrapImage.jsp";
		}
		
		if(sendURL != null){
			sdao.forwardRequest(sendURL);
			return;
		}
		res.setStatus(404);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		servletDAO sdao = new servletDAO(req,res);
		
		String curCmd = sdao.getCurSubURI();

		String sendURL = null;
		boolean sendRedirect = false;
		
		if(curCmd.equals("/weatherData.xml")){
			sendURL = "./controller/weatherPage.jsp";
		
		}else if(curCmd.equals("/curIdCheck.xml")){
			sendURL = "./controller/checkId.jsp";
		}
		
		if(sendURL != null){
			sdao.forwardRequest(sendURL);
			return;
		}
		res.setStatus(404);
	}
	
}

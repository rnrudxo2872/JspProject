package com.weatherFood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servletFunc.servletDAO;
import com.weatherFood.action.Action;
import com.weatherFood.action.boardDeleteAction;
import com.weatherFood.action.boardInsertAction;
import com.weatherFood.action.boardUpdateAction;
import com.weatherFood.session.sessionDAO;

@WebServlet({"/shareBoard/content","/shareBoard/insertBoard","/shareBoard/delBoard",
	"/shareBoard/insertBoardAction","/shareBoard/fileDown","/shareBoard/updateBoard",
	"/shareBoard/updateBoardAction"})
public class boardController extends HttpServlet{
	servletDAO sdao = null;
	sessionDAO Sedao = null;
	String curCmd = null;
	ForwardDTO fdto = null;
	Action action = null;
	
	private void setInit(HttpServletRequest req, HttpServletResponse res){
		sdao = new servletDAO(req, res);
		Sedao = new sessionDAO(req);
		curCmd = sdao.getCurSubURI(11);
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
		if(curCmd.equals("/content")){
			fdto.setURL("../boardContent.jsp");
		
		}else if(curCmd.equals("/insertBoard")){
			fdto.setURL("../insertBoard.jsp");
			
		}else if(curCmd.equals("/updateBoard")){
			fdto.setURL("../updateBoard.jsp");
			
		}else if(curCmd.equals("/delBoard")){
			action = new boardDeleteAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/fileDown")){
			fdto.setURL("../controller/file/fileDown.jsp");
		}
		
		render(res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res);
		
		System.out.println("Post! => " + curCmd);
		if(curCmd.equals("/insertBoardAction")){
			action = new boardInsertAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/updateBoardAction")){
			action = new boardUpdateAction(req, res);
			fdto = action.execute();
			
		}
		
		render(res);
	}
}

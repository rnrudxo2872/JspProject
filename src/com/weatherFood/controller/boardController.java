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
import com.weatherFood.action.commentDeleteAction;
import com.weatherFood.action.commentUpdateAction;
import com.weatherFood.session.sessionDAO;

@WebServlet({"/shareBoard/content","/shareBoard/insertBoard","/shareBoard/delBoard",
	"/shareBoard/insertBoardAction","/shareBoard/fileDown","/shareBoard/updateBoard",
	"/shareBoard/updateBoardAction","/shareBoard/delComment","/shareBoard/updateCommentAction"})
public class boardController extends Controller{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res, 11);
		
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
		
		}else if(curCmd.equals("/delComment")){
			action = new commentDeleteAction(req, res);
			fdto = action.execute();
			
		}
		render(res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		setInit(req, res, 11);
		
		System.out.println("Post! => " + curCmd);
		if(curCmd.equals("/insertBoardAction")){
			action = new boardInsertAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/updateBoardAction")){
			action = new boardUpdateAction(req, res);
			fdto = action.execute();
		
		}else if(curCmd.equals("/updateCommentAction")){
			action = new commentUpdateAction(req, res);
			fdto = action.execute();
			
		}
		render(res);
	}
}

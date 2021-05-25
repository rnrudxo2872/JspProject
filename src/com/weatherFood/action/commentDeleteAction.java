package com.weatherFood.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.comment.commentDAO;
import com.weatherFood.controller.ForwardDTO;

public class commentDeleteAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public commentDeleteAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}

	@Override
	public ForwardDTO execute() {
		int commentNum = Integer.parseInt(req.getParameter("idx"));
		System.out.println(commentNum);

		commentDAO cdao = new commentDAO();
		cdao.delComment(commentNum);
		
		return null;
	}
	
}

package com.weatherFood.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.member.memberBean;
import com.weatherFood.member.memberDAO;

public class userUpdateAction implements Action{
	HttpServletRequest req; 
	HttpServletResponse res;
	
	public userUpdateAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	public void sendError(String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("location.href='./main';");
		out.println("</script>");
		
		out.close();
	}
	
	public void wrongPw(String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
	
	public void alertSuccess() throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('수정 완료!');");
		out.println("history.back()");
		out.println("</script>");
		out.close();
		
		
	}

	@Override
	public ForwardDTO execute(){

		try {
			req.setCharacterEncoding("utf-8");
			
			memberBean mb = new memberBean();
			mb.setParams(req);
			
			memberDAO mdao = new memberDAO();

			//0 아이디 X, 1 비밀번호 틀림, 2 정상, -5 비정상
			int flag = mdao.updateInfo(mb);
			if(flag == 0){
				sendError("아이디가 없습니다!");
				return null;
			
			}else if(flag == 1){
				wrongPw("비밀번호가 다릅니다!");
				return null;
				
			}else if(flag == -5){
				sendError("Something Wrong!");
				return null;
			
			}else{
				alertSuccess();
				return new ForwardDTO("./update.use", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

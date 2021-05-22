package com.weatherFood.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.member.memberBean;
import com.weatherFood.member.memberDAO;
import com.weatherFood.session.sessionDAO;

public class userDeleteAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public userDeleteAction(HttpServletRequest req,	HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}

	public void ValueException(String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
	
	public void alertDel() throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('정상적으로 처리되었습니다! 이용해 주셔서 감사합니다.');");
		out.println("location.href='./main';");
		out.println("</script>");
		
		out.close();
	}
	
	@Override
	public ForwardDTO execute() {
		try {
			req.setCharacterEncoding("utf-8");
			memberBean mb = new memberBean();
			mb.setParams(req);
			
			memberDAO mdao = new memberDAO();
			int state = mdao.userDel(mb);
			
			if(state == -1){
				ValueException("비밀번호가 다릅니다!");
				
			}else if(state == 0){
				ValueException("무언가 잘못 됐습니다!");
				
			}else{
				alertDel();
				sessionDAO Ssdao = new sessionDAO(req);
				Ssdao.session.removeAttribute("id");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

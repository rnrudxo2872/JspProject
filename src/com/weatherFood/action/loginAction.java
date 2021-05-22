package com.weatherFood.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.member.memberBean;
import com.weatherFood.member.memberDAO;
import com.weatherFood.session.sessionDAO;

public class loginAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public loginAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	public void ValueException(String msg) throws IOException{
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('" + msg + "');");
		out.println("history.back()");
		out.println("</script>");
		
		out.close();
	}
	
	public void questionJoin() throws IOException{
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("if(window.confirm('아이디가 없습니다! 회원가입 하시겠습니까?'))");
		out.println("{location.href='../view/join.jsp';}");
		out.println("else {history.back();}");
		out.println("</script>");
	}
	
	private boolean isValidLogin(int flag) throws IOException{
		//아이디가 없다면,
		if(flag == 0){
			questionJoin();
			return false;
		}
		if(flag == 1){
			ValueException("비밀번호가 틀렸습니다!");
			return false;
		}
		return true;
	}
	
	@Override
	public ForwardDTO execute() {
		ForwardDTO fdto = null;
		System.out.println("execute!@!@");

		try {
			req.setCharacterEncoding("utf-8");
			String id = req.getParameter("id") ;
			String pw = req.getParameter("pw");
			
			//파라미터 유무 확인
			if(id == null || pw == null){
				ValueException("정보값이 잘못됐습니다!");
				return fdto;
			}
			
			memberBean mb = new memberBean();
			mb.setId(id);
			mb.setPw(pw);
			
			memberDAO mdao = new memberDAO();
			int flag = mdao.loginFun(mb);
			
			if(!isValidLogin(flag))
				return null;
			
			sessionDAO Sedao = new sessionDAO(req);
			Sedao.setSession("id", mb.getId());
			
			fdto = new ForwardDTO(Sedao.getPrevURL(), true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fdto;
	}
}

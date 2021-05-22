package com.weatherFood.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.member.memberBean;
import com.weatherFood.member.memberDAO;

public class joinAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public joinAction(HttpServletRequest req, HttpServletResponse res){
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
	
	@Override
	public ForwardDTO execute() {
		try {
			req.setCharacterEncoding("utf-8");
			
			String pw1 = req.getParameter("pw");
			String pw2 = req.getParameter("pw2");
			
			if(!pw1.equals(pw2)){
				ValueException("입력된 비밀번호가 서로 다릅니다! 다시 확인해주세요");
				return null;
			}
			if(pw1.indexOf(' ') != -1){
				ValueException("비밀번호가 옳지않은 방식입니다!");
				return null;
			}
			
			memberBean mb = new memberBean();
			mb.setParams(req);
			
			memberDAO mdao= new memberDAO();
			boolean joinSuc = mdao.insertMember(mb);

			if(joinSuc){
				System.out.print("회원가입 성공!");
				return new ForwardDTO("./login.use", true);
			}
			ValueException("이미 사용중인 아이디가 있습니다! 중복 체크해주세요!");
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
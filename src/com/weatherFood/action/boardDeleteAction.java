package com.weatherFood.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weatherFood.board.boardBean;
import com.weatherFood.board.boardDAO;
import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.session.sessionDAO;

public class boardDeleteAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public boardDeleteAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}

	private void sendRedirect(String msg) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("location.href='../main'");
		out.println("</script>");
		
		out.close();
	}
	
	private void complit() throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('글 삭제 성공!');");
		out.println("location.href='../shareBoard'");
		out.println("</script>");
		
		out.close();
	}
	
	@Override
	public ForwardDTO execute() {
		try {
			req.setCharacterEncoding("utf-8");
			int num = Integer.parseInt(req.getParameter("num"));
			
			sessionDAO Ssdao = new sessionDAO(req);

			if(Ssdao.getSession("id") == null){
				sendRedirect("세션이 만료되었습니다!");
				return null;
			}

			boardBean bb = new boardBean();
			boardDAO bdao = new boardDAO();
			
			String fileSysName = bdao.getFileSysName(num);

			//-1이면 예상 외 에러, 1이면 성공
			int flag = bdao.boardDel(Ssdao.getSession("id"),num);

			if(flag == -1){
				sendRedirect("회원 정보 불일치!");
			}else{
				//성공시, 파일도 삭제
				String filePath = req.getRealPath("/upload");
				
				if(fileSysName != null){
					filePath += "/" + fileSysName;
					
					File file = new File(filePath);
					if(file.exists()){
						file.delete();
						System.out.println("파일 삭제! => " + fileSysName);
					}else{
						System.out.println("서버에 파일이 존재하지 않음!");
					}
				}else{
					System.out.println("해당 게시글 파일이 존재하지 않음!");
				}
				complit();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return null;
	}
}

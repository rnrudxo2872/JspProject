package com.weatherFood.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.weatherFood.board.boardBean;
import com.weatherFood.board.boardDAO;
import com.weatherFood.controller.ForwardDTO;

public class boardInsertAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public boardInsertAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	private void sendError() throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('게시글 오류!');");
		out.println("location.href='../main'");
		out.println("</script>");
		
		out.close();
	}

	@Override
	public ForwardDTO execute() {
		int num = 0;
		
		try{
			String realPath = req.getRealPath("/upload");
			System.out.println(realPath);
			
			//파일 업로드 최대크기(500MB)
			int maxFileSize = 500 * 1024 * 1024;
			
			//인코딩
			String enc = "utf-8";
			
			//파일 업로드객체
			MultipartRequest mul
			= new MultipartRequest(
					req,
					realPath,
					maxFileSize,
					enc,
					new DefaultFileRenamePolicy()
					);
			
			//파일이름
			System.out.println("저장이름 : " + mul.getFilesystemName("filename"));
			System.out.println("근본이름 : " + mul.getOriginalFileName("filename"));
			
			//DB 데이터 삽입
			boardBean bb = new boardBean();
			bb.setParam(mul);
			
			//게시글 ip주소
			bb.setIp(req.getRemoteAddr());
			
			boardDAO bdao = new boardDAO();
			num = bdao.insertBoard(bb);
			
			if(num == 0){
				sendError();
				return null;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		
		return new ForwardDTO("./content?num="+num, true);
	}
}

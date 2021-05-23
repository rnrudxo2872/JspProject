package com.weatherFood.action;

import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.weatherFood.board.boardBean;
import com.weatherFood.board.boardDAO;
import com.weatherFood.controller.ForwardDTO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

public class boardUpdateAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public boardUpdateAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}
	
	private void sendRedirect(String url) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('게시글 수정완료!');");
		out.println("location.href='"+url+"'");
		out.println("</script>");
		
		out.close();
	}
	
	private void sendError(String url) throws IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('수정 실패!');");
		out.println("location.href='"+url+"'");
		out.println("</script>");
		
		out.close();
	}

	@Override
	public ForwardDTO execute() {
		try {
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
			
			//넘어온 게시물 번호
			int num = Integer.parseInt(mul.getParameter("num"));
			String pageNum = mul.getParameter("pageNum");

			//DB 데이터 삽입
			boardBean bb = new boardBean();
			bb.setNum(num);
			bb.setParam(mul);
	
			System.out.println(mul.getFilesystemName("filename"));
			System.out.println(mul.getParameter("fileChange"));
	
			//원래 게시물을 조회.
			boardBean originBB = new boardBean();
			boardDAO bdao = new boardDAO();
	
			originBB = bdao.getBoard(num);
	
			System.out.println(originBB.getFile_sys());
	
	
			//게시글 ip주소
			bb.setIp(req.getRemoteAddr());
	
			/* 
			경우
			Del	x를 누르고 파일 안내기
				x를 누르고 파일 등록
				
			1	x를 안누르고 파일 안내기
				x를 안누르고 파일 등록
				
			0	x를 안누르고 파일 안내기
				x를 안누르고 파일 등록
			 */
	
			//파일이 등록 안되어있다면,
			String SysFileName = mul.getFilesystemName("filename");
			String changeState = mul.getParameter("fileChange");
			System.out.println("수정 파일"+SysFileName);
			
			if(SysFileName == null){
				
				//원래 첨부파일 있다면 유지
				if(changeState.equals("1")){
					bb.setFile(originBB.getFile());
					bb.setFile_sys(originBB.getFile_sys());
				
				}else if(changeState.equals("Del")){
					
					if(originBB.getFile() != null){
						realPath += "/" + originBB.getFile_sys();
							
						File file = new File(realPath);
						if(file.exists()){
							file.delete();
							System.out.println("파일 삭제! => " + originBB.getFile_sys());
						}else{
							System.out.println("서버에 파일이 존재하지 않음!");
						}
					}
				}
	
			//파일이 등록 되어있다면,
			}else{
				
				//이미 물어봤으니, 덮어쓴다.
				//원래 파일이 있다면, 삭제
				if(originBB.getFile() != null){
					realPath += "/" + originBB.getFile_sys();
						
					File file = new File(realPath);
					if(file.exists()){
						file.delete();
						System.out.println("파일 삭제! => " + originBB.getFile_sys());
					}else{
						System.out.println("서버에 파일이 존재하지 않음!");
					}
	
				}
				//덮어쓰는데 원래 파일이 없다면, 삭제안해도 됨 
				
			}
	
			int flag = bdao.updateBoard(bb);
			if(flag != 0)
				sendRedirect("./content?num="+num+(pageNum != null ? "&pageNum="+pageNum:""));
	
			else{
				sendError("../main");
			}
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return null;
	}
	
	

}

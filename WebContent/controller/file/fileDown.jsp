<%@page import="java.net.URLEncoder"%>
<%@page import="com.weatherFood.board.boardDAO"%>
<%@page import="java.io.FileInputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식은 request 인코딩 설정을 안해도 된다.
	String fileName = request.getParameter("fileName");
	int boardNum = Integer.parseInt(request.getParameter("num"));
	
	boardDAO bdao = new boardDAO();
	
	String sysFileName = null;
	
	try{
		sysFileName = bdao.getFileSysName(boardNum);
	}catch(Exception e){
		System.out.println("파일 경로 에러!");
		return;
	}
	
	String uploadPath = "upload";
	
	//프로젝트 정보
	ServletContext ctx = getServletContext();
	
	//프로젝트 루트path + upload dir
	String DownloadPath = ctx.getRealPath(uploadPath);

	String FilePath = DownloadPath + "\\" + sysFileName;
	
	//버퍼 설정
	byte[] buf = new byte[4096];
	
	FileInputStream fis = new FileInputStream(FilePath);
	System.out.println(fis);
	
	String curMimeType = getServletContext().getMimeType(FilePath);
	
	//지정되지 않은 MIME 타입
	if(curMimeType == null){
		curMimeType = "application/octet-stream";
	}
	
	//response type지정
	response.setContentType(curMimeType);
	
	//ie 브라우저 대비
	String agent = request.getHeader("User-Agent");
	
	//ie 브라우저 여부
	boolean ieBrowser = (agent.indexOf("MSIE") > -1 || agent.indexOf("Trident") > -1);

	if(ieBrowser){
		fileName = URLEncoder.encode(fileName,"utf-8").replaceAll("\\+", "%20");//공백은 %20이다.
	}else{
		//모든 브라우저들 한글 깨짐 방지(인코딩 방식 변경)
		fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
	}
	
	//다운로드 형식 response 설정
	response.setHeader("Content-Disposition", "attachment; filename= " + fileName);

	//브라우저를 통한 출력 스트림
	ServletOutputStream outStream = response.getOutputStream();
	
	int readByte = 0;
	
	while((readByte = fis.read(buf,0,buf.length)) != -1){
		outStream.write(buf,0,readByte);
	}
	
	// 잔여 내보냄
	outStream.flush();
	
	//자원해제 => outStream은 해제시 모든 과정 완료.
	outStream.close();
	fis.close();
%>
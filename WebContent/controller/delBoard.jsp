<%@page import="java.io.File"%>
<%@page import="com.weatherFood.board.boardBean"%>
<%@page import="com.weatherFood.board.boardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 삭제 중...</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
int num = Integer.parseInt(request.getParameter("num"));
String id = (String)session.getAttribute("id");

if(id == null){
	
}else{

	boardBean bb = new boardBean();
	boardDAO bdao = new boardDAO();
	
	String fileSysName = bdao.getFileSysName(num);

	//-1이면 예상 외 에러, 1이면 성공
	int flag = bdao.boardDel(id,num);

	if(flag == -1){
		%>
		<script>
			alert("세션값 또는 다른 데이터값이 틀립니다!");
			history.back();
		</script>
		<%
	}else{
		//성공시, 파일도 삭제
		String filePath = request.getRealPath("/upload");
		
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
		%>
		<script>
			alert("글 삭제 성공!");
			location.href="../view/shareBoard.jsp";
		</script>
		<%
	}
}

%>
</body>
</html>
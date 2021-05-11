package com.weatherFood.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

public class boardDAO {
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private String sql = "";
	
	//getConnection
	private Connection getConnection(){
		try {
			Context initCTX = new InitialContext();
			
			Context envCTX = (Context)initCTX.lookup("java:comp/env");
			DataSource ds = (DataSource)envCTX.lookup("jdbc/mysqlDB");
			
			conn = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("드라이버 연결!");
		return conn;
	}
	//getConnection
	
	private void clearDB(){
			try {
				if(conn != null) conn.close();
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	//getBoardCount
	public int getBoardCount(){
		int cnt = 0;
		
		try {
			conn = getConnection();
			sql = "select count(*) from board";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return cnt;
	}
	//getBoardCount
	
	
	//getBoards
	public ArrayList<boardBean> getBoards(int startRow, int pageSize){
		ArrayList<boardBean> ret = null;
		boardBean tmpBb = null;
		
		try {
			conn = getConnection();
			
			sql = "select * from board "
					+ "order by num desc "
					+ "limit ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			ret = new ArrayList<boardBean>();
			while(rs.next()){
				tmpBb = new boardBean();
				
				tmpBb.setNum(rs.getInt(1));
				tmpBb.setTitle(rs.getString(2));
				tmpBb.setUser_name(rs.getString(3));
				tmpBb.setContent(rs.getString(4));
				tmpBb.setDate(rs.getDate(5));
			
				ret.add(tmpBb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			clearDB();
		}
		
		return ret;
	}
	//getBoards
	
	//getBoard
	public boardBean getBoard(int num){
		boardBean bb = null;
		
		try {
			conn = getConnection();
			sql = "select * from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				bb = new boardBean();
				bb.setNum(rs.getInt(1));
				bb.setTitle(rs.getString(2));
				bb.setUser_name(rs.getString(3));
				bb.setContent(rs.getString(4));
				bb.setDate(rs.getDate(5));
				bb.setFile(rs.getString(6));
				bb.setReadcount(rs.getInt(8));
				bb.setComments(rs.getInt(9));
				bb.setFile_sys(rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return bb;
	}
	//getBoard
	
	//insertBoard
	public int insertBoard(boardBean bb){
		
		// 게시판 번호
		int num = 0;
		
		try {
			conn = getConnection();
			sql = "select max(num) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1) + 1;
			}else
				num = 1;
			
			sql = "insert into board"
					+ "(num,title,user_name,content,date,file,ip,readcount,comments,file_sys) "
					+ "values (?,?,?,?,now(),?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getTitle());
			pstmt.setString(3, bb.getUser_name());
			pstmt.setString(4, bb.getContent());
			pstmt.setString(5, bb.getFile());
			pstmt.setString(6, bb.getIp());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, bb.getFile_sys());
			pstmt.executeUpdate();
			System.out.println("게시글 작성!" + bb.getUser_name());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return num;
	}
	//insertBoard
	
	//updateReadCount
	public void updateReadCount(int boardNum){
		
		try {
			conn = getConnection();
			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
	}
	//updateReadCount
	
	//boardDel(bb,id);
	public int boardDel(String id, int num){
		//-1 error, 1 정상
		int flag = -1;
		
		try {
			conn = getConnection();
			sql = "select user_name from board where num=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			//글쓴이가 같은지 확인
			if(rs.next()){
				
				String getId = rs.getString(1);
				if(!getId.equals(id)){
					return flag;
				}
			}else{
				return flag;
			}
			
			//db삭제
			sql = "delete from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			System.out.println("글삭제!");
			flag = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return flag;
	}
	//boardDel(bb,id);
	
	//getFileSysName
	public String getFileSysName(int num){
		
		String fileSysName = null;
		
		//파일 삭제
		try {
			System.out.println(num);
			conn = getConnection();
			sql = "select file_sys from board where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				String fileName = rs.getString(1);
				System.out.println("글 파일 조회! => " + fileName);
				
				System.out.println(fileName);
				if(fileName != null){
					fileSysName = fileName;
				}
			}else{
				System.out.println("글 없음");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return fileSysName;
	}
	//getFileSysName
	
	//updateBoard
	public int updateBoard(boardBean bb){
		int flag = 0;
		
		try {
			conn = getConnection();
			sql = "update board set title=?,content=?,file=?,file_sys=? where num=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bb.getTitle());
			pstmt.setString(2, bb.getContent());
			pstmt.setString(3, bb.getFile());
			pstmt.setString(4, bb.getFile_sys());
			pstmt.setInt(5, bb.getNum());
			
			pstmt.executeUpdate();
			flag = 1;
			System.out.println("수정 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}
	//updateBoard
}

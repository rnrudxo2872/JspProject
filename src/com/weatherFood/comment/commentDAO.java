package com.weatherFood.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class commentDAO {
	
	//연결 객체
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	//getConnection
	private Connection getConnection(){
		try {
			//Context 객체생성
			Context initCTX = new InitialContext();
			
			//DB연동 정보
			Context envCTX = (Context)initCTX.lookup("java:comp/env");
			DataSource ds = (DataSource)envCTX.lookup("jdbc/mysqlDB");
			
			conn = ds.getConnection();
			
		} catch (Exception e) {
			System.out.println("드리이버 연결 실패!");
			e.printStackTrace();
		}
		
		System.out.println("드라이버 연결! => " + conn);
		return conn;
	}
	//getConnection
	
	//clearDB
	public void clearDB(){
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//clearDB
	
	//insertComment
	public void insertComment(commentBean cb){
		int idx = 0;
		
		try {
			conn = getConnection();
			sql = "select max(idx) from board_comment";
			pstmt = conn.prepareStatement(sql);

			rs =pstmt.executeQuery();
			if(rs.next()){
				idx = rs.getInt(1) + 1;
			}else
				idx = 1;
			
			sql = "insert into board_comment (idx,user_id,comment,date,board_num) "
					+ "values (?,?,?,now(),?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, cb.getUser_id());
			pstmt.setString(3, cb.getComment());
			pstmt.setInt(4, cb.getBoard_num());
			
			pstmt.executeUpdate();
			System.out.println(cb.getUser_id() + " : 사용자 덧글 등록!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
	}
	//insertComment
	
	//getComments
	public JSONArray getComments(int start, int boardNum){
		JSONArray comments = null;
		
		try {
			conn = getConnection();
			sql = "select * from board_comment where board_num=? "
					+ "order by idx desc "
					+ "limit ?,5";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				comments = new JSONArray();
				
				JSONObject obj = new JSONObject();
				obj.put("idx", rs.getInt(1));
				obj.put("user_id", rs.getString(2));
				obj.put("comment", rs.getString(3));
				
				//date는 문자열로 해줘야함.
				obj.put("date", rs.getString(4));
				obj.put("board_num",rs.getInt(5));
				
				comments.add(obj);
			}
			while(rs.next()){
				JSONObject obj = new JSONObject();
				obj.put("idx", rs.getInt(1));
				obj.put("user_id", rs.getString(2));
				obj.put("comment", rs.getString(3));
				
				//date는 문자열로 해줘야함.
				obj.put("date", rs.getString(4));
				obj.put("board_num",rs.getInt(5));
				
				comments.add(obj);
			}
			System.out.println("게시판 덧글 불러오기!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return comments;
	}
	//getComments
	
	//getBoardCommentSize
	public int getBoardCommentSize(int num){
		int ret = 0;
		
		try {
			conn = getConnection();
			sql = "select count(*) from board_comment where board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next())
				ret = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return ret;
	}
	//getBoardCommentSize
	
	//delComment
	public void delComment(int idx){
		
		try {
			conn = getConnection();
			sql = "delete from board_comment where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
	}
	//delComment
	
	//updateComment
	public int updateComment(JSONObject data){
		int flag = -1;
		String curId = (String)data.get("user_id");
		String curComment = (String)data.get("comment");
		int idx = Integer.parseInt((String)data.get("idx"));

	 	try {
	 		
	 		conn = getConnection();
	 		sql = "select user_id from board_comment where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, idx);
			
			rs = pstmt.executeQuery();
			
			//회원정보 일치
			if(rs.next()){
				flag = 0;
			}else{
				return flag;
			}
			
			sql = "update board_comment set comment=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, curComment);
			pstmt.setInt(2, idx);
			
			pstmt.executeUpdate();
			
			//정상 업데이트
			flag = 1;
			System.out.println("댓글 업데이트! => " + curComment);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
	 	
		return flag;
	}
	//updateComment
}
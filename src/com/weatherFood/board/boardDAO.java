package com.weatherFood.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
					+ "order by re_ref desc, re_seq asc "
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
				tmpBb.setRe_ref(rs.getInt(6));
				tmpBb.setRe_lev(rs.getInt(7));
				tmpBb.setRe_seq(rs.getInt(8));
			
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
				bb.setRe_ref(rs.getInt(6));
				bb.setRe_lev(rs.getInt(7));
				bb.setRe_seq(rs.getInt(8));
				bb.setFile(rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return bb;
	}
	//getBoard
}

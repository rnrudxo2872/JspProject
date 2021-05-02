package com.weatherFood.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class memberDAO {
	
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
	
	//checkId
	public boolean checkId(String Id){
		boolean exist = false;
		try {
			conn = getConnection();
			
			//id 검색
			sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("같은 아이디가 있음! => " + rs.getString(1));
				exist = true;
			}
			else
				exist = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	//checkId
	
	//insertMember
	public boolean insertMember(memberBean mb){
		int idx = 0;
		
		try {
			conn = getConnection();
			
			//idx 조회
			sql = "select max(idx) from member";
			pstmt =conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				idx = rs.getInt(1) + 1;
			}else
				idx = 1;
			
			//삽입구문
			sql = "insert into member (idx,id,pw,name,gender,reg_date) "
					+ "values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, mb.getId());
			pstmt.setString(3, mb.getPw());
			pstmt.setString(4, mb.getName());
			pstmt.setString(5, mb.getGender());
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.executeUpdate();
			
			System.out.println("회원 삽입 성공!");
			
			return true;
		}catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("id 중복 에러!");
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return false;
	}
	//insertMember
}

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
		}finally{
			clearDB();
		}
		
		return exist;
	}
	//checkId
	
	//insertMember
	public boolean insertMember(memberBean mb){
		int idx = 0;
		
		try {
			conn = getConnection();
			
			//중복여부
			sql = "select pw from member where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next())
				return false;
			
			//idx 조회
			sql = "select max(idx) from member";
			pstmt =conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				idx = rs.getInt(1) + 1;
			}else
				idx = 1;
			
			//삽입구문
			sql = "insert into member (idx,id,pw,name,gender,reg_date,addr,addr_detail) "
					+ "values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, mb.getId());
			pstmt.setString(3, mb.getPw());
			pstmt.setString(4, mb.getName());
			pstmt.setString(5, mb.getGender());
			pstmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(7, mb.getAddr());
			pstmt.setString(8, mb.getAddr_detail());
			pstmt.executeUpdate();
			
			System.out.println("회원 삽입 성공!");
			
			return true;
		}catch(MySQLIntegrityConstraintViolationException e){
			System.out.println("중복 에러!");
			return false;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return false;
	}
	//insertMember
	
	//loginFun
	public int loginFun(memberBean mb){
		
		//아이디가 없을 시 - 0, 비밀번호가 틀렸을 시 - 1, 로그인 성공 시 - 2
		int flag = 0;
		
		try {
			conn = getConnection();
			sql = "select pw from member where id=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mb.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				// 동일한 아이디가 존재함
				flag = 1;
				
				String rsPW = rs.getString(1);
				String curPW = mb.getPw();
				
				//아이디와 비밀번호가 같다면,
				if(curPW.equals(rsPW)){
					flag = 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return flag;
	}
	//loginFun
	
	//getInfo
	public memberBean getInfo(String id){
		memberBean mb = null;
		
		try {
			conn = getConnection();
			
			//같은 아이디가 있는가?
			sql = "select count(*) from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1) > 1){
					throw new Exception();
				}
			}
			
			sql = "select * from member where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				mb = new memberBean();
				
				mb.setIdx(rs.getInt(1));
				mb.setId(rs.getString(2));
				mb.setPw(rs.getString(3));
				mb.setName(rs.getString(4));
				mb.setGender(rs.getString(5));
				mb.setReg_date(rs.getTimestamp(6));
				mb.setAddr(rs.getString(7));
				mb.setAddr_detail(rs.getString(8));
			}
		} catch (SQLException e) {
			System.out.println("회원정보 조회 실패!");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("종합적 오류!");
		} finally{
			clearDB();
		}
		
		
		return mb;
	}
	//getInfo
	
	
	//updateInfo
	public int updateInfo(memberBean mb){
		//0 아이디 X, 1 비밀번호 틀림, 2 정상, -5 비정상
		int flag = -5;
		
		try {
			int idx = 0;
			
			conn = getConnection();
			sql = "select pw, idx from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				String ans = rs.getString(1);
				idx = rs.getInt(2);
				if(!ans.equals(mb.getPw())){
				flag = 1;
				return flag;
				}
			}else{
				//아이디 존재 X
				flag = 0;
				return flag;
			}
			
			sql = "update member set name=?,gender=?,addr=?,addr_detail=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getName());
			pstmt.setString(2, mb.getGender());
			pstmt.setString(3, mb.getAddr());
			pstmt.setString(4, mb.getAddr_detail());
			pstmt.setInt(5, idx);
			
			pstmt.executeUpdate();
			flag = 2;
			System.out.println("회원정보 업데이트!");
		} catch (SQLException e) {
			System.out.println("업데이트 혹은 조회 sql 에러!");
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return flag;
	}
	//updateInfo
	
	//userDel
	public int userDel(memberBean mb){
		
		//-1 비밀번호 틀림, 1정상, 0 다른 에러
		int flag = 0;
		
		try {
			conn = getConnection();
			sql = "select pw from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				String rsPw = rs.getString(1);
				if(!rsPw.equals(mb.getPw())){
					flag = -1;
					return flag;
				}
			}
			
			sql = "delete from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			
			pstmt.executeUpdate();
			flag = 1;
			System.out.println("회원탈퇴 성공!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		return flag;
	}
	//userDel(mb,pw)
}

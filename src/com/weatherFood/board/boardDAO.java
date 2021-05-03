package com.weatherFood.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class boardDAO {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
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
}

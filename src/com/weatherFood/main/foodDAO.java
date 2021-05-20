package com.weatherFood.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class foodDAO {
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
	
	//clearDB
	public void clearDB(){
		try {
			if(conn != null) conn.close();
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//clearDB
	
	//putObject
	public JSONObject putObject(ResultSet row) throws SQLException{
		JSONObject data = new JSONObject();
		
		data.put("idx", row.getInt(1));
		data.put("name", row.getString(2));
		
		return data;
	}
	//putObject
	
	//getDatas
	public JSONArray getDatas(ResultSet results) throws SQLException{
		JSONArray retDatas = new JSONArray();
	
		while(results.next()){
			JSONObject data = putObject(results);
			retDatas.add(data);
		}
		
		return retDatas;
	}
	//getDatas
	
	//getRandomFood
	public JSONArray getRandomFood(){
		JSONArray retArr = null;
		
		try {
			conn = getConnection();
			sql = "select * from weather_food t1, "
				+ "("
				+ "select idx from weather_food "
				+ "order by rand() limit 10"
				+ ") t2 "
				+ "where t1.idx = t2.idx;";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			retArr = getDatas(rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			clearDB();
		}
		
		return retArr;
	}
	//getRandomFood
	
}

package com.weatherFood.member;

import java.sql.Timestamp;

public class memberBean {
	private int idx;
	private String id;
	private String pw;
	private String name;
	private String gender;
	private Timestamp reg_date;
	
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String toString() {
		return "memberBean [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender
				+ ", reg_date=" + reg_date + "]";
	}
}

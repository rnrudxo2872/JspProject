package com.weatherFood.member;

public class memberBean {
	private int idx;
	private String id;
	private String pw;
	private String gender;
	
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
		return "memberBean [idx=" + idx + ", id=" + id + ", pw=" + pw + ", gender=" + gender + "]";
	}
}

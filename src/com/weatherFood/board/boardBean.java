package com.weatherFood.board;

import java.sql.Date;

public class boardBean {
	private int num;
	private String title;
	private String user_name;
	private String content;
	private Date date;
	private String file;
	private String ip;
	private int readcount;
	private int comments;
	private String file_sys;
	
	public String toString() {
		return "boardBean [num=" + num + ", title=" + title + ", user_name=" + user_name + ", content=" + content
				+ ", date=" + date + ", file=" + file + ", ip=" + ip + ", readcount=" + readcount + ", comments="
				+ comments + ", file_sys=" + file_sys + "]";
	}

	public String getFile_sys() {
		return file_sys;
	}

	public void setFile_sys(String file_sys) {
		this.file_sys = file_sys;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	
	
}

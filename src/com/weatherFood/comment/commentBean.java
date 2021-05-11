package com.weatherFood.comment;

import java.sql.Date;

public class commentBean {
	private int idx;
	private String comment;
	private Date date;
	private int board_num;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String toString() {
		return "commentBean [idx=" + idx + ", comment=" + comment + ", date=" + date + ", board_num=" + board_num + "]";
	}
}

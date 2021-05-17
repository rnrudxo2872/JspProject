package com.weatherFood.board;

public class searchBean {
	private String searchWord;
	private int searchType;
	
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getSearchType() {
		return searchType;
	}
	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String toString() {
		return "searchBean [searchWord=" + searchWord + ", searchType=" + searchType + "]";
	}
	
	
}

package com.weatherFood.image;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class scrap {
	private ArrayList<String> getSourceArr(Elements elements){
		ArrayList<String> retArr = new ArrayList<String>();
		
		for(Element item : elements){
			retArr.add(item.attr("src"));
		}
		
		return retArr;
	}
	
	public List<String> parseHTML(){
		List<String> arr = new ArrayList<String>();
		
		try {
			Document doc = Jsoup.connect("https://stock.adobe.com/kr/search?load_type=search&is_recent_search=&search_type=usertyped&k=%EC%9D%8C%EC%8B%9D&native_visual_search=&similar_content_id="/*"https://search.naver.com/search.naver?where=image&sm=tab_jum&query=%EC%9D%8C%EC%8B%9D"*/).timeout(50000).get();

			Elements elements = doc.select("#search-results img");
			
			arr = getSourceArr(elements);
			
		} catch (Exception e) {
			System.out.println("스크랩에러!");
		}
		
		return arr;
	}
}

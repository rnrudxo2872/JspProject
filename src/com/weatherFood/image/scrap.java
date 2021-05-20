package com.weatherFood.image;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class scrap {
	private JSONArray getSourceArr(Elements elements){
		JSONArray retArr = new JSONArray();
		
		for(Element item : elements){
			JSONObject tmpObj = new JSONObject();
			tmpObj.put("src", item.attr("src"));
			
			retArr.add(tmpObj);
		}
		
		return retArr;
	}
	
	public JSONArray parseHTML(){
		JSONArray arr = null;
		
		try {
			Document doc = Jsoup.connect("https://stock.adobe.com/kr/search?load_type=search&is_recent_search=&search_type=usertyped&k=%EC%9D%8C%EC%8B%9D&native_visual_search=&similar_content_id=").timeout(50000).get();

			Elements elements = doc.select("#search-results img");
			arr = getSourceArr(elements);
			
		} catch (Exception e) {
			System.out.println("스크랩에러!");
		}
		
		return arr;
	}
}

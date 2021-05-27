package com.weatherFood.image;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.weatherFood.papago.PapagoTranslator;

public class scrap {
	private String descTranslateArray(String desc){
		PapagoTranslator translator = new PapagoTranslator();
		return translator.engToKor(desc);
	}
	
	private String getDescTranslate(String engStr){
		String result = descTranslateArray(engStr);
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = null;
		System.out.println("여기 try 전  " );
		try {
			jsonObj = (JSONObject)jsonParser.parse(result);
			System.out.println("여기 : " + jsonObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject messageObj = (JSONObject)jsonObj.get("message");
		System.out.println("여기 resultObj : " + messageObj);
		JSONObject resultObj = (JSONObject)messageObj.get("result");
		String ret = (String)resultObj.get("translatedText");
		System.out.println(ret);
		return ret;
	}
	
	private JSONArray getSourceArr(Elements elements){
		JSONArray retArr = new JSONArray();
		
		for(Element item : elements){
			JSONObject tmpObj = new JSONObject();
			
			//String tranStr = getDescTranslate(item.attr("alt")); 
			
			tmpObj.put("src", item.attr("src"));
			tmpObj.put("alt", item.attr("alt"));
			
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

package com.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather {
    public static void main(String[] args) {
        try{
        	
        	//서울시청의 위도와 경도
            String lon = "126.977948";  //경도
            String lat = "37.566386";   //위도
            
            //OpenAPI call하는 URL
            String urlstr = "https://api.openweathermap.org/data/2.5/weather?"
                        + "lat="+lat+"&lon="+lon
                        +"&lang=kr&appid=9ec4f040743c8a068d8372f0f8af47d2"
                        +"&units=metric";
            URL url = new URL(urlstr);
        
            BufferedReader bf;
            String line;
            String result="";

            //날씨 정보를 받아온다.
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 문자열로 변환.
            while((line=bf.readLine())!=null){
                result=result.concat(line);
                System.out.println(line);
            }

            //문자열을 JSON으로 파싱
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
            System.out.println(jsonObj);
            
            //지역 출력
            System.out.println("지역 : " + jsonObj.get("name"));

            //날씨 출력
            JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
            JSONObject obj = (JSONObject) weatherArray.get(0);
            System.out.println("날씨 : "+obj.get("main"));

            //온도 출력(절대온도라서 변환 필요)
            JSONObject mainArray = (JSONObject) jsonObj.get("main");
            //double ktemp = Double.parseDouble(mainArray.get("temp").toString());
            //double temp = mainArray.get("temp");
            System.out.println(mainArray.get("temp"));

            bf.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

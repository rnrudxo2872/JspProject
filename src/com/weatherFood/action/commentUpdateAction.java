package com.weatherFood.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.weatherFood.comment.commentDAO;
import com.weatherFood.controller.ForwardDTO;
import com.weatherFood.function.getbody;

public class commentUpdateAction implements Action{
	HttpServletRequest req;
	HttpServletResponse res;
	
	public commentUpdateAction(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
	}

	@Override
	public ForwardDTO execute() {
		String requestBody;
		try {
			requestBody = getbody.readBody(req);
			JSONParser jsonParser = new JSONParser();
			JSONObject updateBody= (JSONObject)jsonParser.parse(requestBody);
			
			commentDAO cdao = new commentDAO();
			int flag = cdao.updateComment(updateBody);
			
			if(flag == -1){
				System.out.println("회원 정보값 다름!");
			}else if(flag == 0){
				System.out.println("업데이트 실패!");
			}else{
				System.out.println("정상 업데이트!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}

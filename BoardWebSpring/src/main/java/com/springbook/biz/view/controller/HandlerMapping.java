package com.springbook.biz.view.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.Controller;

public class HandlerMapping {	//클라이언트 요청에 대한 Controller 매핑 
	
	private Map<String, Controller> mappings;   //객체 변수 선언 (Map는 인터페이스)
	
	public HandlerMapping () {   //기본 생성자 
		mappings = new HashMap<String, Controller>();    //HashMap : Map를 구현하는 클래스 		
		mappings.put("/login.do", new LoginController()); 
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController()); 
	}
	public Controller getController(String path) {
		return mappings.get(path); 
	}

}

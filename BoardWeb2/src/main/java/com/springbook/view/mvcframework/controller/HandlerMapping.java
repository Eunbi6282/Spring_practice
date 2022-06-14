package com.springbook.view.mvcframework.controller;

import java.util.HashMap;
import java.util.Map;

import com.springbook.view.mvcframework.board.DeleteBoardController;
import com.springbook.view.mvcframework.board.GetBoardController;
import com.springbook.view.mvcframework.board.GetBoardListController;
import com.springbook.view.mvcframework.board.InsertBoardController;
import com.springbook.view.mvcframework.board.UpdateBoardController;
import com.springbook.view.mvcframework.user.LoginController;
import com.springbook.view.mvcframework.user.LogoutController;

public class HandlerMapping {
	private Map<String, Controller> mappings;

	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());		
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());		
	}

	public Controller getController(String path) {
		return mappings.get(path);
	}
}


package com.springbook.biz.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

	@Override
	public String HanlderRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리 - Controller");
		
		// 뷰페이지 리턴
		return null;
	}

}

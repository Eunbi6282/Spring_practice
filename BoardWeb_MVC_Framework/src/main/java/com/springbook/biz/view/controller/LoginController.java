package com.springbook.biz.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

	@Override
	public String HanlderRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�α��� ó�� - Controller");
		
		// �������� ����
		return null;
	}

}

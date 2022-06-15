package com.springbook.biz.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리 - SpringMVC 호출 - Controller 분리");
		
		// 1. 사용자 입력 정보 추출
					String id = request.getParameter("id");
					String password = request.getParameter("password");

					// 2. DB 연동 처리
					UserVO vo = new UserVO();
					vo.setId(id);
					vo.setPassword(password);

					UserDAO userDAO = new UserDAO();
					UserVO user = userDAO.getUser(vo);

					// 3. 화면 네비게이션  (뷰 페이지를 돌려줌 )
					ModelAndView mav = new ModelAndView();
					
					if (user != null) {
						mav.setViewName("getBoardList.do"); 
						return mav;
					} else {
						mav.setViewName("login.jsp"); 
						return mav; // ViewResolver 페이지에서 뷰페이지를 완성함. 
					}
	}

}

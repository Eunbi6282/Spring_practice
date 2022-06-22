package com.springbook.biz.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class UserController {
	
	//1. 로그인 (GET 방식 처리 메소드)
	@RequestMapping (value = "/login.do", method = RequestMethod.GET)
	//public String loginView(UserVO vo)  {
	public String loginView(@ModelAttribute("user") UserVO vo)  {
		System.out.println("로그인 처리 (GET)- Spring MVC 호출 - Controller 통합");
		
		
		vo.setId("admin"); 
		vo.setPassword("1234"); 
		
		
		return "login.jsp";				//forward로 전송시 vo의 변수의 값이 잘 전달됨
		//return "redirect:login.jsp"; 
		
		//Command 객체에 변수의 값을 담아서 View 페지로 전송 할 수 있다. 
		//VO 객체의 클래스 이름은 UserVO , ${userVO.id} 뷰페이지에서 출력을 할 수 있다. 
	 /*
	  @ModelAttribute : Command 객체로 던져지는 객체 이름을 다른 이름으로 변경 (UserVO => user)
	  		view 페이지에서 출력시 : ${user.id}, ${user.password}
	   	
	  */
		
	}
	
	//1. 로그인 (POST 방식 처리 메소드)
	@RequestMapping (value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session)  {
		System.out.println("로그인 처리 (POST)- Spring MVC 호출 - Controller 통합");
		
		if (vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
			// vo에 id변수의 값이 넘어오지 않으면 강제로 예외 발생
		}

		UserVO user = userDAO.getUser(vo);
		
		if (user != null) {
			session.setAttribute("userName", user.getName()); 
			return "getBoardList.do"; 
		}else {
			return "login.jsp"; 
		}
		
	}	
	
	//2. 로그 아웃 : Logout Controller 통합
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		System.out.println("로그 아웃 처리 - Spring MVC 호출 - Controller 통합");
		
		session.invalidate();	
		return "redirect:login.jsp"; 	
	}

}

package com.springbook.view.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.springbook.view")
 //com.springbook.view 이 패키지에서 발생되는 모든 exception들이 처리됨
	//BoardController, UserController
public class CommonExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class) 
	public ModelAndView handlerIllegalArgumentException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/arithmeticError.jsp");
		return mav;
	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmetdicException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/arithmeticError.jsp");			
		return mav;
	}
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/nullPointError.jsp");
		return mav;
	}
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/common/error.jsp");
		return mav;
	}
	
	
	
	
}

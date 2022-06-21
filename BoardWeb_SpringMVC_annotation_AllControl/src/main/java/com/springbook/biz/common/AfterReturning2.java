package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

public class AfterReturning2 {
	
	public void afterReturningLog(JoinPoint jp, Object returnObj ) {
		//AOP의 바인드 변수 : Object returnObj
		// afterReturning : 핵심 로직이 실행후 리턴값이 성공적으로 적용될때 작동되는
		// Object returnObj 는 핵심 로직이 실행되고 리턴되는 객체를 매개변수로 받는다. 
		
		String method = jp.getSignature().getName(); 
		
		if (returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj; 
			if (user.getRole().equals("Admin")) {
				System.out.println(user.getName() + "로그인 (admin)");
			}		
		}
		
		System.out.println("[사후 처리] 메소드 명 " + method + "()" );
		System.out.println("[사후 처리] 메소드 리턴값 : " + returnObj.toString());
	}

}

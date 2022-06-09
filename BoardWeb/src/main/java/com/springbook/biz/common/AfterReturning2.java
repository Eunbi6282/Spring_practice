package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

public class AfterReturning2 {
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		// AOP 바인드 변수 : Object returnObj
		// afterReturning : 핵심 로직이 실행 후 리턴값이 성공적으로 적용될 때 작동
		// Object returnObj 는 핵심 로직이 실행되고 리턴되는 객체를 매개변수로 받는다. 
		
		String method = jp.getSignature().getName();
		
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj; // 다운캐스팅하고
			if(user.getRole().equals("Admin")) {  //role이 admin이면
				System.out.println(user.getName() + "로그인(admin)");
			}
		}
		
		System.out.println("[사후처리]" + method + "() - 메서드 명");
		System.out.println("[사후처리]" + returnObj.toString() + "() - 메서드 리턴값");
	}
}

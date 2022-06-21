package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	
	//비즈니스 로직 실행전, 실행후 작동 
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("[Before] : 비즈니스 로직 실행전 처리");
		
		Object returnObj = pjp.proceed(); 
		
		System.out.println("[After] : 비즈니스 로직 실행후 처리");
			
		return returnObj; 
				
	}

}

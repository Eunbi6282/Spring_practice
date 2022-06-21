package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice2 {
	//AfterThrowing : 핵심 로직에서 예외가 발생 되었을때 처리되는 Advice
	//Exception exceptionOjb : AOP의 바이드 변수 
	
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName(); 
		
		System.out.println("[예외 처리] " +method + "() 메소드 수행중 오류 발생");
		System.out.println("[예외 처리] 예외메세지 : " + exceptionObj.getMessage());
		
	}
	

}

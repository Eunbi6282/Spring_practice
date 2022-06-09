package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

public class AfterThrowingAdvice2 {
	//AfterThrowing : 핵심 로직에서 예외가 발생되었을 때 처리되는 Advice
	// Exception exceptionObj : 바인드 변수
	
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName();
		System.out.println("[예외처리] " + method + "() - 메서드 수행 중 오류 발생");
		System.out.println("[예외처리] 예외 메세지" + exceptionObj.getMessage() + "() - 메서드 수행 중 오류 발생");
		
	}
}

package com.springbook.biz.common;

// 모든 메서드에서 처리될 내용
public class LogAdvice {
	// Advice : AOP 용어, 모든 메서드 실행전에 공통으로 실행되는 코드가 들어간 클래스 
	
	public void printLog() {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 동작(AOP설정에서 작동)");
	}
}

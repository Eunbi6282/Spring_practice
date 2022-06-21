package com.springbook.biz.common;

public class LogAdvice {
	
	//Advice : AOP 용어,  모든 메소드 실행전에 공통으로 실행되는 코드가 들어간 클래스 
	
	public void printLog() {
		System.out.println("[공통로그] 비즈스 로직 수행 전 동작 (AOP 설정을 통한 구동)");
	}

}

package com.springbook.biz.common;

public class AfterThrowingAdvice {
	// 예외가 발생되었을 때 작동되는 Advice
	public void exceptionLog() {
		System.out.println("[예외 발생] -비즈니스 로직 처리중 예외 발생");
	}
}

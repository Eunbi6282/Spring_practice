package com.springbook.biz.common;

public class Log4jAdvice {
	
	//기존의 LogAdvice의 기능이 낡아서 새로운 기능이 적용된 Log4jAdvice로 수정됨. 
	
	public void printLogging() {
		System.out.println("[공통 로그 - Log4j] - 비즈니스 로직 수행전 동작(AOP 설정을 통한 구동");
	}

}

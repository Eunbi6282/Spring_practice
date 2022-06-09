package com.springbook.biz.common;

public class log4jAdvice {
	//기존의 LogAdvice의 기능이 낡아서 새로ㅜㄴ 기능이 적요된 LOG4jAdvice로 수정됨
	public void printlogging() {
		System.out.println("[공통로그 - log4j] - 비즈니스 로직 수행 전 동작");
	}
}

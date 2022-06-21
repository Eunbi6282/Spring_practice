package com.springbook.biz.common;

public class AfterReturningAdvice {

	public void afterReturningLog () {
		System.out.println("[사후 처리] - 리턴이 성공적으로 작동후 작동 ");
	}
}

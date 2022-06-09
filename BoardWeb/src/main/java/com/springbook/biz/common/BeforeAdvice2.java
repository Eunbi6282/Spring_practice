package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
// AOP에서 정보를 출력하는 메서드가 저장된 인터페이스
public class BeforeAdvice2 {
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
			// 처리하는 메서드 이름을 알아올 수 있다.
		Object[] args = jp.getArgs();
			// 메서드 호출에 어떤 값들을 사용했는지 알 수 있다.
			// 메서드의 매개변수에 등록된 정보
		System.out.println("[사전처리]" + method + "() - 메서드 정보");
		System.out.println("[사전처리] ARGS 정보 -" + args[0].toString());
	}
}

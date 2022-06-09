package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice3 {
	
	/////// 1. 포인트 컷 설정 : 적용할 메서드의 필터링//////////////
		@Pointcut("execution ( * com.springbook.biz..*Impl.*(..))")
		public void allPointCut() {}  // 포인트컷 이름만 적용하는 메서드 생성
		
		@Pointcut("execution ( * com.springbook.biz..*Impl.get*(..))")
		public void getPointCut() {}  
	
	//// 2. Advice 메서드 : 모든 필터링된 메서드에 적용되는 메서드
		@Before("allPointCut()")
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
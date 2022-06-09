package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;


@Service
@Aspect
public class AfterThrowingAdvice3 {
	//AfterThrowing : 핵심 로직에서 예외가 발생되었을 때 처리되는 Advice
	// Exception exceptionObj : 바인드 변수
	

	/////// 1. 포인트 컷 설정 : 적용할 메서드의 필터링//////////////
		@Pointcut("execution ( * com.springbook.biz..*Impl.*(..))")
		public void allPointCut() {}  // 포인트컷 이름만 적용하는 메서드 생성
		
		@Pointcut("execution ( * com.springbook.biz..*Impl.get*(..))")
		public void getPointCut() {}  
	
		//////2. 어드바이스 
	@AfterThrowing(pointcut = "allPointCut()", throwing = "exceptionLog") 
	public void exceptionLog(JoinPoint jp, Exception exceptionObj) {
		String method = jp.getSignature().getName();
		
		if (exceptionObj instanceof IllegalArgumentException) {
			System.out.println("부적절한 값이 입력됨");
		}else if (exceptionObj instanceof NumberFormatException) {
			System.out.println("숫자 형식의 값이 아닙니다.");
		}else if (exceptionObj instanceof Exception) {
			System.out.println("문제가 발생헸습니다.");
		}
		System.out.println("[예외처리] " + method + "() - 메서드 수행 중 오류 발생");
		System.out.println("[예외처리] 예외 메세지" + exceptionObj.getMessage() + "() - 메서드 수행 중 오류 발생");
		
	}
}

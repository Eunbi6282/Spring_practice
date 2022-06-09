package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice2 {

		// Aroound : 메서드 실행 전후로 작동하는 메서드 
			// 매개변수로 ProceedingJoinPoint
			// ProceedingJoinPoint는 인터페이스 이고, JoinPoint를 상속하고 있다. 
		public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
			String method = pjp.getSignature().getName();
			
			// 메서드 실햄ㅇ 시간을 출력
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			
			Object obj = pjp.proceed(); // 핵심관심 메서드 처리
				//이 메서드 앞뒤로 적용
			
			stopWatch.stop();
			return obj;
		}

}

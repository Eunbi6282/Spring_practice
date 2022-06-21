package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect       //Aspect : 포인트 컷 + 어드바이스 
public class BeforeAdvice3 {
	
	//////1. 포인터 컷 셋팅 //////////////////
	
	@Pointcut("execution (* com.springbook.biz..*Impl.*(..))")
	public void allPointCut () {}   //포인트 컷 이름만 적용하는 빈 메소드 생성 
	
	@Pointcut("execution (* com.springbook.biz..*Impl.get*(..))")
	public void getPointCut() {}
	
	
	
	////////2.Advice 메소드 : 모든 필터링된 메소드에 적용되는 메소드 ///////////
	@Before ("allPointCut()")
	public void beforeLog(JoinPoint jp) {
		
		String method = jp.getSignature().getName(); 
			//처리하는 메소드 이름을 알아올 수 있다. 
		Object[] args = jp.getArgs(); 
			//메소드 호출에 어떤 값들을 사용했는지 알 수 있다.
			//메소드의 매개변수에 등록된 정보 
		
		System.out.println("[사전 처리-어노테이션 적용]" + method + "() - 메소드 정보 ");
		System.out.println("[사전 처리-어노테이션 적용] ARGS 정보 - " + args[0].toString());
		
	}
}

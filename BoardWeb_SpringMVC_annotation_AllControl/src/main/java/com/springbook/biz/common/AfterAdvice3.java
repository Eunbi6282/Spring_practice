package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice3 {
	
	///////1.포인터 컷 설정 ////////
		//외부 포이트컷 사용하기 

	//////2. 어드바이스 설정 
	@After ("PointCutCommon.getPointCut()")
	public void afterLog( ) {
		System.out.println("[사후 처리^^] - 비즈니스 로직 수행후 작동");
	}
	
	
}

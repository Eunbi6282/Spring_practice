package com.springbook.biz.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutCommon {
	//외부 포인트 컷 클래스 만들기 ( 포인트 컷만 저장하는 클래스 )
	
	@Pointcut("execution (* com.springbook.biz..*Impl.*(..))")
	public void allPointCut () {}   //포인트 컷 이름만 적용하는 빈 메소드 생성 
	
	@Pointcut("execution (* com.springbook.biz..*Impl.get*(..))")
	public void getPointCut() {}

}

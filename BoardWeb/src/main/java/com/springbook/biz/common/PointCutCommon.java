package com.springbook.biz.common;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutCommon {
		// �ܺ� ����Ʈ�� ����� (����Ʈ�Ƹ� �����ϴ� Ŭ����)
		@Pointcut("execution ( * com.springbook.biz..*Impl.*(..))")
		public void allPointCut() {}  // ����Ʈ�� �̸��� �����ϴ� �޼��� ����
		
		@Pointcut("execution ( * com.springbook.biz..*Impl.get*(..))")
		public void getPointCut() {}  
		
		
}

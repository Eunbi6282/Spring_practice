package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice3 {
	
	/////// 1. ����Ʈ �� ���� : ������ �޼����� ���͸�//////////////
		@Pointcut("execution ( * com.springbook.biz..*Impl.*(..))")
		public void allPointCut() {}  // ����Ʈ�� �̸��� �����ϴ� �޼��� ����
		
		@Pointcut("execution ( * com.springbook.biz..*Impl.get*(..))")
		public void getPointCut() {}  
	
	//// 2. Advice �޼��� : ��� ���͸��� �޼��忡 ����Ǵ� �޼���
		@Before("allPointCut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
			// ó���ϴ� �޼��� �̸��� �˾ƿ� �� �ִ�.
		Object[] args = jp.getArgs();
			// �޼��� ȣ�⿡ � ������ ����ߴ��� �� �� �ִ�.
			// �޼����� �Ű������� ��ϵ� ����
		System.out.println("[����ó��]" + method + "() - �޼��� ����");
		System.out.println("[����ó��] ARGS ���� -" + args[0].toString());
		
	}
}
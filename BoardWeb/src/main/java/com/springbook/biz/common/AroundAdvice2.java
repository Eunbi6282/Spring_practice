package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice2 {

		// Aroound : �޼��� ���� ���ķ� �۵��ϴ� �޼��� 
			// �Ű������� ProceedingJoinPoint
			// ProceedingJoinPoint�� �������̽� �̰�, JoinPoint�� ����ϰ� �ִ�. 
		public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
			String method = pjp.getSignature().getName();
			
			// �޼��� ���ܤ� �ð��� ���
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			
			Object obj = pjp.proceed(); // �ٽɰ��� �޼��� ó��
				//�� �޼��� �յڷ� ����
			
			stopWatch.stop();
			return obj;
		}

}

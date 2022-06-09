package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
// AOP���� ������ ����ϴ� �޼��尡 ����� �������̽�
public class BeforeAdvice2 {
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

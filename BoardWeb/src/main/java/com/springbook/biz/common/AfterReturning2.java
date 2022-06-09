package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;

import com.springbook.biz.user.UserVO;

public class AfterReturning2 {
	public void afterReturningLog(JoinPoint jp, Object returnObj) {
		// AOP ���ε� ���� : Object returnObj
		// afterReturning : �ٽ� ������ ���� �� ���ϰ��� ���������� ����� �� �۵�
		// Object returnObj �� �ٽ� ������ ����ǰ� ���ϵǴ� ��ü�� �Ű������� �޴´�. 
		
		String method = jp.getSignature().getName();
		
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj; // �ٿ�ĳ�����ϰ�
			if(user.getRole().equals("Admin")) {  //role�� admin�̸�
				System.out.println(user.getName() + "�α���(admin)");
			}
		}
		
		System.out.println("[����ó��]" + method + "() - �޼��� ��");
		System.out.println("[����ó��]" + returnObj.toString() + "() - �޼��� ���ϰ�");
	}
}

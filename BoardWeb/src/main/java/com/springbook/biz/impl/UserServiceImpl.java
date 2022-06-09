package com.springbook.biz.impl;

import org.springframework.beans
.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

@Service("userService") // ����Ͻ� ������ ó���ϴ� �޼���
public class UserServiceImpl implements UserService {
	
	// ��ü ���� (DI)
		// 1. �����ڸ� ���� ���� (xml����)
			/*
			<bean id = "userService" class = "com.springbook.biz.impl.UserServiceImpl">
				<constructor-arg ref = "userDAO"></constructor-arg>
			</bean>
			<bean id = "userDAO" class = "com.springbook.biz.impl.UserDAO"></bean>
			*/
	
		// 2. Setter ����	(xml����)
			/*
			<bean id = "userService" class = "com.springbook.biz.impl.UserServiceImpl">
				<property name = "userDAO" ref = "userDAO"></property>  //userDAO => setter�̸�
			</bean>
			<bean id = "userDAO" class = "com.springbook.biz.impl.UserDAO"></bean>
			*/
	
		// 3. Annotation�� �̿��� ����(@Autowired, @Qualifier, @Resource)
	
	
	
	// 3. 
	 @Autowired	// ��ü ����(Ÿ������ ����) => ������̼����� ��ü ����
		private UserDAO userDAO;
	
	// 2.
	//Setter�� ����ؼ� ��ü ����
		public void setUserDAO (UserDAO userDAO) {
			this.userDAO = userDAO;
		}
	
	
	@Override
	public UserVO getUser(UserVO vo) { //@Autowired�� ���ؼ� ��ü ������ �߱� ������ �ҷ��� �� ����
		return userDAO.getUser(vo);
	}


	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}
	
	

}

package com.springbook.biz.impl;

import org.springframework.beans
.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

@Service("userService") // 비즈니스 로직을 처리하는 메서드
public class UserServiceImpl implements UserService {
	
	// 객체 주입 (DI)
		// 1. 생성자를 통한 주입 (xml설정)
			/*
			<bean id = "userService" class = "com.springbook.biz.impl.UserServiceImpl">
				<constructor-arg ref = "userDAO"></constructor-arg>
			</bean>
			<bean id = "userDAO" class = "com.springbook.biz.impl.UserDAO"></bean>
			*/
	
		// 2. Setter 주입	(xml설정)
			/*
			<bean id = "userService" class = "com.springbook.biz.impl.UserServiceImpl">
				<property name = "userDAO" ref = "userDAO"></property>  //userDAO => setter이름
			</bean>
			<bean id = "userDAO" class = "com.springbook.biz.impl.UserDAO"></bean>
			*/
	
		// 3. Annotation을 이용한 주입(@Autowired, @Qualifier, @Resource)
	
	
	
	// 3. 
	 @Autowired	// 객체 주입(타입으로 주입) => 어노테이션으로 객체 주입
		private UserDAO userDAO;
	
	// 2.
	//Setter를 사용해서 객체 주입
		public void setUserDAO (UserDAO userDAO) {
			this.userDAO = userDAO;
		}
	
	
	@Override
	public UserVO getUser(UserVO vo) { //@Autowired를 통해서 객체 주입을 했기 때문에 불러올 수 있음
		return userDAO.getUser(vo);
	}


	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}
	
	

}

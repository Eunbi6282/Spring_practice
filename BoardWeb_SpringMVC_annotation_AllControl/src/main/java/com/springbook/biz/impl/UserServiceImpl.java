package com.springbook.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserVO;

@Service ("userService")    //비즈니스 로직을 처리하는 클래스 
public class UserServiceImpl implements UserService {
	
	//객체 주입 (DI) 
		//1. 생성자를 통한 주입     ( XML 설정 )
		//2. Setter를 사용한 주입  ( XML 설정 ) 
		//3. 어노테이션을 이용한 주입 (@Autowired , ) 
	
	//@Autowired					//객체 주입 (타입으로 주입) , 어노테이션으로 객체 주입 
	//private UserDAO userDAO; 
	@Autowired
	private UserDAOSpring userDAO; 
	
	// Setter 를 사용해서 객체 주입 (XML 설정)
	/*
	public void setUserDAO (UserDAO userDAO) {
		this.userDAO = userDAO; 
	}
	*/
	//생성자를 이용한 객체 주입 (XML 설정)
	/*
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO; 
	}
	*/ 
	

	@Override
	public UserVO getUser(UserVO vo) {
		
		return userDAO.getUser(vo);
	}

	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
		
	}
	


}

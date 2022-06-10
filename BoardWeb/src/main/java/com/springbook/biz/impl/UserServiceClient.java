package com.springbook.biz.impl;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserVO;

public class UserServiceClient {

	public static void main(String[] args) {
		// 1. Spring 컨테이너 구동
			AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
			
		// 2. Spring 컨테이너로부터 UserServiceInpl 객체를 Lookup (가져온다)
				// 인터페이스를 업캐스팅해서 가져오기 
			UserService userService = (UserService) container.getBean("userService");
			
		// 3. 로그인 기능 테스트 
			UserVO vo = new UserVO();
			vo.setId("admin");
			vo.setPassword("1234");
			
			UserVO user = userService.getUser(vo);
			
			if(user != null) { // 로그인 성공한 상태
				System.out.println(user.getName() + "님 환영합니다.");
				System.out.println("사용자 아이디 :" + user.getId());
			}else { //null이라면 로그인 실패한 상태
				System.out.println("로그인이 실패했습니다.");
			}
			
			// 3-2 insert메서드호출
				vo.setId("user200");
				vo.setPassword("1234");
				vo.setName("홍길동2");
				vo.setRole("user");
				userService.insertUser(vo);
			
		// 4. Spring 컨테이너 종료
			container.close();
	}

}

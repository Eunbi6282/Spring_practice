package com.springbook.biz.impl;

import com.springbook.biz.user.UserVO;

public interface UserService {

	// 3. CRUD 기능의 메서드 구현    
	// 회원정보 검색(ID, PASSWORD)매개변수로 받는다.
	UserVO getUser(UserVO vo);
	
	void insertUser(UserVO vo);

}
package com.springbook.biz.impl;

import com.springbook.biz.user.UserVO;

public interface UserService {

	// 3. CRUD ����� �޼��� ����    
	// ȸ������ �˻�(ID, PASSWORD)�Ű������� �޴´�.
	UserVO getUser(UserVO vo);
	
	void insertUser(UserVO vo);

}
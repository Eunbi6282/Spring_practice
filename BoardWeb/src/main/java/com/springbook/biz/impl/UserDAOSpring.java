package com.springbook.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.user.UserVO;

@Repository("userDAOSpring")
public class UserDAOSpring {
	
	@Autowired			//jdbcTemplate 객체 활성화 
	private JdbcTemplate jdbcTemplate;  
	
	
	private String USER_GET = "select * from users where id = ? and pass = ?";
	private String INSERT_USER = "insert into users values (?,?,?,?)"; 

	//글 상세 목록 출력
	public UserVO getUser(UserVO vo) {
		System.out.println("===> Spring JDBC로 getUser() 기능 구현");
		Object[] args = {vo.getId(), vo.getPassword()};
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
	
	//글 등록 
	
	public void insertUser(UserVO vo) {
		System.out.println("===> Spring JDBC로 insertUser() 기능 구현");
		jdbcTemplate.update(INSERT_USER, vo.getId(),vo.getPassword(),vo.getName(),vo.getRole());
	}
	
	
	

}

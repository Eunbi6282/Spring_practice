package com.springbook.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")		//UserDAO 클래스를 객체화 userDAO
public class UserDAO  {
	
	//1. JDBC 관련 변수 선언 (conn, pstmt, rs) 
	private Connection conn = null; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	//2. SQL 상수 구문 적용. 
	private String USER_GET = "select * from users where id = ? and password = ?";
	private String INSERT_USER = "insert into users values (?,?,?,?)"; 
	
	//3. CRUD 기능의 메소드 구현 
		//회원 정보 검색 (ID와 password를 매개변수로 받아서 처리 ) 
	public UserVO getUser(UserVO vo) {   //vo : 매개변수로 들어오는 값 
		UserVO user = null;   //user: DB에서 select 해온 값을 리턴 
		
		try {
			System.out.println("===> JDBC로 getUser() 기능처리");
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(USER_GET); 
			pstmt.setString(1,vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();  //sql 실행 결과를 rs에 담는다. 
				//rs의 값이 존재하면 DB의 vo로 넘어오는 id와 password 가 DB에 존재한다. 
			
			//rs 의 값이 존재 할때 user (VO) 에 저장해서 리턴 해서 값을 돌려 준다. 
			
			if (rs.next()) {  // id와 password가 일치할때 rs.next 가 true 가 돤다. 
				user = new UserVO(); 
				user.setId(rs.getString("ID"));
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setRole(rs.getString("ROLE"));			
			}
					
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
				
		return user; 	
	}
	
	//Insert 메소드 구현  (수정 사항 )
	
	public void insertUser(UserVO vo) {
		System.out.println("==> insertUser() 기능구현");
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(INSERT_USER); 
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getRole());
			pstmt.executeUpdate(); 
			
			System.out.println("Insert 성공 ");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	

}

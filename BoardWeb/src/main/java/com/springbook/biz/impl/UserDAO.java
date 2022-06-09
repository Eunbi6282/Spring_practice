package com.springbook.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO") // UserDAO클래스를 객체화
public class UserDAO implements UserService {
	
	// 1. JDBC관련 변수 선언(conn, pstmt, rs)
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	// 2. SQL상수 구문적용
	private String USER_GET = "select * from users where id = ? and pass = ?";
	private String USER_INSERT = "insert into users values(?,?,?,?)";
	
	// 3. CRUD 기능의 메서드 구현    
		// 회원정보 검색(ID, PASSWORD)매개변수로 받는다.
	public UserVO getUser(UserVO vo) {  //vo:매개변수로 들어오는 값
		UserVO user = null;  //user: DB에서 select 해온 값을 리턴
		
		try {
			System.out.println("===> JDBC로 getUser() 기능처리");
			conn = JDBCUtil.getConnection();  //getConnection()를 호출하는 순간 DB에 접속시도
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();  //sql실행 결과를 rs에 담는다. 
			
			if (rs.next()) { // rs에 값이 존재하면=> DB의 VO로 넘어오는 아이디와 패스워드가 일치한다는 뜻(true)
				// userVO에 저장해서 리턴해서 값을 돌려준다.
				user = new UserVO();
				user.setId(rs.getString("ID"));
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("PASS"));
				user.setRole(rs.getString("ROLE"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}
	
		// 3-2 Insert메서드 구현 
	public void insertUser(UserVO vo) {
		try {
			System.out.println("===> JDBC로 insertUser() 기능처리");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getRole());
			pstmt.executeUpdate();
			System.out.println("Insert성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert 기능 구현중 예외 발생");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
}

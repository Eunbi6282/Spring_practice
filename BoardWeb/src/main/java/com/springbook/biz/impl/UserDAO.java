package com.springbook.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO") // UserDAOŬ������ ��üȭ
public class UserDAO implements UserService {
	
	// 1. JDBC���� ���� ����(conn, pstmt, rs)
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	// 2. SQL��� ��������
	private String USER_GET = "select * from users where id = ? and pass = ?";
	private String USER_INSERT = "insert into users values(?,?,?,?)";
	
	// 3. CRUD ����� �޼��� ����    
		// ȸ������ �˻�(ID, PASSWORD)�Ű������� �޴´�.
	public UserVO getUser(UserVO vo) {  //vo:�Ű������� ������ ��
		UserVO user = null;  //user: DB���� select �ؿ� ���� ����
		
		try {
			System.out.println("===> JDBC�� getUser() ���ó��");
			conn = JDBCUtil.getConnection();  //getConnection()�� ȣ���ϴ� ���� DB�� ���ӽõ�
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();  //sql���� ����� rs�� ��´�. 
			
			if (rs.next()) { // rs�� ���� �����ϸ�=> DB�� VO�� �Ѿ���� ���̵�� �н����尡 ��ġ�Ѵٴ� ��(true)
				// userVO�� �����ؼ� �����ؼ� ���� �����ش�.
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
	
		// 3-2 Insert�޼��� ���� 
	public void insertUser(UserVO vo) {
		try {
			System.out.println("===> JDBC�� insertUser() ���ó��");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getRole());
			pstmt.executeUpdate();
			System.out.println("Insert����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert ��� ������ ���� �߻�");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
}

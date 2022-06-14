package com.my.project.logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{
	
	private static LogonDAO instance = new LogonDAO();

	/// LogonDTO객체�? 리턴?��?�� 메서?�� 
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// 기본 ?��?��?�� : private =>?���??��?�� 객체 ?��?�� 불�??��	
		// �?�? ?��?��?��?�� 기본 ?��?��?�� ?���?
	private LogonDAO() { super(); };
	
	// ?��?���??�� 처리 (registerForm -> regiserPro.jsp)?��?�� ?��?��?��?�� 값을 DB?�� ???�� (Insert)
	public void insertMember (LogonDTO member) {
		try {
			String orgPass = member.getPass();
			byte[] targetBytes = orgPass.getBytes();
			
			// ?��?��?��
			Encoder encoder = Base64.getEncoder();
			//Base64?��코딩
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			String sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getEmail());
			psmt.setString(3, member.getName());
			psmt.setString(4, encodedTxt );
			psmt.setString(5, member.getAddress());
			psmt.setString(6, member.getTel());
			psmt.setDate(7, member.getRegidate());
			psmt.executeUpdate();
			
			System.out.println("?��?��?���? DB?��?�� ?���?");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("?��?��?���? DB?��?��?�� ?��?�� 발생");
		}finally {
			//instance.close();
		}
	}
	
	// ?��?��?�� 중복처리 체크
	public int confirmId (String id) {
		int x = -1;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // ?��?��?���? DB?�� 존재?��?�� 경우
				
				System.out.println("존재?��?�� ID?��?��?��. ?��?��?��: " + id);
				
				x = 1;
			} else { // ?��?��?���? DB?�� 존재?���? ?��?�� 경우
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("?��?��?�� 중복 체크 �? ?��?��발생");
		}
		return x;
	}
	
	// 로그?�� 기능(loginPro.jsp) : ?��?��?�� ?��겨받?? ?��?��?��?? ?��?��?��?���? DB?? ?��?��
		// ?��?��?�� ?���?, Db?�� ?��보�?? ?��?��?�� ?��, SB?�� ?��보�?? ?��?��?�� ?��
		// ?��?��?�� ?���?(MemberCheck.jsp)?��?�� ?��?��?��?�� 메서?��
	public int userCheck(String id, String pass) {
		int x = -1;
		
		// 복호?�� : ?��?��?��?�� Password�? ?��?��?�� Password�? �??��
		try {
			String orgPass = pass;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64?��코딩
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT pass FROM member WHERE id=?";
		
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) { // ?��?��?���? 존재?���?
				String dbpasswd = rs.getString("pass");
					// ?��코딩 ?��?��
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decode처리?��
				
				if(orgPass.equals(decodedTxt)) {
					x = 1;  // ?��?��?�� ?��겨온 ?��?��?��?��?? DB?��?�� �??��?�� ?��?��?��?���? ?��치할 ?�� x?�� 1?�� ?��?��
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "?�� 존재?���? ?��?�� 비�?번호 ?��?��?��.");
					x = 0;
				} else {
					System.out.println(id + " ?�� 존재?���? ?��?�� ?��?��?��?��?��?��.");
					x = -1;  // ?��?��?��?���? ?��치하�? ?��?�� ?��
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("?���? ?��?��?��?��?��?��.");
			}finally {
				//instance.close();
			}
		return x;
	}
	
	
	
	
	
	
	
}

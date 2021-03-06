package com.my.project.logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{
	
	private static LogonDAO instance = new LogonDAO();

	/// LogonDTOκ°μ²΄λ₯? λ¦¬ν΄?? λ©μ? 
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// κΈ°λ³Έ ??±? : private =>?ΈλΆ??? κ°μ²΄ ??± λΆκ??₯	
		// λΆ?λͺ? ?΄??€? κΈ°λ³Έ ??±? ?ΈμΆ?
	private LogonDAO() { super(); };
	
	// ??κ°?? μ²λ¦¬ (registerForm -> regiserPro.jsp)?? ??΄?€? κ°μ DB? ???₯ (Insert)
	public void insertMember (LogonDTO member) {
		try {
			String orgPass = member.getPass();
			byte[] targetBytes = orgPass.getBytes();
			
			// ??Έ?
			Encoder encoder = Base64.getEncoder();
			//Base64?Έμ½λ©
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
			
			System.out.println("??? λ³? DB?? ₯ ?±κ³?");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("??? λ³? DB?? ₯? ??Έ λ°μ");
		}finally {
			//instance.close();
		}
	}
	
	// ??΄? μ€λ³΅μ²λ¦¬ μ²΄ν¬
	public int confirmId (String id) {
		int x = -1;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // ??΄?κ°? DB? μ‘΄μ¬?? κ²½μ°
				
				System.out.println("μ‘΄μ¬?? ID???€. ??΄?: " + id);
				
				x = 1;
			} else { // ??΄?κ°? DB? μ‘΄μ¬?μ§? ?? κ²½μ°
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("??΄? μ€λ³΅ μ²΄ν¬ μ€? ??Έλ°μ");
		}
		return x;
	}
	
	// λ‘κ·Έ?Έ κΈ°λ₯(loginPro.jsp) : ?Ό?? ?κ²¨λ°?? ??΄??? ?¨?€??λ₯? DB?? ??Έ
		// ?¬?©? ?Έμ¦?, Db? ? λ³΄λ?? ?? ?  ?, SB? ? λ³΄λ?? ?­? ?  ?
		// ?¬?©? ?Έμ¦?(MemberCheck.jsp)?? ?¬?©?? λ©μ?
	public int userCheck(String id, String pass) {
		int x = -1;
		
		// λ³΅νΈ? : ??Έ?? Passwordλ₯? ?΄?? Passwordλ‘? λ³??
		try {
			String orgPass = pass;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64?μ½λ©
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT pass FROM member WHERE id=?";
		
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) { // ??΄?κ°? μ‘΄μ¬?λ©?
				String dbpasswd = rs.getString("pass");
					// ?μ½λ© ??
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeμ²λ¦¬?¨
				
				if(orgPass.equals(decodedTxt)) {
					x = 1;  // ?Ό?? ?κ²¨μ¨ ?¨?€???? DB?? κ°?? Έ?¨ ?¨?€??κ°? ?ΌμΉν  ? x? 1? ? ?Ή
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "? μ‘΄μ¬?μ§? ?? λΉλ?λ²νΈ ???€.");
					x = 0;
				} else {
					System.out.println(id + " ? μ‘΄μ¬?μ§? ?? ??΄????€.");
					x = -1;  // ?¨?€??κ°? ?ΌμΉνμ§? ?? ?
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("?Έμ¦? ?€?¨??΅??€.");
			}finally {
				//instance.close();
			}
		return x;
	}
	
	
	
	
	
	
	
}

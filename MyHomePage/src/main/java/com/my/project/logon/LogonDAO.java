package com.my.project.logon;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{
	
	private static LogonDAO instance = new LogonDAO();

	/// LogonDTOê°ì²´ë¥? ë¦¬í„´?•˜?Š” ë©”ì„œ?“œ 
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// ê¸°ë³¸ ?ƒ?„±? : private =>?™¸ë¶??—?„œ ê°ì²´ ?ƒ?„± ë¶ˆê??Š¥	
		// ë¶?ëª? ?´?˜?Š¤?˜ ê¸°ë³¸ ?ƒ?„±? ?˜¸ì¶?
	private LogonDAO() { super(); };
	
	// ?šŒ?›ê°??… ì²˜ë¦¬ (registerForm -> regiserPro.jsp)?—?„œ ?„˜?–´?˜¤?Š” ê°’ì„ DB?— ???¥ (Insert)
	public void insertMember (LogonDTO member) {
		try {
			String orgPass = member.getPass();
			byte[] targetBytes = orgPass.getBytes();
			
			// ?•”?˜¸?™”
			Encoder encoder = Base64.getEncoder();
			//Base64?¸ì½”ë”©
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
			
			System.out.println("?šŒ?›? •ë³? DB?…? ¥ ?„±ê³?");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("?šŒ?›? •ë³? DB?…? ¥?‹œ ?˜ˆ?™¸ ë°œìƒ");
		}finally {
			//instance.close();
		}
	}
	
	// ?•„?´?”” ì¤‘ë³µì²˜ë¦¬ ì²´í¬
	public int confirmId (String id) {
		int x = -1;
		
		try {
			String sql = "SELECT id FROM member WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // ?•„?´?””ê°? DB?— ì¡´ì¬?•˜?Š” ê²½ìš°
				
				System.out.println("ì¡´ì¬?•˜?Š” ID?…?‹ˆ?‹¤. ?•„?´?””: " + id);
				
				x = 1;
			} else { // ?•„?´?””ê°? DB?— ì¡´ì¬?•˜ì§? ?•Š?Š” ê²½ìš°
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("?•„?´?”” ì¤‘ë³µ ì²´í¬ ì¤? ?˜ˆ?™¸ë°œìƒ");
		}
		return x;
	}
	
	// ë¡œê·¸?¸ ê¸°ëŠ¥(loginPro.jsp) : ?¼?—?„œ ?„˜ê²¨ë°›?? ?•„?´?””?? ?Œ¨?Š¤?›Œ?“œë¥? DB?? ?™•?¸
		// ?‚¬?š©? ?¸ì¦?, Db?˜ ? •ë³´ë?? ?ˆ˜? •?•  ?•Œ, SB?˜ ? •ë³´ë?? ?‚­? œ?•  ?•Œ
		// ?‚¬?š©? ?¸ì¦?(MemberCheck.jsp)?—?„œ ?‚¬?š©?•˜?Š” ë©”ì„œ?“œ
	public int userCheck(String id, String pass) {
		int x = -1;
		
		// ë³µí˜¸?™” : ?•”?˜¸?™”?œ Passwordë¥? ?•´?…?œ Passwordë¡? ë³??™˜
		try {
			String orgPass = pass;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64?””ì½”ë”©
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT pass FROM member WHERE id=?";
		
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) { // ?•„?´?””ê°? ì¡´ì¬?•˜ë©?
				String dbpasswd = rs.getString("pass");
					// ?””ì½”ë”© ?•„?š”
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeì²˜ë¦¬?¨
				
				if(orgPass.equals(decodedTxt)) {
					x = 1;  // ?¼?—?„œ ?„˜ê²¨ì˜¨ ?Œ¨?Š¤?›Œ?“œ?? DB?—?„œ ê°?? ¸?˜¨ ?Œ¨?Š¤?›Œ?“œê°? ?¼ì¹˜í•  ?•Œ x?— 1?„ ?• ?‹¹
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "?Š” ì¡´ì¬?•˜ì§? ?•Š?Š” ë¹„ë?ë²ˆí˜¸ ?…?‹ˆ?‹¤.");
					x = 0;
				} else {
					System.out.println(id + " ?Š” ì¡´ì¬?•˜ì§? ?•Š?Š” ?•„?´?””?…?‹ˆ?‹¤.");
					x = -1;  // ?Œ¨?Š¤?›Œ?“œê°? ?¼ì¹˜í•˜ì§? ?•Š?„ ?•Œ
				}
			}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("?¸ì¦? ?‹¤?Œ¨?–ˆ?Šµ?‹ˆ?‹¤.");
			}finally {
				//instance.close();
			}
		return x;
	}
	
	
	
	
	
	
	
}

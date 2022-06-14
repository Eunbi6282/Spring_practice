package com.my.project.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.my.project.common.DBConnPool;


public class BoardDAO extends DBConnPool{
	
	private static BoardDAO instance = new BoardDAO();

	/// LogonDTOê°ì²´ë¥? ë¦¬í„´?•˜?Š” ë©”ì„œ?“œ 
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public BoardDAO() {
		super();
	}
	
	//ê²??ƒ‰ ì¡°ê±´?— ë§ëŠ” ê²Œì‹œë¬? ê°œìˆ˜ë¥? ë°˜í™˜
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";	// ? ˆì½”ë“œ?˜ ì´? ê°œìˆ˜ë°˜í™˜,
			if (map.get("searchWord") != null) { //T(String)?— searchWord ?´ ?ˆ?„ ?•Œ whereê°’ì„ ì¶”ê?ë¡? ì¶°ë¦¬?— ?„£?Š”?‹¤. 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
			
		try {
			psmt = con.prepareStatement(query);
			rs= psmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			System.out.println(totalCount);
		} catch (Exception e) {
			System.out.println("ê²Œì‹œë¬? ì¹´ìš´?Š¸ì¤? ?˜ˆ?™¸ë°œìƒ");
			e.printStackTrace();
		}finally {
			instance.close();
		}
		
		return totalCount;
	}
	
	// ê²??ƒ‰ ì¡°ê±´?— ë§ëŠ” ê²Œì‹œë¬? ëª©ë¡?„ ë°˜í™˜?•©?‹ˆ?‹¤.
			// DataBase?—?„œ Select?•œ ê²°ê³¼ê°’ì„ DTO?— ?‹´?•„?„œ ë¦¬í„´ ?‹œì¼œì¤Œ
		public List <BoardDTO> selectListPage ( Map<String, Object> map){
			List<BoardDTO> board = new Vector <BoardDTO>();
			
			String query = " "
					+ "SELECT * FROM ( " 
					+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
					+ "			SELECT * FROM board ";
			
			if (map.get("searchWord") != null) {	// ê²??ƒ‰ ê¸°ëŠ¥?„ ?‚¬?š©?–ˆ?‹¤?¼ë©? 
				query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += "		ORDER BY num DESC"
					+ " ) Tb "
					+ ") " 
					+" WHERE rNUM BETWEEN ? AND ?"
					+ " ORDER BY postdate DESC";
			
			System.out.println(query);  //ì½˜ì†”?— ? „ì²? ì¿¼ë¦¬ ì¶œë ¥
			
			try{	//psmtê°ì²´ ?ƒ?„±?›„ ì¿¼ë¦¬ ?‹¤?–‰
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();	// DataBase?—?„œ Select?•œ ê²°ê³¼ê°’ì„ rs?— ???¥
				
				// rs?˜ ???¥?œ ê°’ì„ DTO?— ???¥ ==> ê°ì²´ë¥? List?— add
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getString(1));
					dto.setId(rs.getString(2)); //rs?˜ index1ë²ˆì˜ ê°’ì„ setter?†µ?•´ ì£¼ì…
					dto.setName(rs.getString(3));
					dto.setTitle(rs.getString(4));
					dto.setContent(rs.getString(5));
					dto.setPostdate(rs.getDate(6));
					dto.setOfile(rs.getString(7));
					dto.setSfile(rs.getString(8));
					dto.setDowncount(rs.getInt(9));
					dto.setPass(rs.getString(10));
					dto.setVisitcount(rs.getInt(11));
					
					
					
					board.add(dto); // List?˜ DB?˜ rs?˜ ?•˜?‚˜?˜ ? ˆì½”ë“œ ê°’ì„ dto?— ???¥?•˜ê³? 
										// dtoë¥? List?— ì¶”ê?
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("ë¦¬ìŠ¤?Š¸?˜¤ë¥?");
			}finally {
				instance.close();
			}
			
			return board;	// board?Š” DTOê°ì²´ë¥? ?‹´ê³? ?ˆ?‹¤. 
		}
		
		// ëª©ë¡ ê²??ƒ‰ (Select ) : ì£¼ì–´ì§? ?¼? ¨ë²ˆí˜¸?— ?•´?‹¹?•˜?Š” ê°’ì„ DTO?— ?‹´?•„ ë°˜í™˜(?•œ ?˜?´ì§? read)
				//ViewController?—?„œ ?š”ì²? ì²˜ë¦¬/ idxê°’ìœ¼ë¡? select?•˜ê¸?
			public BoardDTO selectView(String num) {
				BoardDTO dto = new BoardDTO();	
				String query = "SELECT * FROM board WHERE num =?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, num);
					rs = psmt.executeQuery();
					
					if(rs.next()) {
						//rs(select ê²°ê³¼ë¬? ?“¤?–´?ˆ?Œ) set?´?š©?•´?„œ ê°? ì£¼ì…
						dto.setNum(rs.getString(1));
						dto.setId(rs.getString(2)); //rs?˜ index1ë²ˆì˜ ê°’ì„ setter?†µ?•´ ì£¼ì…
						dto.setName(rs.getString(3));
						dto.setTitle(rs.getString(4));
						dto.setContent(rs.getString(5));
						dto.setPostdate(rs.getDate(6));
						dto.setOfile(rs.getString(7));
						dto.setSfile(rs.getString(8));
						dto.setDowncount(rs.getInt(9));
						dto.setPass(rs.getString(10));
						dto.setVisitcount(rs.getInt(11));
					}
				}catch (Exception e) {
					System.out.println("ê²Œì‹œë¬? ?ƒ?„¸? •ë³? ì¶œë ¥?‹œ ?˜ˆ?™¸ ë°œìƒ");
					e.printStackTrace();
				}finally {
					instance.close();
				}
				return dto;
			}
			
		// ê²Œì‹œë¬? ì¡°íšŒ?ˆ˜ ì¦ê? ë©”ì„œ?“œ 
		public void updateVisitCount(String num) {
			String query = "UPDATE board SET "
					+ " visitcount = visitcount + 1 "
					+ " WHERE num = ?";
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				rs= psmt.executeQuery();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ê²Œì‹œë¬? ì¡°íšŒ?ˆ˜ ì¦ê??‹œ ?˜ˆ?™¸ ë°œìƒ");
			}finally {
				instance.close();
			}
		}
	
		
		
		// ?°?´?„° ?‚½?…(Insert)
		public int insertWrite(BoardDTO dto) { // ?¼?—?„œ ?„˜ê²¨ë°›?? ê°’ë“¤?„ dto?— ???¥
			int result = 0;
			
			try {
				String query = "INSERT INTO board("
						+ " num, id ,name, title, content, ofile, sfile, pass) " 
						+ " VALUES( " 
						+ " seq_board_peb.nextval, ?, ?, ?, ?, ?, ? ,?)";
				
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getId());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setString(5, dto.getOfile());
				psmt.setString(6, dto.getSfile());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  //Insert?„±ê³µì¼ ?•Œ result > 0 // DB?— ê°’ì„ ???¥
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ê°? insert?‹œ ?˜ˆ?™¸ë°œìƒ");
			}
			
			return result;  // ?„±ê³µì¼ ?–„ result >0, ?‹¤?Œ¨?‹œ :0 
		}
		
		// ?‹¤?š´ë¡œë“œ ?šŸ?ˆ˜ ì¦ê? ë©”ì„œ?“œ
		public void downCountPlus (String num) {
			String query = "UPDATE board SET downcount = downcount + 1"
					+ " WHERE num = ?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("downloadCount ì²˜ë¦¬ì¤? ?˜ˆ?™¸ ë°œìƒ");
			}
		}
		
		// ë¹„ë?ë²ˆí˜¸ ?™•?¸ ë©”ì„œ?“œ (?…? ¥?•œ ë¹„ë?ë²ˆí˜¸ê°? DB?˜ ê°’ê³¼ ?¼ì¹˜í•˜?Š”ì§? ?™•?¸)
		public boolean confirmPass (String pass, String num) {
			boolean isCorr = true;
			try {
				// count(*) = 1 ? ˆì½”ë“œ ê°œìˆ˜, ? ˆì½”ë“œê°? ì¡´ì¬?•˜ë©? ?•„?´?””?? ?Œ¨?Š¤?›Œ?“œê°? ?¼ì¹˜í•˜?Š” ê²½ìš°?´?‹¤.
				// count(*) = 0 ? ˆì½”ë“œê°? ì¡´ì¬?•˜ì§? ?•Š?Œ.
				
				String query = "SELECT COUNT(*) FROM board WHERE pass = ? and num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, pass);
				psmt.setString(2, num);
				rs = psmt.executeQuery();
				
				rs.next();  // ? ˆì½”ë“œ?˜ ì²«ë²ˆ?•Œ?— ì»¤ì„œë¥? ?œ„ì¹? ?‹œì¼œë¼
				if (rs.getInt(1) == 0) {  // indexë°©ë²ˆ?˜¸?˜ 1ë²ˆë°©?˜ ê°’ì´ 0?´ë©? false
					isCorr = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ê²Œì‹œ?Œ ë¹„ë?ë²ˆí˜¸ ê²?ì¦? ì¤? ?˜ˆ?™¸ë°œìƒ");
			}
			return isCorr;
		}
		
		
		// Update
		public int updatePost (BoardDTO dto) {
			int result = 0;
			
			try {
				String query = "UPDATE board SET title =?, name=?, content=?, ofile =?, sfile=? WHERE num=? and pass=?"; //num?? passê°? ?‹¤ ë§ì„ ?–„ ?ˆ˜? •
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getNum());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  // update?„±ê³µì‹œ resultë³??ˆ˜?˜ ê°’ì´  >0
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ê²Œì‹œë¬? Update?‹œ ?˜ˆ?™¸ë°œìƒ");
			}
			return result;
		}
		
		// Delete
		public int deletePost (String num) {
			int result = 0;
			
			try {
				String query = "DELETE board WHERE num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				
				result = psmt.executeUpdate();	//resultê°? 0ë³´ë‹¤ ?¬ë©? ?‚­? œ ?„±ê³?, resultê°? 0?´ë©? ?‚­? œ ?‹¤?Œ¨
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ê²Œì‹œë¬? Delete?‹œ ?˜ˆ?™¸ë°œìƒ");
			}
			return result;
		}
		
	
}

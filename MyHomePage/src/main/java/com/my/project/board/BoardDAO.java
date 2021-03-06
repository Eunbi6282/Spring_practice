package com.my.project.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.my.project.common.DBConnPool;


public class BoardDAO extends DBConnPool{
	
	private static BoardDAO instance = new BoardDAO();

	/// LogonDTOκ°μ²΄λ₯? λ¦¬ν΄?? λ©μ? 
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public BoardDAO() {
		super();
	}
	
	//κ²?? μ‘°κ±΄? λ§λ κ²μλ¬? κ°μλ₯? λ°ν
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";	// ? μ½λ? μ΄? κ°μλ°ν,
			if (map.get("searchWord") != null) { //T(String)? searchWord ?΄ ?? ? whereκ°μ μΆκ?λ‘? μΆ°λ¦¬? ?£??€. 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
			
		try {
			psmt = con.prepareStatement(query);
			rs= psmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			System.out.println(totalCount);
		} catch (Exception e) {
			System.out.println("κ²μλ¬? μΉ΄μ΄?Έμ€? ??Έλ°μ");
			e.printStackTrace();
		}finally {
			instance.close();
		}
		
		return totalCount;
	}
	
	// κ²?? μ‘°κ±΄? λ§λ κ²μλ¬? λͺ©λ‘? λ°ν?©??€.
			// DataBase?? Select? κ²°κ³Όκ°μ DTO? ?΄?? λ¦¬ν΄ ?μΌμ€
		public List <BoardDTO> selectListPage ( Map<String, Object> map){
			List<BoardDTO> board = new Vector <BoardDTO>();
			
			String query = " "
					+ "SELECT * FROM ( " 
					+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
					+ "			SELECT * FROM board ";
			
			if (map.get("searchWord") != null) {	// κ²?? κΈ°λ₯? ?¬?©??€?Όλ©? 
				query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += "		ORDER BY num DESC"
					+ " ) Tb "
					+ ") " 
					+" WHERE rNUM BETWEEN ? AND ?"
					+ " ORDER BY postdate DESC";
			
			System.out.println(query);  //μ½μ? ? μ²? μΏΌλ¦¬ μΆλ ₯
			
			try{	//psmtκ°μ²΄ ??±? μΏΌλ¦¬ ?€?
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();	// DataBase?? Select? κ²°κ³Όκ°μ rs? ???₯
				
				// rs? ???₯? κ°μ DTO? ???₯ ==> κ°μ²΄λ₯? List? add
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getString(1));
					dto.setId(rs.getString(2)); //rs? index1λ²μ κ°μ setter?΅?΄ μ£Όμ
					dto.setName(rs.getString(3));
					dto.setTitle(rs.getString(4));
					dto.setContent(rs.getString(5));
					dto.setPostdate(rs.getDate(6));
					dto.setOfile(rs.getString(7));
					dto.setSfile(rs.getString(8));
					dto.setDowncount(rs.getInt(9));
					dto.setPass(rs.getString(10));
					dto.setVisitcount(rs.getInt(11));
					
					
					
					board.add(dto); // List? DB? rs? ??? ? μ½λ κ°μ dto? ???₯?κ³? 
										// dtoλ₯? List? μΆκ?
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("λ¦¬μ€?Έ?€λ₯?");
			}finally {
				instance.close();
			}
			
			return board;	// board? DTOκ°μ²΄λ₯? ?΄κ³? ??€. 
		}
		
		// λͺ©λ‘ κ²?? (Select ) : μ£Όμ΄μ§? ?Ό? ¨λ²νΈ? ?΄?Ή?? κ°μ DTO? ?΄? λ°ν(? ??΄μ§? read)
				//ViewController?? ?μ²? μ²λ¦¬/ idxκ°μΌλ‘? select?κΈ?
			public BoardDTO selectView(String num) {
				BoardDTO dto = new BoardDTO();	
				String query = "SELECT * FROM board WHERE num =?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, num);
					rs = psmt.executeQuery();
					
					if(rs.next()) {
						//rs(select κ²°κ³Όλ¬? ?€?΄??) set?΄?©?΄? κ°? μ£Όμ
						dto.setNum(rs.getString(1));
						dto.setId(rs.getString(2)); //rs? index1λ²μ κ°μ setter?΅?΄ μ£Όμ
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
					System.out.println("κ²μλ¬? ??Έ? λ³? μΆλ ₯? ??Έ λ°μ");
					e.printStackTrace();
				}finally {
					instance.close();
				}
				return dto;
			}
			
		// κ²μλ¬? μ‘°ν? μ¦κ? λ©μ? 
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
				System.out.println("κ²μλ¬? μ‘°ν? μ¦κ?? ??Έ λ°μ");
			}finally {
				instance.close();
			}
		}
	
		
		
		// ?°?΄?° ?½?(Insert)
		public int insertWrite(BoardDTO dto) { // ?Ό?? ?κ²¨λ°?? κ°λ€? dto? ???₯
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
				
				result = psmt.executeUpdate();  //Insert?±κ³΅μΌ ? result > 0 // DB? κ°μ ???₯
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("κ°? insert? ??Έλ°μ");
			}
			
			return result;  // ?±κ³΅μΌ ? result >0, ?€?¨? :0 
		}
		
		// ?€?΄λ‘λ ?? μ¦κ? λ©μ?
		public void downCountPlus (String num) {
			String query = "UPDATE board SET downcount = downcount + 1"
					+ " WHERE num = ?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("downloadCount μ²λ¦¬μ€? ??Έ λ°μ");
			}
		}
		
		// λΉλ?λ²νΈ ??Έ λ©μ? (?? ₯? λΉλ?λ²νΈκ°? DB? κ°κ³Ό ?ΌμΉν?μ§? ??Έ)
		public boolean confirmPass (String pass, String num) {
			boolean isCorr = true;
			try {
				// count(*) = 1 ? μ½λ κ°μ, ? μ½λκ°? μ‘΄μ¬?λ©? ??΄??? ?¨?€??κ°? ?ΌμΉν? κ²½μ°?΄?€.
				// count(*) = 0 ? μ½λκ°? μ‘΄μ¬?μ§? ??.
				
				String query = "SELECT COUNT(*) FROM board WHERE pass = ? and num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, pass);
				psmt.setString(2, num);
				rs = psmt.executeQuery();
				
				rs.next();  // ? μ½λ? μ²«λ²?? μ»€μλ₯? ?μΉ? ?μΌλΌ
				if (rs.getInt(1) == 0) {  // indexλ°©λ²?Έ? 1λ²λ°©? κ°μ΄ 0?΄λ©? false
					isCorr = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("κ²μ? λΉλ?λ²νΈ κ²?μ¦? μ€? ??Έλ°μ");
			}
			return isCorr;
		}
		
		
		// Update
		public int updatePost (BoardDTO dto) {
			int result = 0;
			
			try {
				String query = "UPDATE board SET title =?, name=?, content=?, ofile =?, sfile=? WHERE num=? and pass=?"; //num?? passκ°? ?€ λ§μ ? ?? 
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getNum());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  // update?±κ³΅μ resultλ³??? κ°μ΄  >0
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("κ²μλ¬? Update? ??Έλ°μ");
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
				
				result = psmt.executeUpdate();	//resultκ°? 0λ³΄λ€ ?¬λ©? ?­?  ?±κ³?, resultκ°? 0?΄λ©? ?­?  ?€?¨
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("κ²μλ¬? Delete? ??Έλ°μ");
			}
			return result;
		}
		
	
}

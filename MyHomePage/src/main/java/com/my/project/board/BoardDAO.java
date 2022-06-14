package com.my.project.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.my.project.common.DBConnPool;


public class BoardDAO extends DBConnPool{
	
	private static BoardDAO instance = new BoardDAO();

	/// LogonDTO객체�? 리턴?��?�� 메서?�� 
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public BoardDAO() {
		super();
	}
	
	//�??�� 조건?�� 맞는 게시�? 개수�? 반환
	public int selectCount(Map<String,Object> map) {
		int totalCount = 0;
		String query = "SELECT COUNT(*) FROM board";	// ?��코드?�� �? 개수반환,
			if (map.get("searchWord") != null) { //T(String)?�� searchWord ?�� ?��?�� ?�� where값을 추�?�? 춰리?�� ?��?��?��. 
				query += " Where " + map.get("searchField") + " " + "like '%" + map.get("searchWord") + "%'";
			}
			
		try {
			psmt = con.prepareStatement(query);
			rs= psmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
					
			System.out.println(totalCount);
		} catch (Exception e) {
			System.out.println("게시�? 카운?���? ?��?��발생");
			e.printStackTrace();
		}finally {
			instance.close();
		}
		
		return totalCount;
	}
	
	// �??�� 조건?�� 맞는 게시�? 목록?�� 반환?��?��?��.
			// DataBase?��?�� Select?�� 결과값을 DTO?�� ?��?��?�� 리턴 ?��켜줌
		public List <BoardDTO> selectListPage ( Map<String, Object> map){
			List<BoardDTO> board = new Vector <BoardDTO>();
			
			String query = " "
					+ "SELECT * FROM ( " 
					+ "		SELECT Tb.*, ROWNUM rNUM FROM ( "
					+ "			SELECT * FROM board ";
			
			if (map.get("searchWord") != null) {	// �??�� 기능?�� ?��?��?��?��?���? 
				query += " WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += "		ORDER BY num DESC"
					+ " ) Tb "
					+ ") " 
					+" WHERE rNUM BETWEEN ? AND ?"
					+ " ORDER BY postdate DESC";
			
			System.out.println(query);  //콘솔?�� ?���? 쿼리 출력
			
			try{	//psmt객체 ?��?��?�� 쿼리 ?��?��
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();	// DataBase?��?�� Select?�� 결과값을 rs?�� ???��
				
				// rs?�� ???��?�� 값을 DTO?�� ???�� ==> 객체�? List?�� add
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNum(rs.getString(1));
					dto.setId(rs.getString(2)); //rs?�� index1번의 값을 setter?��?�� 주입
					dto.setName(rs.getString(3));
					dto.setTitle(rs.getString(4));
					dto.setContent(rs.getString(5));
					dto.setPostdate(rs.getDate(6));
					dto.setOfile(rs.getString(7));
					dto.setSfile(rs.getString(8));
					dto.setDowncount(rs.getInt(9));
					dto.setPass(rs.getString(10));
					dto.setVisitcount(rs.getInt(11));
					
					
					
					board.add(dto); // List?�� DB?�� rs?�� ?��?��?�� ?��코드 값을 dto?�� ???��?���? 
										// dto�? List?�� 추�?
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("리스?��?���?");
			}finally {
				instance.close();
			}
			
			return board;	// board?�� DTO객체�? ?���? ?��?��. 
		}
		
		// 목록 �??�� (Select ) : 주어�? ?��?��번호?�� ?��?��?��?�� 값을 DTO?�� ?��?�� 반환(?�� ?��?���? read)
				//ViewController?��?�� ?���? 처리/ idx값으�? select?���?
			public BoardDTO selectView(String num) {
				BoardDTO dto = new BoardDTO();	
				String query = "SELECT * FROM board WHERE num =?";
				
				try {
					psmt = con.prepareStatement(query);
					psmt.setString(1, num);
					rs = psmt.executeQuery();
					
					if(rs.next()) {
						//rs(select 결과�? ?��?��?��?��) set?��?��?��?�� �? 주입
						dto.setNum(rs.getString(1));
						dto.setId(rs.getString(2)); //rs?�� index1번의 값을 setter?��?�� 주입
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
					System.out.println("게시�? ?��?��?���? 출력?�� ?��?�� 발생");
					e.printStackTrace();
				}finally {
					instance.close();
				}
				return dto;
			}
			
		// 게시�? 조회?�� 증�? 메서?�� 
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
				System.out.println("게시�? 조회?�� 증�??�� ?��?�� 발생");
			}finally {
				instance.close();
			}
		}
	
		
		
		// ?��?��?�� ?��?��(Insert)
		public int insertWrite(BoardDTO dto) { // ?��?��?�� ?��겨받?? 값들?�� dto?�� ???��
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
				
				result = psmt.executeUpdate();  //Insert?��공일 ?�� result > 0 // DB?�� 값을 ???��
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("�? insert?�� ?��?��발생");
			}
			
			return result;  // ?��공일 ?�� result >0, ?��?��?�� :0 
		}
		
		// ?��?��로드 ?��?�� 증�? 메서?��
		public void downCountPlus (String num) {
			String query = "UPDATE board SET downcount = downcount + 1"
					+ " WHERE num = ?";
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, num);
				psmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("downloadCount 처리�? ?��?�� 발생");
			}
		}
		
		// 비�?번호 ?��?�� 메서?�� (?��?��?�� 비�?번호�? DB?�� 값과 ?��치하?���? ?��?��)
		public boolean confirmPass (String pass, String num) {
			boolean isCorr = true;
			try {
				// count(*) = 1 ?��코드 개수, ?��코드�? 존재?���? ?��?��?��?? ?��?��?��?���? ?��치하?�� 경우?��?��.
				// count(*) = 0 ?��코드�? 존재?���? ?��?��.
				
				String query = "SELECT COUNT(*) FROM board WHERE pass = ? and num = ?";
				psmt = con.prepareStatement(query);
				psmt.setString(1, pass);
				psmt.setString(2, num);
				rs = psmt.executeQuery();
				
				rs.next();  // ?��코드?�� 첫번?��?�� 커서�? ?���? ?��켜라
				if (rs.getInt(1) == 0) {  // index방번?��?�� 1번방?�� 값이 0?���? false
					isCorr = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시?�� 비�?번호 �?�? �? ?��?��발생");
			}
			return isCorr;
		}
		
		
		// Update
		public int updatePost (BoardDTO dto) {
			int result = 0;
			
			try {
				String query = "UPDATE board SET title =?, name=?, content=?, ofile =?, sfile=? WHERE num=? and pass=?"; //num?? pass�? ?�� 맞을 ?�� ?��?��
				psmt = con.prepareStatement(query);
				psmt.setString(1, dto.getTitle());
				psmt.setString(2, dto.getName());
				psmt.setString(3, dto.getContent());
				psmt.setString(4, dto.getOfile());
				psmt.setString(5, dto.getSfile());
				psmt.setString(6, dto.getNum());
				psmt.setString(7, dto.getPass());
				
				result = psmt.executeUpdate();  // update?��공시 result�??��?�� 값이  >0
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시�? Update?�� ?��?��발생");
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
				
				result = psmt.executeUpdate();	//result�? 0보다 ?���? ?��?�� ?���?, result�? 0?���? ?��?�� ?��?��
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("게시�? Delete?�� ?��?��발생");
			}
			return result;
		}
		
	
}

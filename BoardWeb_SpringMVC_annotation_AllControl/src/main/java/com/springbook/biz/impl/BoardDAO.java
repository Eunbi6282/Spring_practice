package com.springbook.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//Bean 등록  : 어노테이션 기반 빈등록. @Component 
@Repository ("boardDAO")
public class BoardDAO  {
	
	//1. JDBC 관련 변수 선언 
	private Connection conn = null; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	//2. SQL 쿼리 정의 (상수로 선언 ) 
	private final String BOARD_INSERT = "insert into board (seq, title, writer,content) "
			+ "	values ((select nvl (max(seq), 0)+1 from board) , ?, ?, ?)"; 
	private final String BOARD_UPDATE = "update board set title = ? , content = ? "
			+ "where seq = ?"; 
	private final String BOARD_DELETE = "delete board where seq = ?"; 
	private final String BOARD_GET = "select * from board where seq = ?"; 
	private final String BOARD_LIST = "select * from board order by seq desc"; 
	private final String BOARD_LIST_T = "select * from board where title like '%'||?||'%' "
							+ "order by seq desc";   //글 제목으로 검색 쿼리
	private final String BOARD_LIST_C = "select * from board where content like '%'||?||'%' "
							+ "order by seq desc";   //글 내용으로 검색 쿼리 
	
	//CRUD를 구현 하는 메소드 정의 
	//3. Insert 메소드 정의  (글 등록) 
	public void insertBoard(BoardVO vo) {
		 System.out.println("==> JDBC로 insertBoard() 기능 처리됨 ");
		 
		 try {
			 conn = JDBCUtil.getConnection();  // Connection객체 호출 
			 pstmt = conn.prepareStatement(BOARD_INSERT); 
			 pstmt.setString(1, vo.getTitle());
			 pstmt.setString(2, vo.getWriter());
			 pstmt.setString(3, vo.getContent());
			 pstmt.executeUpdate(); 
			 System.out.println("insert 성공함");
			 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 JDBCUtil.close(pstmt, conn);   //객체 제거 
		 }
	}
	
	//4. Update 메소드 정의 (글 수정)
	public void updateBoard(BoardVO vo) {
		System.out.println("==> JDBC로 updateBoard() 기능 처리됨 ");
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(BOARD_UPDATE); 
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());     
			pstmt.executeUpdate(); 
			System.out.println("글 수정 성공");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
		
	//5. Delete 메소드 정의 (글 삭제)
	public void deleteBoard(BoardVO vo) {
		System.out.println("==> JDBC로 deleteBoard() 기능 처리됨 ");
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(BOARD_DELETE); 
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate(); 
			System.out.println("글 삭제 성공");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	//6. Select 메소드 정의  (상세내용) (글 상세 조회)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==> JDBC로 getBoard() 기능 처리됨 ");
		BoardVO board = null; 
		
		try {
			conn = JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(BOARD_GET); 
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery(); 
			
			if (rs.next()) {   // select 한 결과값이 존재 한다면 , rs의 값을 board 에 setter 주입
				board = new BoardVO(); 
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));			
			}		
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;    // 메소드를 호출하는 곳으로 DB에서 가져온 값을 전달 
	}
	
	//7. Select 메소드 정의 (List ) (글 목록 조회 )
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		System.out.println("==> JDBC로 getBoardList() 기능 처리됨 ");
		
		List<BoardVO> boardList = new ArrayList<BoardVO>(); 
		
		try {
			conn= JDBCUtil.getConnection(); 
			pstmt = conn.prepareStatement(BOARD_LIST); 
			rs = pstmt.executeQuery();
			
			System.out.println("글 목록 조회 성공 ");
			
			while (rs.next()) { // DB에서 Select 한 결과 같을 vo에 저장 해서 
									// 각각의 vo 객체를 ArrayList에 저장해서 리턴 
				BoardVO board = new BoardVO(); 
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER")); 
				board.setContent(rs.getString("CONTENT")); 
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("CNT"));
				
				boardList.add (board); 		//board 를 ArrayList 에 저장함. 		
			}
			
		}catch (Exception e) {
			
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
				
		return boardList; 
	}
	
	
	

}

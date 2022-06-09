package com.springbook.biz.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//Bean 등록 : 어노테이션 기반 빈 등록 @Component
@Repository("boardDAO")
public class BoardDAO {
	
	//1. JDBC관련 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//2. SQL쿼리 정의(상수로 선언)
	private final String BOARD_INSERT = "insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE = "update board set title = ?, content = ?, where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_LIST = "select * from board order by seq desc";	
	private final String BOARD_LIST_T = "select * from board where title like '%'||?||'%'"
										+"order by seq desc";		//글 제목으로 검색쿼리
	private final String BOARD_LIST_C = "select * from board where content like '%'||?||'%'"
										+"order by seq desc"; //글내용으로 검색쿼리
	//CRUD를 구현하는 메소드 정의
	//3. Insert 메소드 정의
	public void insertBoard(BoardVO vo) {
		System.out.println("==> JDBC로 insertBoard() 기능처리됨");
		
		try {
			conn = JDBCUtil.getConnection();		//Connection객체 호출
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
			System.out.println("insert 성공");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("insert 기능 구현중 예외발생");
		}finally {
			JDBCUtil.close(pstmt, conn);		//객체 제거
		}
	}
	//4. Update메소드 정의
	public void updateBoard(BoardVO vo) {
		System.out.println("==> JDBC로 updateBoard()기능 처리됨");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getSeq());
			
			pstmt.executeUpdate();
			
			
			System.out.println("Update 성공");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("update 기능구현중 예외 발생");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	//5. Delete 메소드 정의
	public void deleteBoard(BoardVO vo) {
		System.out.println("==> JDBC로 deleteBoard() 기능 처리됨.");
		try {
			conn=JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
			System.out.println("글 삭제 성공");
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("delete 기능 구현중 예외 발생");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	//6. Select 메소드 정의 (상세내용) (글 상세 조회)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("==> JDBC로 getBoard() 기능 처리됨");
		BoardVO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("리스트 오류");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}
	//7. select 메소드 정의(List) (글 목록 조회)
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("==> JDBC로 getBoardList() 기능 처리됨");
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			pstmt = conn.prepareStatement(BOARD_LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	//DB에서 Select한 결과 값을 VO에 저장해서 각각의 vo 객체를 ArrayList에 저장해서 리턴
				
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				boardlist.add(board);		//board를 ArrayList(boardlist)에 저장한다.
			}
			
		}catch(Exception e) {
			
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardlist;
	}
}

package com.springbook.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.BoardVO;

@Repository("boardDAOSpring")
public class BoardDAOSpring {
	
	// Spring에서는 Connectio 갹체, Statement, PreparedStatement, ResultSet
		// 선언하지 않고 사용하지 않는다. 대신 jdbcTemplate을 사용해서 처리
	
	//JDBCTemplate을 사용하면 코드가 간단하고 깔끔해진다.
	
	// Insert, Update, Delete
		//jdbcTemplate.update(SQL,?,?,?..)
			
			
	// select
		// 1. 단일 레코드만 출력 (상세내용)
			// jdbcTemplate.queryForObject(SQL구문,?,?,new RowMapper인터페이스를 구현한 객체로 전달)
		// 2. .
			// jdbcTemplate.query(SQL구문,?,?,new RowMapper인터페이스를 구현한 객체로 전달)
	
	
		
	@Autowired  //객체 활성화
	private JdbcTemplate jdbcTemplate;//JdbcTemplate의 객체를 찾아서 활성화 시켜줌
	
	//2. SQL쿼리 정의(상수로 선언)
		private final String BOARD_INSERT = "insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE = "update board set title = ?, content = ?, where seq=?";
		private final String BOARD_DELETE = "delete board where seq=?";
		private final String BOARD_GET = "select * from board where seq = ?";
		private final String BOARD_LIST = "select * from board order by seq desc";	
		
	// CRUD 기능 구현
		// 1.  글 등록
		public void insertBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 insertBoard() 기능 처리");
			jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
		}
		
		// 2. 글 수정
		public void updateBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 updatetBoard() 기능 처리");
			jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		}
		
		//3. 글 삭제
		public void deleteBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 deletetBoard() 기능 처리");
			jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
		}
		
		//4. 글 상세 조회
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC로 getBoard() 기능 처리");
			Object[] args = {vo.getSeq()};
 			return jdbcTemplate.queryForObject(BOARD_GET,args, new BoardRowMapper());
		}
		
		//5. 글 목록 조회
		public List<BoardVO> getBoardList(BoardVO vo) {
			System.out.println("==> Spring JDBC로 getBoardList() 기능 처리");
			return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		}
		
		
	
}

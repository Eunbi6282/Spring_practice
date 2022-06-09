package com.springbook.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.BoardVO;

@Repository("boardDAOSpring")
public class BoardDAOSpring {
	
	// Spring������ Connectio ��ü, Statement, PreparedStatement, ResultSet
		// �������� �ʰ� ������� �ʴ´�. ��� jdbcTemplate�� ����ؼ� ó��
	
	//JDBCTemplate�� ����ϸ� �ڵ尡 �����ϰ� ���������.
	
	// Insert, Update, Delete
		//jdbcTemplate.update(SQL,?,?,?..)
			
			
	// select
		// 1. ���� ���ڵ常 ��� (�󼼳���)
			// jdbcTemplate.queryForObject(SQL����,?,?,new RowMapper�������̽��� ������ ��ü�� ����)
		// 2. .
			// jdbcTemplate.query(SQL����,?,?,new RowMapper�������̽��� ������ ��ü�� ����)
	
	
		
	@Autowired  //��ü Ȱ��ȭ
	private JdbcTemplate jdbcTemplate;//JdbcTemplate�� ��ü�� ã�Ƽ� Ȱ��ȭ ������
	
	//2. SQL���� ����(����� ����)
		private final String BOARD_INSERT = "insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
		private final String BOARD_UPDATE = "update board set title = ?, content = ?, where seq=?";
		private final String BOARD_DELETE = "delete board where seq=?";
		private final String BOARD_GET = "select * from board where seq = ?";
		private final String BOARD_LIST = "select * from board order by seq desc";	
		
	// CRUD ��� ����
		// 1.  �� ���
		public void insertBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC�� insertBoard() ��� ó��");
			jdbcTemplate.update(BOARD_INSERT,vo.getTitle(),vo.getWriter(),vo.getContent());
		}
		
		// 2. �� ����
		public void updateBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC�� updatetBoard() ��� ó��");
			jdbcTemplate.update(BOARD_UPDATE,vo.getTitle(),vo.getContent(),vo.getSeq());
		}
		
		//3. �� ����
		public void deleteBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC�� deletetBoard() ��� ó��");
			jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
		}
		
		//4. �� �� ��ȸ
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("==> Spring JDBC�� getBoard() ��� ó��");
			Object[] args = {vo.getSeq()};
 			return jdbcTemplate.queryForObject(BOARD_GET,args, new BoardRowMapper());
		}
		
		//5. �� ��� ��ȸ
		public List<BoardVO> getBoardList(BoardVO vo) {
			System.out.println("==> Spring JDBC�� getBoardList() ��� ó��");
			return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		}
		
		
	
}

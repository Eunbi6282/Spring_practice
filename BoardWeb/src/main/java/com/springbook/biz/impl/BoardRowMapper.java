package com.springbook.biz.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springbook.biz.BoardVO;

public class BoardRowMapper implements RowMapper<BoardVO>{
	// DB에서 Select한 결과를 BoardVo에 저장해서 값을 처리(JDBC 템플릿에서 사용함)

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BoardVO board = new BoardVO();
		
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setContent(rs.getString("WRITER"));
		board.setWriter(rs.getString("CONTENT"));
		board.setRegdate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		
		return null;
	}

}

package com.springbook.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.BoardVO;
import com.springbook.biz.common.LogAdvice;
import com.springbook.biz.common.log4jAdvice;

@Service("boardService")		//비즈니스 로직을 처리하는 클래스(BoardServiceImpl.java)
public class BoardServiceImpl implements BoardService {
	
	@Autowired	//객체 주입
	private BoardDAO boardDAO;
	
	// 모든 메서드가 실행하기 전에 로그를 남겨야 한다.
		//private LogAdvice log;
		//private log4jAdvice log2;
	
	public BoardServiceImpl() { //기본 생성자 호출 시 logAdvice객체 생성
		//log = new LogAdvice()
		//log2 = new log4jAdvice();
	}
	@Override
	public void insertBoard(BoardVO vo) {
		//log2.printlogging();
		
		// 예외 강제 발생
		//if (vo.getSeq() == 0) {
		//	throw new IllegalArgumentException();
		//}
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		//log2.printlogging();
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		//log2.printlogging();
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		//log2.printlogging();
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		//log2.printlogging();
		return boardDAO.getBoardList(vo);
	}

}

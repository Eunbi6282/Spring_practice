package com.springbook.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.BoardVO;
import com.springbook.biz.common.LogAdvice;
import com.springbook.biz.common.log4jAdvice;

@Service("boardService")		//����Ͻ� ������ ó���ϴ� Ŭ����(BoardServiceImpl.java)
public class BoardServiceImpl implements BoardService {
	
	@Autowired	//��ü ����
	private BoardDAO boardDAO;
	
	// ��� �޼��尡 �����ϱ� ���� �α׸� ���ܾ� �Ѵ�.
		//private LogAdvice log;
		//private log4jAdvice log2;
	
	public BoardServiceImpl() { //�⺻ ������ ȣ�� �� logAdvice��ü ����
		//log = new LogAdvice()
		//log2 = new log4jAdvice();
	}
	@Override
	public void insertBoard(BoardVO vo) {
		//log2.printlogging();
		
		// ���� ���� �߻�
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

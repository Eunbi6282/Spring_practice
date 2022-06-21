package com.springbook.biz.impl;

import java.util.List;

import com.springbook.biz.BoardVO;

public interface BoardService {   //인터페이스 : 객체화 할 수 없다. 

	//CRUD를 구현 하는 메소드 정의 
	//3. Insert 메소드 정의  (글 등록) 
	void insertBoard(BoardVO vo);

	//4. Update 메소드 정의 (글 수정)
	void updateBoard(BoardVO vo);

	//5. Delete 메소드 정의 (글 삭제)
	void deleteBoard(BoardVO vo);

	//6. Select 메소드 정의  (상세내용) (글 상세 조회)
	BoardVO getBoard(BoardVO vo);

	//7. Select 메소드 정의 (List ) (글 목록 조회 )
	List<BoardVO> getBoardList(BoardVO vo);

}
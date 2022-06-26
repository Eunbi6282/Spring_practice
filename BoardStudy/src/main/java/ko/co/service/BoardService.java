package ko.co.service;

import ko.co.vo.BoardVO;

public interface BoardService {
	
	//게시글 작성 
	public void write(BoardVO boardVo) throws Exception;
}

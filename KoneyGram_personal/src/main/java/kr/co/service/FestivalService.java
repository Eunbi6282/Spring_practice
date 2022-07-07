package kr.co.service;

import java.util.List;

import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

public interface FestivalService {
	//축제 조회
	public List<FestivalVO> listFestival (SearchCriteria scri) throws Exception;
	
	// 게시물 총 갯수
	public int listFCount(SearchCriteria scri) throws Exception;
}

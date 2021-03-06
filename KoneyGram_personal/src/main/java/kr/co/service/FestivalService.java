package kr.co.service;

import java.util.List;

import kr.co.vo.FestivalVO;
import kr.co.vo.SearchCriteria;

public interface FestivalService {
	//축제 조회
	public List<FestivalVO> listFestival (SearchCriteria scri) throws Exception;
	
	// 게시물 총 갯수
	public int listFCount(SearchCriteria scri) throws Exception;
	
	// 축제 by admin
	public List<FestivalVO> listByAdmin (SearchCriteria scri) throws Exception;
	
	//축제 By admin 개수
	public int listByCount(SearchCriteria scri) throws Exception;
	
	//read
	public FestivalVO read(int f_code) throws Exception;
}
